package model;

/**
 *
 * @author Michał Wróbel
 */
public class ObszarWydzielony extends ElementPoziomy {
    
    String komentarz;
    
    public String zwrocKomantarz() {
        return komentarz;
    }
    
    public ObszarWydzielony (String nazwaObszaru, String komentarz, Obiekt obiektPoczatkowy, Obiekt obiektKoncowy, int wysokoscObszaru, int indeksWiersza) {
        
        super(obiektPoczatkowy, obiektKoncowy, indeksWiersza);
        wysokoscBloku = wysokoscObszaru;
        this.komentarz = komentarz;
        this.nazwa = nazwaObszaru;
 
        
    }
    
    public int zwrocWysokoscBloku() {
        return wysokoscBloku;
    }
    
}