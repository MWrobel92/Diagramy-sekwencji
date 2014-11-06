package model;

import java.util.LinkedList;

/**
 * Klasa zajmująca się tworzeniem modelu na podstawie pliku tekstowego.
 * @author Michal
 */
public class AnalizatorTekstu {
    
    public static LinkedList<Komenda> wyodrebnijKomendy (String tekstWejsciowy) throws DiagramException {
        
        LinkedList<Komenda> komendy = new LinkedList<>();
        
        StringBuilder bufor = new StringBuilder();        
        String tymczasowyIdentyfikator = "";
                
        boolean wnetrzeIdentyfikatora = false;
        boolean wnetrzeCiala = false;
        boolean wnetrzeKomentarza = false;
        boolean ignorujKolejnyZnak = false;
        boolean wnetrzeNapisu = false;
        short liczbaKlamerZagniezdzonych = 0;
        
        for(char c : tekstWejsciowy.toCharArray()) {
            
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
                    komendy.add(new Komenda(bufor.toString(), ""));
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
                            komendy.add(new Komenda(tymczasowyIdentyfikator, bufor.toString()));
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
                    bufor.append(c);
                }                
            }            
        }
        
        if (wnetrzeCiala) {
            throw new DiagramException("Błąd składni - brak klamry zamykającej.");
        }
        else if (wnetrzeNapisu || ignorujKolejnyZnak) {
            throw new DiagramException("Błąd składni - brak nawiasu zamykającego napis.");
        }
             
        return komendy;
    }
    
    public static Diagram przygotujDiagram (String tekstWejsciowy) throws DiagramException {
        
        Diagram diagram = new Diagram();
        
        for (Komenda k : wyodrebnijKomendy(tekstWejsciowy)) {
            
            if (k.dotyczyDiagramu()) {
                przetworzKomendeDiagramu(k.przygotujListeAtrybutow(), diagram);                
            }
            else if (k.dotyczyObiektu()) {
                przetworzKomendeObiektu(k.przygotujListeAtrybutow(), diagram);
            }
            else if (k.dotyczyKomunikatu()) {
                przetworzKomendeKomunikatu(k.przygotujListeAtrybutow(), diagram);
            }
            else if (k.dotyczyObszaru()) {
                przetworzKomendeObszaru(k.przygotujListeAtrybutow(), diagram);
            }
            else {
                throw new DiagramException("Identyfikator komendy \"" + k.identyfikator + "\" jest niprawidłowy."); 
            }
            
        }
        
        diagram.ustawCzasyZyciaObiektow();
        return diagram;
        
    }

    private static void przetworzKomendeDiagramu(LinkedList<AtrybutKomendy> atrybuty, Diagram diagram) throws DiagramException {
          
        DaneDiagramu dane = new DaneDiagramu(atrybuty);
        if (dane.nazwa != null) {
            diagram.ustawNazwe(dane.nazwa);
        }
        diagram.ustawWymiary(dane.szerokosc, dane.wysokosc);
        
    }

    private static void przetworzKomendeObiektu(LinkedList<AtrybutKomendy> atrybuty, Diagram diagram) throws DiagramException {
        diagram.dodajObiekt(new DaneObiektu(atrybuty));
    }

    private static void przetworzKomendeKomunikatu(LinkedList<AtrybutKomendy> atrybuty, Diagram diagram) throws DiagramException {
        diagram.dodajKomunikat(new DaneKomunikatu(atrybuty));
    }

    private static void przetworzKomendeObszaru(LinkedList<AtrybutKomendy> atrybuty, Diagram diagram) throws DiagramException {           
        diagram.dodajObszarWydzielony(new DaneObszaru(atrybuty));
    }
    
}
