package model;

import java.util.List;

/**
 * Ta klasa przechowuje dane, które następnie mogą zostać wykorzystane do utworzenia nowego obszaru wydzielonego.
 * @author Michał Wróbel
 */
public class DaneObszaru {
    
    /** Nazwa obszaru - element obowiązkowy */
    String nazwa;
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
    
    public DaneObszaru(List<AtrybutKomendy> listaAtrybutow) throws DiagramException {
        
        nazwa = null;
        identyfikator = null;
        komentarz = null;        
        nazwaObiektu1 = null;
        nazwaObiektu2 = null;        
        indeksWiersza = null;
        wysokoscBloku = null;        
        domyslnaRownoleglosc = true;
        przetworz(listaAtrybutow);
    }
        
    private void przetworz(List<AtrybutKomendy> atrybuty) throws DiagramException {
        
        boolean zdefiniowanaDomyslnaRownoleglosc = false;
        
        // Przetworzenie listy komend
        for (AtrybutKomendy a : atrybuty) {
            
            if (a.atrybutNazwy()) {
                // Atrybyt nazwy diagramu
                if (nazwa != null) {
                    throw new DiagramException("Dwukrotna próba wprowadzenia nazwy obszaru.");
                }
                nazwa = a.cialo;                
            }
            else if (a.atrybutIdentyfikatora()) {
                // Atrybyt nazwy diagramu
                if (identyfikator != null) {
                    throw new DiagramException("Dwukrotna próba wprowadzenia nazwy identyfikatora.");
                }
                identyfikator = a.cialo;                
            }
            else if (a.atrybutKomentarza()) {
                // Atrybyt nazwy klasy
                if (komentarz != null) {
                    throw new DiagramException("Dwukrotna próba wprowadzenia komentarza obszaru.");
                }
                komentarz = a.cialo; 
            }
            else if (a.atrybutObiektuStartowego()) {
                // Atrybyt obiektu początkowego
                if (nazwaObiektu1 != null) {
                    throw new DiagramException("Dwukrotna próba wprowadzenia obiektu początkowego.");
                }
                nazwaObiektu1 = a.cialo; 
            }
            else if (a.atrybutObiektuKoncowego()) {
                // Atrybyt obiektu końcowego
                if (nazwaObiektu2 != null) {
                    throw new DiagramException("Dwukrotna próba wprowadzenia obiektu końcowego.");
                }
                nazwaObiektu2 = a.cialo; 
            }
            else if (a.atrybutWymuszeniaWiersza()) {
                // Atrybyt wymuszenia wiersza
                if (indeksWiersza != null) {
                    throw new DiagramException("Dwukrotna próba wprowadzenia indeksu wiersza.");
                }
                
                try {
                    indeksWiersza = Integer.parseInt(a.cialo);
                }
                catch (NumberFormatException ex) {
                    throw new DiagramException ("Wartość: " + a.cialo + " nie jest liczbą.");
                }
                
                if (indeksWiersza < 0) {
                    throw new DiagramException ("Numer wiersza nie może być ujemny.");
                }
            }
            else if (a.atrybutWysokosciBloku()) {
                // Atrybyt wymuszenia wiersza
                if (wysokoscBloku != null) {
                    throw new DiagramException("Dwukrotna próba wprowadzenia indeksu wiersza.");
                }
                
                try {
                    wysokoscBloku = Integer.parseInt(a.cialo);
                }
                catch (NumberFormatException ex) {
                    throw new DiagramException ("Wartość: " + a.cialo + " nie jest liczbą.");
                }
            }
            else if (a.atrybutWymuszeniaRownoleglosci()) {
                // Atrybyt wymuszenia wiersza
                if (zdefiniowanaDomyslnaRownoleglosc) {
                    throw new DiagramException("Dwukrotna próba zdefiniowania równoległości.");
                }
                
                zdefiniowanaDomyslnaRownoleglosc = true;
                domyslnaRownoleglosc = false;
            }
            else if (a.atrybutWzglednegoPolozenia()) {
                
                // Atrybyt wymuszenia wiersza
                if (nazwaRownoleglego != null) {
                    throw new DiagramException("Dwukrotna próba wprowadzenia nazwy elementu równoległego.");
                }
                else {
                    nazwaRownoleglego = a.cialo;
                }                
            }
            else if (a.drugiAtrybutWzglednegoPolozenia()) {
                
                // Atrybyt wymuszenia wiersza
                if (nazwaKoncowego != null) {
                    throw new DiagramException("Dwukrotna próba wprowadzenia nazwy elementu równoległego.");
                }
                else {
                    nazwaKoncowego = a.cialo;
                }                
            }
            else {
                //Nieznany atrybut - generuj wyjątek
                throw new DiagramException ("Atrybut \"" + a.identyfikator + "\" nie jest prawidłowym atrybutem obszaru wydzielonego.");
            }
        }
        
        // Rozpatrzenie przypadków nieistniejących atrybutów
        if (nazwa == null) {
            throw new DiagramException("Obszar wydzielony musi mieć zdefiniowaną etykietę.");
        }
        if (nazwaObiektu1 == null) {
            throw new DiagramException("Obszar wydzielony musi mieć zdefiniowany obiekt początkowy.");
        }
        if (nazwaObiektu2 == null) {
            throw new DiagramException("Obszar wydzielony musi mieć zdefiniowany obiekt końcowy.");
        } 

    }    
}
