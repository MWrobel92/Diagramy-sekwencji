package model;

import java.util.Arrays;
import java.util.List;

    
/**
 * Ta klasa przechowuje dane, które następnie mogą zostać wykorzystane do utworzenia nowego obiektu.
 * @author Michał Wróbel
 */
public class DaneObiektu {
    
    /** Nazwa obiektu - element obowiązkowy */
    String nazwa;
    /** Nazwa selektora (to, co przed dwukropkiem) */
    String nazwaSelektora;
    /** Typ obiektu */
    String typObiektu;
    /** Nazwa identyfikatora (null oznacza używanie nazwy jako identyfikatora) */
    String identyfikator;
    
    /** Lista "punktów granicznych" życia obiektu */
    List<String> listaPunktow;
    /** Odnośnik do dodanego obiektu */
    Obiekt gotowyObiekt;   
    
    
    public DaneObiektu(List<AtrybutKomendy> listaAtrybutow) throws DiagramException {
        
        nazwa = null;
        nazwaSelektora = null;
        typObiektu = null;
        identyfikator = null;
        listaPunktow = null;
        gotowyObiekt = null;
        przetworz(listaAtrybutow);
    }
        
    private void przetworz(List<AtrybutKomendy> atrybuty) throws DiagramException {
        
        // Przetworzenie listy komend
        for (AtrybutKomendy a : atrybuty) {
            
            if (a.atrybutNazwy()) {                
                // Atrybut nazwy obiektu
                if (nazwa != null) {
                    throw new DiagramException("Dwukrotna próba wprowadzenia nazwy obiektu.");
                }
                nazwa = a.cialo;                
            }
            else if (a.atrybutNazwyKlasy()) {
                // Atrybyt nazwy klasy/selektora
                if (nazwaSelektora != null) {
                    throw new DiagramException("Dwukrotna próba wprowadzenia nazwy klasy obiektu.");
                }
                nazwaSelektora = a.cialo; 
            }
            else if (a.atrybutTypu()) {
                // Atrybyt typu
                if (typObiektu != null) {
                    throw new DiagramException("Dwukrotna próba wprowadzenia typu obiektu.");
                }
                typObiektu = a.cialo; 
            }
            else if (a.atrybutIdentyfikatora()) {
                // Atrybyt nazwy diagramu
                if (identyfikator != null) {
                    throw new DiagramException("Dwukrotna próba wprowadzenia nazwy identyfikatora.");
                }
                identyfikator = a.cialo;                
            }
            else if (a.atrybutZycia()) {
                // Atrybyt nazwy diagramu
                if (listaPunktow != null) {
                    throw new DiagramException("Dwukrotna próba wprowadzenia nazwy identyfikatora.");
                }
                String[] nazwyPunktow = a.cialo.split("[,]");
                if (nazwyPunktow.length < 2) {
                    throw new DiagramException("Zbyt mała liczba punktów życia obiektu.");
                }
                else if ((nazwyPunktow.length % 2) == 1) {
                    throw new DiagramException("Nieparzysta liczba punktów życia obiektu.");
                }
                else {
                    listaPunktow = Arrays.asList(nazwyPunktow);
                }
                
            }
            else {
                //Nieznany atrybut - generuj wyjątek
                throw new DiagramException ("Atrybut \"" + a.identyfikator + "\" nie jest prawidłowym atrybutem komunikatu.");
            }
        }
        
        // Rozpatrzenie przypadków nieistniejących atrybutów
        if (nazwa == null) {
            throw new DiagramException("Obiekt musi mieć podaną nazwę.");
        }        
        if (nazwaSelektora == null) {
            nazwaSelektora = "";
        }        
        if (typObiektu == null) {
            typObiektu = "";
        }

    }   
}
