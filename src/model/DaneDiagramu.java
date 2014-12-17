/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 * Ta klasa przechowuje dane, które następnie mogą zostać wykorzystane do utworzenia nowego obszaru wydzielonego.
 * @author Michał Wróbel
 */
public class DaneDiagramu extends DaneElementu{
    
    /** Wysokość wiersza w pikselach (null oznacza wartość domyślną) */
    Integer wysokosc;
    /** Szerokosc kolumny w pikselach (null oznacza wartość domyślną) */
    Integer szerokosc;
    
    public DaneDiagramu(List<AtrybutKomendy> listaAtrybutow) throws DiagramException {
        
        nazwa = null;
        wysokosc = null;
        szerokosc = null;
        przetworz(listaAtrybutow);
    }
        
    /**
     * Przetwarza listę atrybutów na dane opisujące diagram.
     * @param atrybuty Lista atrybutów.
     * @throws DiagramException W przypadku błędnych atrybutów.
     */
    private void przetworz(List<AtrybutKomendy> atrybuty) throws DiagramException {      

        // Przetworzenie listy komend
        for (AtrybutKomendy a : atrybuty) {
            
            if (a.atrybutNazwy()) {
                // Atrybyt nazwy diagramu
                if (nazwa != null) {
                    throw new DiagramException(DiagramException.TypBledu.DWUKROTNA_DEFINICJA, a.nrLinii, a.identyfikator);
                }
                nazwa = a.cialo;
            }
            else if (a.atrybutSzerokosci()) {
                // Atrybut szerokośći kolumny
                if (szerokosc != null) {
                    throw new DiagramException(DiagramException.TypBledu.DWUKROTNA_DEFINICJA, a.nrLinii, a.identyfikator);
                }
                
                try {
                    szerokosc = Integer.parseInt(a.cialo);
                }
                catch (NumberFormatException ex) {
                    throw new DiagramException (DiagramException.TypBledu.WYMAGANA_LICZBA, a.nrLinii, a.identyfikator, a.cialo);
                }
                
                if (szerokosc < 50) {
                    throw new DiagramException (DiagramException.TypBledu.PONIZEJ_50, a.nrLinii, a.identyfikator, a.cialo);
                }
            }
            else if (a.atrybutWysokosci()) {
                // Atrybut wysokośći kolumny
                if (wysokosc != null) {
                    throw new DiagramException(DiagramException.TypBledu.DWUKROTNA_DEFINICJA, a.nrLinii, a.identyfikator);
                }
                
                try {
                    wysokosc = Integer.parseInt(a.cialo);
                }
                catch (NumberFormatException ex) {
                    throw new DiagramException (DiagramException.TypBledu.WYMAGANA_LICZBA, a.nrLinii, a.identyfikator, a.cialo);
                }
                
                if (wysokosc < 50) {
                    throw new DiagramException (DiagramException.TypBledu.PONIZEJ_50, a.nrLinii, a.identyfikator, a.cialo);
                }
            }
            else {
                //Nieznany atrybut - generuj wyjątek
                throw new DiagramException (DiagramException.TypBledu.NIEOCZEKIWANY_ATRYBUT, a.nrLinii, a.identyfikator);
            }
            
        }      
    }
}
