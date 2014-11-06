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
public class DaneKomunikatu {
    
    /** Nazwa komunikatu - element obowiązkowy */
    String nazwa;
    /** Nazwa identyfikatora (null oznacza używanie nazwy jako identyfikatora) */
    String identyfikator;
    /** Typ komunikatu */
    String typKomunikatu;
    /** Obiekt, z którego wychodzi komunikat (null oznacza komunikat przychodzący spoza diagramu) */
    String nazwaObiektu1;
    /** Obiekt, do którego wpada komunikat (null oznacza komunikat wychodzący poza diagram) */
    String nazwaObiektu2;
    /** Indeks wiersza, w którym wymuszony jest komunikat (null oznacza wartośc domyślną) */
    Integer indeksWiersza;
    /** Dane obiektu tworzonego w tym samym czasie, co komunikat - zastępuje on wtedy obiekt nr 2 */
    DaneObiektu daneObiektuZagniezdzonego;
    /** Nazwa komunikatu, równolegle z którym ma się rysować komunikat */
    String nazwaRownoleglego;

    
    public DaneKomunikatu(List<AtrybutKomendy> listaAtrybutow) throws DiagramException {
        
        nazwa = null;
        identyfikator = null;
        typKomunikatu = null;        
        nazwaObiektu1 = null;
        nazwaObiektu2 = null;
        indeksWiersza = null;
        daneObiektuZagniezdzonego = null;
        nazwaRownoleglego = null;
        przetworz(listaAtrybutow);
    }
        
    private void przetworz(List<AtrybutKomendy> atrybuty) throws DiagramException {
    
        // Przetworzenie listy komend
        for (AtrybutKomendy a : atrybuty) {
            
            if (a.atrybutNazwy()) {
                // Atrybyt nazwy diagramu
                if (nazwa != null) {
                    throw new DiagramException("Dwukrotna próba wprowadzenia nazwy komunikatu.");
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
            else if (a.atrybutTypu()) {
                // Atrybyt nazwy klasy
                if (typKomunikatu != null) {
                    throw new DiagramException("Dwukrotna próba wprowadzenia nazwy typu komunikatu.");
                }
                typKomunikatu = a.cialo; 
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
                if ((nazwaObiektu2 != null) || (daneObiektuZagniezdzonego != null)) {
                    throw new DiagramException("Dwukrotna próba wprowadzenia obiektu końcowego.");
                }
                
                Komenda komendaPodrzedna = a.sprawdzKomendePodrzedna();                
                if ((komendaPodrzedna != null) && (komendaPodrzedna.dotyczyObiektu())) {                    
                    // Analiza obiektu zagnieżdżonego
                    daneObiektuZagniezdzonego = new DaneObiektu(komendaPodrzedna.przygotujListeAtrybutow());
                }
                else {
                    nazwaObiektu2 = a.cialo;
                }
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
            else if (a.atrybutWzglednegoPolozenia()) {
                
                // Atrybyt wymuszenia wiersza
                if (nazwaRownoleglego != null) {
                    throw new DiagramException("Dwukrotna próba wprowadzenia nazwy elementu równoległego.");
                }
                else {
                    nazwaRownoleglego = a.cialo;
                }
                
            }
            else {
                //Nieznany atrybut - generuj wyjątek
                throw new DiagramException ("Atrybut \"" + a.identyfikator + "\" nie jest prawidłowym atrybutem komunikatu.");
            }
        }
        
        // Rozpatrzenie przypadków nieistniejących atrybutów
        if (typKomunikatu == null) {
            typKomunikatu="";
        }        
        if (nazwa == null) {
            throw new DiagramException("Komunikat musi mieć zdefiniowaną nazwę.");
        }       
    }
}
