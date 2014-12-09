package model;

import java.util.LinkedList;

/**
 * Klasa zajmująca się tworzeniem modelu na podstawie pliku tekstowego.
 * @author Michał Wróbel
 */
public class AnalizatorTekstu {
    
    private JezykSkladni jezykS;

    public AnalizatorTekstu(JezykSkladni jezyk) {
        jezykS = jezyk;
    }
    
    /**
     * Zamienia tekst podany jako parametr na listę komend.
     * @param tekstWejsciowy
     * @return
     * @throws DiagramException 
     */
    private LinkedList<Komenda> wyodrebnijKomendy (String tekstWejsciowy) throws DiagramException {
        
        tekstWejsciowy = tekstWejsciowy + ' '; // Żeby zawsze kończyło się białym znakiem
        
        LinkedList<Komenda> komendy = new LinkedList<>();
        int nrLinii = 1;
        int nrLiniiPoczatkuKomendy = 0;
        
        StringBuilder bufor = new StringBuilder();        
        String tymczasowyIdentyfikator = "";
                
        boolean wnetrzeIdentyfikatora = false;
        boolean wnetrzeCiala = false;
        boolean wnetrzeKomentarza = false;
        boolean ignorujKolejnyZnak = false;
        boolean wnetrzeNapisu = false;
        short liczbaKlamerZagniezdzonych = 0;
        
        for(char c : tekstWejsciowy.toCharArray()) {
            
            if (c == '\n') {
                    ++nrLinii;
            };
                
            if (ignorujKolejnyZnak) {
                
                bufor.append(c);
                ignorujKolejnyZnak = false;
                continue;
            }
            
            if (wnetrzeKomentarza) {
                if (c == '*') {
                    wnetrzeKomentarza = false;
                }
                continue;
            }
            else {
                if ((c == '*')&&(!wnetrzeNapisu)) {
                    wnetrzeKomentarza = true;
                    continue;
                }
            }
            
            if (wnetrzeIdentyfikatora) {
                
                //Ładujemy kolejne znaki do wnętrza identyfikatora czekając na klamrę otwierającą bądź biały znak
                if (c == '{') {
                    //Zapisujemy identyfikator i przechodzimy do ciała pętli
                    tymczasowyIdentyfikator = bufor.toString();
                    bufor = new StringBuilder();
                    wnetrzeIdentyfikatora = false;
                    wnetrzeCiala = true;
                }
                else if (Character.isWhitespace(c)) {
                    //Zapisujemy identyfikator bez paramentrów
                    komendy.add(new Komenda(bufor.toString(), "", nrLiniiPoczatkuKomendy, jezykS));
                    bufor = new StringBuilder();
                    wnetrzeIdentyfikatora = false;
                    continue;
                }
                else {
                    bufor.append(c);
                }
                
            }
            else if (wnetrzeCiala) {
                
                if (wnetrzeNapisu) {
                    if (c == '\\') {
                        // Odwrócony ukośnik - kolejny znak należy zignorować
                        ignorujKolejnyZnak = true;
                    }
                    else if (c == '"') {
                        // Cudzysłów zamykający - kończymy napis
                        wnetrzeNapisu = false;
                    }
                    // Jeśli ani jedno, ani drugie - nie robimy nic
                    
                }
                else {
                    
                    if (c == '"') {
                        // Cudzysłów otwierający - rozpoczynamy napis
                        wnetrzeNapisu = true;
                    }
                    else if (c == '{') {
                        // Znaleźliśmy klamrę zagnieżdżoną
                        ++liczbaKlamerZagniezdzonych;
                    } 
                    else if (c == '}') {
                        
                        if (liczbaKlamerZagniezdzonych == 0) {
                            //KONIEC KOMENDY - zapisujemy ją
                            komendy.add(new Komenda(tymczasowyIdentyfikator, bufor.toString(), nrLiniiPoczatkuKomendy, jezykS));
                            bufor = new StringBuilder();
                            wnetrzeCiala = false;
                            continue;
                            
                        }
                        else {
                            //Opuszczamy klamrę zagnieżdżoną
                            --liczbaKlamerZagniezdzonych;
                        }
                    }                                                    
                }  
                
                bufor.append(c);
                
            }
            else {
                
                //Czekamy na znak niebędący białym, który będzie początkiem identyfikatora
                if (!Character.isWhitespace(c)) {
                    wnetrzeIdentyfikatora = true;
                    nrLiniiPoczatkuKomendy = nrLinii;
                    bufor.append(c);
                }                
            }            
        }
        
        if (wnetrzeCiala) {
            throw new DiagramException(DiagramException.TypBledu.SKLADNIA, nrLinii, "}");
        }
        else if (wnetrzeNapisu || ignorujKolejnyZnak) {
            throw new DiagramException(DiagramException.TypBledu.SKLADNIA, nrLinii, "\"");
        }
        else if (wnetrzeKomentarza) {
            throw new DiagramException(DiagramException.TypBledu.SKLADNIA, nrLinii, "*");
        }
             
        return komendy;
    }
    
    /**
     * Funkcja przygotowuje przetwarza tekst źródłowy na model diagramu.
     * @param tekstWejsciowy Kod źródłowy diagramu.
     * @return Obiekt reprezentujący diagram.
     * @throws DiagramException Jeśli tekst źródłowy zawiera błędy.
     */
    public Diagram przygotujDiagram (String tekstWejsciowy) throws DiagramException {
        
        Diagram diagram = new Diagram(jezykS);
        
        for (Komenda k : wyodrebnijKomendy(tekstWejsciowy)) {
            
            if (k.dotyczyDiagramu()) {
                DaneDiagramu dane = new DaneDiagramu(k.przygotujListeAtrybutow());
                if (dane.nazwa != null) {
                    diagram.ustawNazwe(dane.nazwa);
                }
                diagram.ustawWymiary(dane.szerokosc, dane.wysokosc);               
            }
            else if (k.dotyczyObiektu()) {
                diagram.dodajObiekt(new DaneObiektu(k.przygotujListeAtrybutow(), jezykS, k.nrLinii));
            }
            else if (k.dotyczyKomunikatu()) {
                diagram.dodajKomunikat(new DaneKomunikatu(k.przygotujListeAtrybutow(), jezykS, k.nrLinii));
            }
            else if (k.dotyczyObszaru()) {
                diagram.dodajObszarWydzielony(new DaneObszaru(k.przygotujListeAtrybutow(), jezykS, k.nrLinii));
            }
            else {
                throw new DiagramException(DiagramException.TypBledu.NIEISTNIEJACA_KOMENDA, k.nrLinii, k.identyfikator); 
            }
            
        }
        
        diagram.ustawCzasyZyciaObiektow();
        return diagram;
        
    }    
}
