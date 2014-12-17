package model;

import java.util.List;

/**
 * Ta klasa przechowuje dane, które następnie mogą zostać wykorzystane do utworzenia nowego obszaru wydzielonego.
 * @author Michał Wróbel
 */
public class DaneObszaru  extends DaneElementu  {
    
    /** Nazwa identyfikatora (null oznacza używanie nazwy jako identyfikatora) */
    String identyfikator;
    /** Wiadomość, która wyświetli się wewnątrz obszaru wydzielonego */
    String komentarz;
    /** Obiekt brzegowy obszaru - element obowiązkowy */
    String nazwaObiektu1;
    /** Drugi obiekt brzegowy obszaru - element obowiązkowy */
    String nazwaObiektu2;
    /** Indeks wiersza, od którego zaczyna się obszar (null oznacza wartośc domyślną) */
    Integer indeksWiersza = null;
    /** Wysokość obszaru wyrażona w wierszach (null oznacza wartośc domyślną) */
    Integer wysokoscBloku = null;
    /** Informacja, czy obszar domyślnie występuje równolegle z komunikatami */
    boolean domyslnaRownoleglosc;
    /** Nazwa komunikatu, od wysokośći którego ma się zaczynać obszar */
    String nazwaRownoleglego;
    /** Nazwa komunikatu, do wysokośći którego ma sięgać obszar */
    String nazwaKoncowego;
    
    int nrKomendy;
    private JezykSkladni jezykS;
    
    public DaneObszaru(List<AtrybutKomendy> listaAtrybutow, JezykSkladni jezyk, int nrLinii) throws DiagramException {
        
        nazwa = null;
        identyfikator = null;
        komentarz = null;        
        nazwaObiektu1 = null;
        nazwaObiektu2 = null;        
        indeksWiersza = null;
        wysokoscBloku = null;        
        domyslnaRownoleglosc = true;
        
        nrKomendy = nrLinii;
        jezykS = jezyk;
        
        przetworz(listaAtrybutow);
    }
        
