package model;

/**
 * Klasa reprezentująca obszar wydzielony
 * @author Michał Wróbel
 */
public class ObszarWydzielony extends ElementPoziomy {
    
    String komentarz;
    
    public String zwrocKomentarz() {
        return komentarz;
    }
    
    public ObszarWydzielony (String nazwaObszaru, String komentarz, Obiekt obiektPoczatkowy, Obiekt obiektKoncowy, int wysokoscObszaru, int indeksWiersza) {
        
        super(obiektPoczatkowy, obiektKoncowy, indeksWiersza);
        wysokoscBloku = wysokoscObszaru;
        this.komentarz = komentarz;
        this.nazwa = nazwaObszaru;
 
        
    }
    
    /**
     * Akcesor do wysokości bloku wyrażonej w liczbie wierszy.
     * @return 
     */
    public int zwrocWysokoscBloku() {
        return wysokoscBloku;
    }
    
}