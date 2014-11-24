package model;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Michał Wróbel
 */
public class Obiekt extends ElementDiagramu {
    
    private int indeks;    
   
    ObiektTyp typObiektu;
    private String nazwaSelektora;
    private int przesuniecieNaglowka;
    private int przesuniecieKonca;
    
    private LinkedList<Integer> czasZycia;
    
    int liniaKodu;
        
    public void ustawPrzesuniecieNaglowka(int przesuniecieNaglowka) {
        this.przesuniecieNaglowka = przesuniecieNaglowka;
    }
    
    public void ustawPrzesuniecieKonca(int przesuniecieNaglowka) {
        this.przesuniecieKonca = przesuniecieNaglowka;
    }
    
    public int zwrocPrzesuniecieNaglowka() {
        return przesuniecieNaglowka;
    }
    
    public int zwrocPrzesuniecieKonca() {
        return przesuniecieKonca;
    }
    
    void ustawIndeks(int id) {
        indeks = id;
    }
    
    public int zwrocIndeks() {
        return indeks;
    }
    
    public String pelnaNazwa () {
        return nazwaSelektora + " : " + nazwa;
    }
    
    /**
     * Konstruktor tworzący obiekt specjalny, czyli obiekt symbolizujący punkt końcowy/początkowy
     */
    public Obiekt () {
        this.nazwa = "";
        this.nazwaSelektora = "";
        this.typObiektu = ObiektTyp.PUNKT_SPECJALNY;
        this.liniaKodu = 0;
        
        this.przesuniecieNaglowka = -1;
        this.przesuniecieKonca = 0;
    }
    
    public Obiekt (String nazwaKlasy, String nazwaSelektora, ObiektTyp typObiektu, int liniaKodu) {
        
        this.nazwa = nazwaKlasy;
        this.nazwaSelektora = nazwaSelektora;
        this.typObiektu = typObiektu;
        
        this.przesuniecieNaglowka = -1;
        this.przesuniecieKonca = 0;
    }
    
    public void ustawDomyslnyCzasZycia (LinkedList<Komunikat> komunikaty) {
        
        int poczatek;
        int koniec;
        
        czasZycia = new LinkedList<>();
        
        LinkedList<Komunikat> pasujaceKomunikaty = new LinkedList<>();
        
        for(Komunikat k : komunikaty) {
            if ((k.obiektPoczatkowy.nazwa.equals(nazwa)||k.obiektKoncowy.nazwa.equals(nazwa))&&
                    k.indeksOstatniegoWiersza()>przesuniecieNaglowka && ((przesuniecieKonca == 0)||(k.indeksOstatniegoWiersza()<przesuniecieKonca))) {
                pasujaceKomunikaty.add(k);
            }
        }
        
        if (pasujaceKomunikaty.size() > 1) {
            
            poczatek = pasujaceKomunikaty.getFirst().indeksWiersza;
            koniec = pasujaceKomunikaty.getFirst().indeksWiersza;
            
            for(Komunikat k : pasujaceKomunikaty) {
                
                if (k.indeksWiersza < poczatek) {
                    poczatek = k.indeksWiersza;
                }
                
                if (k.indeksOstatniegoWiersza() > koniec) {
                    koniec = k.indeksOstatniegoWiersza();
                }
                
            }
        
        
            czasZycia.add(poczatek);
            czasZycia.add(koniec);
        }
    }
    
    public void ustawSprecyzowanyCzasZycia (LinkedList<Integer> przygotowanaLista) throws DiagramException {
        
        if (przygotowanaLista.size()%2 == 0) {
            czasZycia = przygotowanaLista;
        }
        else {
            throw new DiagramException(DiagramException.TypBledu.BLEDNA_LICZBA_PUNKTOW, liniaKodu, nazwa);
        }
    }

    public List<Integer> zwrocCzasZycia() {
        return (LinkedList<Integer>)czasZycia.clone();
    }

    public boolean przedstawiaUzytkownika() {
        return typObiektu == ObiektTyp.UZYTKOWNIK;
    }
    
    public boolean przedstawiaPunktSpecjalny() {
        return typObiektu == ObiektTyp.PUNKT_SPECJALNY;
    }
}