    /**
     * Przetwarza listę atrybutów na dane opisujące obszar (blok) wydzielony.
     * @param atrybuty Lista atrybutów.
     * @throws DiagramException W przypadku błędnych atrybutów.
     */
    private void przetworz(List<AtrybutKomendy> atrybuty) throws DiagramException {
        
        boolean zdefiniowanaDomyslnaRownoleglosc = false;
        
        // Przetworzenie listy komend
        for (AtrybutKomendy a : atrybuty) {
            
            if (a.atrybutNazwy()) {
                // Atrybyt nazwy diagramu
                if (nazwa != null) {
                    throw new DiagramException(DiagramException.TypBledu.DWUKROTNA_DEFINICJA, a.nrLinii, a.identyfikator);
                }
                nazwa = a.cialo;                
            }
            else if (a.atrybutIdentyfikatora()) {
                // Atrybyt nazwy diagramu
                if (identyfikator != null) {
                    throw new DiagramException(DiagramException.TypBledu.DWUKROTNA_DEFINICJA, a.nrLinii, a.identyfikator);
                }
                identyfikator = a.cialo;                
            }
            else if (a.atrybutKomentarza()) {
                // Atrybyt nazwy klasy
                if (komentarz != null) {
                    throw new DiagramException(DiagramException.TypBledu.DWUKROTNA_DEFINICJA, a.nrLinii, a.identyfikator);
                }
                komentarz = a.cialo; 
            }
            else if (a.atrybutObiektuStartowego()) {
                // Atrybyt obiektu początkowego
                if (nazwaObiektu1 != null) {
                    throw new DiagramException(DiagramException.TypBledu.DWUKROTNA_DEFINICJA, a.nrLinii, a.identyfikator);
                }
                nazwaObiektu1 = a.cialo; 
            }
            else if (a.atrybutObiektuKoncowego()) {
                // Atrybyt obiektu końcowego
                if (nazwaObiektu2 != null) {
                    throw new DiagramException(DiagramException.TypBledu.DWUKROTNA_DEFINICJA, a.nrLinii, a.identyfikator);
                }
                nazwaObiektu2 = a.cialo; 
            }
            else if (a.atrybutWymuszeniaWiersza()) {
                // Atrybyt wymuszenia wiersza
                if (indeksWiersza != null) {
                    throw new DiagramException(DiagramException.TypBledu.DWUKROTNA_DEFINICJA, a.nrLinii, a.identyfikator);
                }
                
                try {
                    indeksWiersza = Integer.parseInt(a.cialo);
                }
                catch (NumberFormatException ex) {
                    throw new DiagramException(DiagramException.TypBledu.WARTOSC_NIELICZBOWA, a.nrLinii, a.identyfikator, a.cialo);
                }
                
                if (indeksWiersza < 0) {
                    throw new DiagramException(DiagramException.TypBledu.WARTOSC_UJEMNA, a.nrLinii, a.identyfikator, a.cialo);
                }
            }
            else if (a.atrybutWysokosciBloku()) {
                // Atrybyt wymuszenia wiersza
                if (wysokoscBloku != null) {
                    throw new DiagramException(DiagramException.TypBledu.DWUKROTNA_DEFINICJA, a.nrLinii, a.identyfikator);
                }
                
                try {
                    wysokoscBloku = Integer.parseInt(a.cialo);
                }
                catch (NumberFormatException ex) {
                    throw new DiagramException(DiagramException.TypBledu.WARTOSC_NIELICZBOWA, a.nrLinii, a.identyfikator, a.cialo);
                }
            }
            else if (a.atrybutWymuszeniaRownoleglosci()) {
                // Atrybyt wymuszenia wiersza
                if (zdefiniowanaDomyslnaRownoleglosc) {
                    throw new DiagramException(DiagramException.TypBledu.DWUKROTNA_DEFINICJA, a.nrLinii, a.identyfikator);
                }
                
                zdefiniowanaDomyslnaRownoleglosc = true;
                domyslnaRownoleglosc = false;
            }
            else if (a.atrybutWzglednegoPolozenia()) {
                
                // Atrybyt wymuszenia wiersza
                if (nazwaRownoleglego != null) {
                    throw new DiagramException(DiagramException.TypBledu.DWUKROTNA_DEFINICJA, a.nrLinii, a.identyfikator);
                }
                else {
                    nazwaRownoleglego = a.cialo;
                }                
            }
            else if (a.drugiAtrybutWzglednegoPolozenia()) {
                
                // Atrybyt wymuszenia wiersza
                if (nazwaKoncowego != null) {
                    throw new DiagramException(DiagramException.TypBledu.DWUKROTNA_DEFINICJA, a.nrLinii, a.identyfikator);
                }
                else {
                    nazwaKoncowego = a.cialo;
                }                
            }
            else {
                //Nieznany atrybut - generuj wyjątek
                throw new DiagramException (DiagramException.TypBledu.NIEOCZEKIWANY_ATRYBUT, a.nrLinii, a.identyfikator);
            }
        }
        
        // Rozpatrzenie przypadków nieistniejących atrybutów
        if (nazwa == null) {
            throw new DiagramException(DiagramException.TypBledu.NIEZDEFINIOWANY_ATRYBUT_OBOWIAZKOWY, nrKomendy, jezykS.atrybutNazwa());
        }
        if (nazwaObiektu1 == null) {
            throw new DiagramException(DiagramException.TypBledu.NIEZDEFINIOWANY_ATRYBUT_OBOWIAZKOWY, nrKomendy, jezykS.atrybutObiektuStartowego());
        }
        if (nazwaObiektu2 == null) {
            throw new DiagramException(DiagramException.TypBledu.NIEZDEFINIOWANY_ATRYBUT_OBOWIAZKOWY, nrKomendy, jezykS.atrybutObiektuKoncowego());
        } 

    }    
}
