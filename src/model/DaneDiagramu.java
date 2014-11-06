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
public class DaneDiagramu {
    
    /** Nazwa obszaru - element obowiązkowy */
    String nazwa;
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
        
    private void przetworz(List<AtrybutKomendy> atrybuty) throws DiagramException {      

        // Przetworzenie listy komend
        for (AtrybutKomendy a : atrybuty) {
            
            if (a.atrybutNazwy()) {
                // Atrybyt nazwy diagramu
                if (nazwa != null) {
                    throw new DiagramException("Dwukrotna próba wprowadzenia nazwy.");
                }
                nazwa = a.cialo;
            }
            else if (a.atrybutSzerokosci()) {
                // Atrybut szerokośći kolumny
                if (szerokosc != null) {
                    throw new DiagramException("Dwukrotna próba wprowadzenia szerokości kolumny.");
                }
                
                try {
                    szerokosc = Integer.parseInt(a.cialo);
                }
                catch (NumberFormatException ex) {
                    throw new DiagramException ("Wartość: " + a.cialo + " nie jest liczbą.");
                }
                
                if (szerokosc < 50) {
                    throw new DiagramException ("Szerokość wiersza nie możę być mniejsza niż 50 (odczytano" + a.cialo + ").");
                }
            }
            else if (a.atrybutWysokosci()) {
                // Atrybut wysokośći kolumny
                if (wysokosc != null) {
                    throw new DiagramException("Dwukrotna próba wprowadzenia wysokości wiersza.");
                }
                
                try {
                    wysokosc = Integer.parseInt(a.cialo);
                }
                catch (NumberFormatException ex) {
                    throw new DiagramException ("Wartość: " + a.cialo + " nie jest liczbą.");
                }
                
                if (szerokosc < 50) {
                    throw new DiagramException ("Szerokość wiersza nie możę być mniejsza niż 50 (odczytano " + a.cialo + ").");
                }
            }  
            
        }      
    }
}
