package model;

/**
 * Klasa reprezentująca komunikat przezyłany pomiędzy obiektami
 * @author Michał Wróbel
 */
public class Komunikat extends ElementPoziomy {
    
    KomunikatTyp typKomunikatu;
    
    public Komunikat (String nazwaKomunikatu, KomunikatTyp typKomunikatu, Obiekt obiektPoczatkowy, Obiekt obiektKoncowy, int indeksWiersza) {
        
        super(obiektPoczatkowy, obiektKoncowy, indeksWiersza);        
        this.nazwa = nazwaKomunikatu;
        this.typKomunikatu = typKomunikatu;
        
        if (obiektPoczatkowy == obiektKoncowy) {
            wysokoscBloku = 2;
        }
        
        if (typKomunikatu == KomunikatTyp.USUWANIE) {
            obiektKoncowy.ustawPrzesuniecieKonca(indeksWiersza + wysokoscBloku - 1);
        }
        
    }    

    public boolean jestPowrotny() {
        return typKomunikatu == KomunikatTyp.POWROT;
    }
    
    public boolean jestWywolaniem() {
        return typKomunikatu == KomunikatTyp.WYWOLANIE;
    }

    public boolean jestTworzeniem() {
        return typKomunikatu == KomunikatTyp.TWORZENIE;
    }
    
    public boolean jestUsuwaniem() {
        return typKomunikatu == KomunikatTyp.USUWANIE;
    }
    
}
