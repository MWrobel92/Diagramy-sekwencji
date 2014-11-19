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
    
    int nrKomendy;
    private JezykSkladni jezykS;
    
    public DaneObiektu(List<AtrybutKomendy> listaAtrybutow, JezykSkladni jezyk, int nrLinii) throws DiagramException {
        
        nazwa = null;
        nazwaSelektora = null;
        typObiektu = null;
        identyfikator = null;
        listaPunktow = null;
        gotowyObiekt = null;
        
        nrKomendy = nrLinii;
        jezykS = jezyk;
        
        przetworz(listaAtrybutow);
    }
        
    private void przetworz(List<AtrybutKomendy> atrybuty) throws DiagramException {
        
        // Przetworzenie listy komend
        for (AtrybutKomendy a : atrybuty) {
            
            if (a.atrybutNazwy()) {                
                // Atrybut nazwy obiektu
                if (nazwa != null) {
                    throw new DiagramException(DiagramException.TypBledu.DWUKROTNA_DEFINICJA, a.nrLinii, a.identyfikator);
                }
                nazwa = a.cialo;                
            }
            else if (a.atrybutNazwyKlasy()) {
                // Atrybyt nazwy klasy/selektora
                if (nazwaSelektora != null) {
                    throw new DiagramException(DiagramException.TypBledu.DWUKROTNA_DEFINICJA, a.nrLinii, a.identyfikator);
                }
                nazwaSelektora = a.cialo; 
            }
            else if (a.atrybutTypu()) {
                // Atrybyt typu
                if (typObiektu != null) {
                    throw new DiagramException(DiagramException.TypBledu.DWUKROTNA_DEFINICJA, a.nrLinii, a.identyfikator);
                }
                typObiektu = a.cialo; 
            }
            else if (a.atrybutIdentyfikatora()) {
                // Atrybyt nazwy diagramu
                if (identyfikator != null) {
                    throw new DiagramException(DiagramException.TypBledu.DWUKROTNA_DEFINICJA, a.nrLinii, a.identyfikator);
                }
                identyfikator = a.cialo;                
            }
            else if (a.atrybutZycia()) {
                // Atrybyt nazwy diagramu
                if (listaPunktow != null) {
                    throw new DiagramException(DiagramException.TypBledu.DWUKROTNA_DEFINICJA, a.nrLinii, a.identyfikator);
                }
                String[] nazwyPunktow = a.cialo.split("[,]");
                if (nazwyPunktow.length < 2) {
                    throw new DiagramException(DiagramException.TypBledu.BLEDNA_LICZBA_PUNKTOW, a.nrLinii, a.identyfikator);
                }
                else if ((nazwyPunktow.length % 2) == 1) {
                    throw new DiagramException(DiagramException.TypBledu.BLEDNA_LICZBA_PUNKTOW, a.nrLinii, a.identyfikator);
                }
                else {
                    listaPunktow = Arrays.asList(nazwyPunktow);
                }
                
            }
            else {
                //Nieznany atrybut - generuj wyjątek
                throw new DiagramException(DiagramException.TypBledu.NIEOCZEKIWANY_ATRYBUT, a.nrLinii, a.identyfikator);
            }
        }
        
        // Rozpatrzenie przypadków nieistniejących atrybutów
        if (nazwa == null) {
            throw new DiagramException(DiagramException.TypBledu.NIEZDEFINIOWANY_ATRYBUT_OBOWIAZKOWY, nrKomendy, jezykS.atrybutNazwa());
        }        
        if (nazwaSelektora == null) {
            nazwaSelektora = "";
        }        
        if (typObiektu == null) {
            typObiektu = "";
        }

    }   
}
