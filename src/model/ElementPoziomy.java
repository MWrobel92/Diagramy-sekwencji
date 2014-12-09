package model;

/**
 * Klasa abstrakcyjna przedstawiająca poziomy element diagramu (komunikat lub blok wydzielony).
 * @author Michał Wróbel
 */
public abstract class ElementPoziomy extends ElementDiagramu {
    
    Obiekt obiektPoczatkowy;
    Obiekt obiektKoncowy;
    
    int wysokoscBloku;
    int indeksWiersza;
    
    public boolean obiektPoczatkowyJestPunktemSpecjalnym() {
        return obiektPoczatkowy.przedstawiaPunktSpecjalny();
    }
    
    public boolean obiektKoncowyJestPunktemSpecjalnym() {
        return obiektKoncowy.przedstawiaPunktSpecjalny();
    }
    
    /**
     * Zwraca indeks obliektu początkowego (lub -1, jeśli obiekt ten nie został zdefiniowany).
     * @return 
     */
    public int indeksObiektuPoczatkowego () {
        
        int doZwrotu = obiektPoczatkowy.zwrocIndeks();
        return doZwrotu;
    }
    
    /**
     * Zwraca indeks obliektu końcowego.
     * @return 
     */
    public int indeksObiektuKoncowego () {
        int doZwrotu = obiektKoncowy.zwrocIndeks();;        
        return doZwrotu;
    }
    
    public int indeksOstatniegoWiersza () {
        return wysokoscBloku + indeksWiersza - 1;
    }
    
    ElementPoziomy (Obiekt obiektPoczatkowy, Obiekt obiektKoncowy, int indeksWiersza) {        
        this.obiektPoczatkowy = obiektPoczatkowy;
        this.obiektKoncowy = obiektKoncowy;
        this.indeksWiersza = indeksWiersza;
        
        wysokoscBloku = 1;        
    }
    
}
