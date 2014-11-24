package model;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Klasa reprezentująca model diagramu sekwencji
 * @author Michał Wróbel
 */
public class Diagram {
    
    private String nazwa;
    private JezykSkladni jezyk;
    
    private LinkedList<Obiekt> listaObiektow;
    private LinkedList<Komunikat> listaKomunikatow;
    
    /** Obszary wydzielone, które domyślnie mogą istnieć równolegle z komunikatami */
    private LinkedList<ObszarWydzielony> listaSlabychObszarowWydzielonych;
    /** Obszary wydzielone, które domyślnie nie mogą instnieć równolegle z komunikatami */
    private LinkedList<ObszarWydzielony> listaMocnychObszarowWydzielonych;
    
    /** Lista danych dodanych obiektów - zapamiętana, żeby dało się ustalić czasy życia */
    private LinkedList<DaneObiektu> daneDodawanychObiektow;
    
    private Integer wysokoscWiersza;
    private Integer szerokoscKolumny;

    
    /**
     * Akcesor do nazwy diagramu
     * @return 
     */
    public String nazwa() {
        return nazwa.substring(0);
    }
    
    /**
     * Ustawia żądane rozmiary komórki siatki rysowanego diagramu wyrażone w pikselach.
     * Wartość null oznacza zgodę na przyjęcie wartości domyślnej.
     */
    public void ustawWymiary (Integer szerokosc, Integer wysokosc) {
        wysokoscWiersza = wysokosc;
        szerokoscKolumny = szerokosc;
    }
    
    public Integer zwrocWysokoscWiersza () {
        return wysokoscWiersza;
    }
    
    public Integer zwrocSzerokoscKolumny () {
        return szerokoscKolumny;
    }
    
    public LinkedList<Obiekt> zwrocListeObiektow () {
        return (LinkedList<Obiekt>)listaObiektow.clone();
    }
    
    public LinkedList<Komunikat> zwrocListeKomunikatow () {
        return (LinkedList<Komunikat>)listaKomunikatow.clone();
    }
    
    public LinkedList<ObszarWydzielony> zwrocListeObszarow () {
        LinkedList<ObszarWydzielony> listaWszystkichObszarow = new LinkedList<>();
        listaWszystkichObszarow.addAll(listaSlabychObszarowWydzielonych);
        listaWszystkichObszarow.addAll(listaMocnychObszarowWydzielonych);
        return listaWszystkichObszarow;
    }
    
    public int zwrocLiczbeObiektow () {
        return listaObiektow.size();
    }
            
    /**
     * Funkcja zwracająca liczbę wykorzystanych wierszy diagramu (jego wysokość).
     * @return 
     */
    public int zwrocLiczbeWierszy () {
        
        int indeksOstatniegoWiersza = zwrocLiczbeZajetychWierszy();        
        for (ObszarWydzielony o : listaSlabychObszarowWydzielonych) {
            if (o.indeksOstatniegoWiersza() >= indeksOstatniegoWiersza) {
                indeksOstatniegoWiersza = o.indeksOstatniegoWiersza() + 1;
            }
        }
        return indeksOstatniegoWiersza;
    }
    
    /**
     * Funkcja zwracająca liczbę wierszy diagramu wykorzystanych przez elementy poziome, które domyślnie
     * nie powinny występować równolegle z innymi obiektami poziomy.
     * @return 
     */
    private int zwrocLiczbeZajetychWierszy () {
        
        int indeksOstatniegoWiersza = 0;        
        for (Komunikat k : listaKomunikatow) {
            if (k.indeksOstatniegoWiersza() >= indeksOstatniegoWiersza) {
                indeksOstatniegoWiersza = k.indeksOstatniegoWiersza() + 1;
            }
        }
        for (ObszarWydzielony o : listaMocnychObszarowWydzielonych) {
            if (o.indeksOstatniegoWiersza() >= indeksOstatniegoWiersza) {
                indeksOstatniegoWiersza = o.indeksOstatniegoWiersza() + 1;
            }
        }
        return indeksOstatniegoWiersza;
    }
    
    public void dodajObiekt(DaneObiektu dane) throws DiagramException {
        
        boolean idDoUstawienia = false;
        
        if (dane.identyfikator != null) {  
            
            for (Obiekt o : listaObiektow) {
                if (o.id().equals(dane.identyfikator)) {
                    throw new DiagramException(DiagramException.TypBledu.POWTORZONY_IDENTYFIKATOR_OBIEKTU, dane.nrKomendy, dane.identyfikator);
                }
            }
            idDoUstawienia = true;
            
        }
        else {
            //Trzeba sprawdzić, czy coś nie ma takiego identyfikatora, jak nasza nazwa
            for (Obiekt o : listaObiektow) {
                if (o.identyfikatorMocny() && o.id().equals(dane.nazwa)) {
                    throw new DiagramException(DiagramException.TypBledu.POWTORZONY_IDENTYFIKATOR_OBIEKTU, dane.nrKomendy, dane.nazwa);
                }
            }
        }
        
        Obiekt nowy = dodajObiekt(dane.nazwa, dane.nazwaSelektora, dane.typObiektu, dane.nrKomendy);
        
        if (idDoUstawienia) {
            nowy.ustawId(dane.identyfikator);
        }
        
        dane.gotowyObiekt = nowy;
        daneDodawanychObiektow.add(dane);
    }
    
    public Obiekt dodajObiekt(String nazwaKlasy, String nazwaSelektora, String typ, int nrLinii) throws DiagramException {
        
        ObiektTyp typObiektu;
        
        if (typ.isEmpty()) {
            typ = jezyk.typKlasa(); // Wartość domyślna
        }
        
        typ = typ.toLowerCase();      
      
        if (typ.matches(jezyk.typUzytkownik())) {
            typObiektu = ObiektTyp.UZYTKOWNIK;
        } else if (typ.matches(jezyk.typKlasa())) {
            typObiektu = ObiektTyp.KLASA;
        } else {
            throw new DiagramException(DiagramException.TypBledu.NIEISTNIEJACY_TYP_OBIEKTU, nrLinii, typ);                
        }
        
        Obiekt nowyObiekt = new Obiekt(nazwaKlasy, nazwaSelektora, typObiektu, nrLinii);
        nowyObiekt.ustawIndeks(listaObiektow.size());
        listaObiektow.add(nowyObiekt);
        
        return nowyObiekt;
        
    }
    
    public void dodajKomunikat(DaneKomunikatu dane) throws DiagramException {
        
        // Sprawdzanie unikalności identyfikatora
        boolean idDoUstawienia = false;        
        if (dane.identyfikator != null) {  
            
            for (ElementPoziomy o : listaElementowPoziomych()) {
                if (o.id().equals(dane.identyfikator)) {
                    throw new DiagramException(DiagramException.TypBledu.DWUKROTNA_DEFINICJA, dane.komendaNr , dane.identyfikator);
                }
            }
            idDoUstawienia = true;
            
        }
        else {
            //Trzeba sprawdzić, czy coś nie ma takiego identyfikatora, jak nasza nazwa
            for (ElementPoziomy o : listaElementowPoziomych()) {
                if (o.identyfikatorMocny() && o.id().equals(dane.nazwa)) {
                    throw new DiagramException(DiagramException.TypBledu.DWUKROTNA_DEFINICJA, dane.komendaNr, dane.nazwa);
                }
            }
        }
        
        //Względna równoległość
        if (dane.nazwaRownoleglego != null) {            
            dane.indeksWiersza = indeksKomunikatu(dane.nazwaRownoleglego, dane.komendaNr);            
        }
        
        Komunikat nowy;
        if (dane.daneObiektuZagniezdzonego == null) {
            nowy = dodajKomunikat(dane.nazwa, dane.typKomunikatu, dane.nazwaObiektu1, dane.nazwaObiektu2, dane.indeksWiersza, dane.komendaNr);
        }
        else {
            nowy = dodajObiektTworzonyKomunikatem(dane.daneObiektuZagniezdzonego.nazwa,
                    dane.daneObiektuZagniezdzonego.nazwaSelektora,
                    dane.nazwa, dane.nazwaObiektu1, dane.indeksWiersza, dane.komendaNr);
            
            dane.daneObiektuZagniezdzonego.gotowyObiekt = nowy.obiektKoncowy;
            daneDodawanychObiektow.add(dane.daneObiektuZagniezdzonego);
        }
        
        if (idDoUstawienia) {
            nowy.ustawId(dane.identyfikator);
        }
        
    }
    
    public Komunikat dodajKomunikat(String nazwaKomunikatu, String typ, String nazwaObiektu1,
            String nazwaObiektu2, Integer indeksWiersza, int nrLinii) throws DiagramException {
        
        KomunikatTyp typKomunikatu;
   
        Obiekt obiektPoczatkowy;
        if (nazwaObiektu1 != null) {
            obiektPoczatkowy = znajdzObiekt(nazwaObiektu1, nrLinii);
        }
        else {
            obiektPoczatkowy = zwrocPunktPoczatkowy();
        }
        
        Obiekt obiektKoncowy;
        if (nazwaObiektu2 != null) {
            obiektKoncowy = znajdzObiekt(nazwaObiektu2, nrLinii);
        }
        else {
            obiektKoncowy = zwrocPunktKoncowy();
        }
        
        if (typ.isEmpty()) {
            typ = jezyk.typWywolanie(); // Wartość domyślna
        }
        
        typ = typ.toLowerCase();      
      
        if (typ.matches(jezyk.typWywolanie())) {
                typKomunikatu = KomunikatTyp.WYWOLANIE;
        } else if (typ.matches(jezyk.typPowrot())) {
                typKomunikatu = KomunikatTyp.POWROT;
        } else if (typ.matches(jezyk.typAsynchroniczny())) {
                typKomunikatu = KomunikatTyp.ASYNCHRONICZNY;
        } else if (typ.matches(jezyk.typUsuwanie())) {
                typKomunikatu = KomunikatTyp.USUWANIE;
        } else {
                throw new DiagramException(DiagramException.TypBledu.NIEISTNIEJACY_TYP_KOMUNIKATU, nrLinii, typ);                
        }
        
        Komunikat nowyKomunikat;
        
        if (indeksWiersza == null)  { // Indeks wiersza nie jest wymuszony - dopisujemy na końcu
            indeksWiersza = zwrocLiczbeZajetychWierszy();           
        }
        
        //Sprawdzenie, czy obiekty w tym momencie w ogóle istnieją
        if (obiektPoczatkowy.zwrocPrzesuniecieNaglowka() >= indeksWiersza) {
            throw new DiagramException(DiagramException.TypBledu.OBIEKT_NIEUTWORZONY, nrLinii, nazwaKomunikatu, obiektPoczatkowy.id());
        } else if (obiektKoncowy.zwrocPrzesuniecieNaglowka() >= indeksWiersza) {
            throw new DiagramException(DiagramException.TypBledu.OBIEKT_NIEUTWORZONY, nrLinii, nazwaKomunikatu, obiektKoncowy.id());
        }
        
        if ((obiektKoncowy.zwrocPrzesuniecieKonca() != 0)) { // Obiekt był niszczony
            
            if (typKomunikatu == KomunikatTyp.USUWANIE) {
                throw new DiagramException(DiagramException.TypBledu.DWUKROTNE_USUNIECIE_OBIEKTU, nrLinii, obiektKoncowy.id());
            }
            if (((obiektPoczatkowy.zwrocPrzesuniecieKonca() != 0) && (obiektPoczatkowy.zwrocPrzesuniecieKonca() <= indeksWiersza)) || (obiektKoncowy.zwrocPrzesuniecieKonca() <= indeksWiersza)) {
                throw new DiagramException(DiagramException.TypBledu.OBIEKT_USUNIETY, nrLinii, nazwaKomunikatu, obiektPoczatkowy.id());
            }
            
        }
        
        
        nowyKomunikat = new Komunikat(nazwaKomunikatu, typKomunikatu, obiektPoczatkowy, obiektKoncowy, indeksWiersza);
        
        listaKomunikatow.add(nowyKomunikat);
        ustawDomyslnyCzasZyciaObiektow();
        
        return nowyKomunikat;
    }
    
    public void dodajObszarWydzielony(DaneObszaru dane) throws DiagramException {
        
        // Sprawdzanie unikalności identyfikatora
        boolean idDoUstawienia = false;        
        if (dane.identyfikator != null) {  
            
            for (ElementPoziomy o : listaElementowPoziomych()) {
                if (o.id().equals(dane.identyfikator)) {
                    throw new DiagramException(DiagramException.TypBledu.POWTORZONY_IDENTYFIKATOR_ELEMENTU_POZIOMEGO, dane.nrKomendy, dane.identyfikator);
                }
            }
            idDoUstawienia = true;
            
        }
        else {
            //Trzeba sprawdzić, czy coś nie ma takiego identyfikatora, jak nasza nazwa
            for (ElementPoziomy o : listaElementowPoziomych()) {
                if (o.identyfikatorMocny() && o.id().equals(dane.nazwa)) {
                    throw new DiagramException(DiagramException.TypBledu.POWTORZONY_IDENTYFIKATOR_ELEMENTU_POZIOMEGO, dane.nrKomendy, dane.nazwa);
                }
            }
        }
        
        //Względna równoległość
        if (dane.nazwaRownoleglego != null) {            
            dane.indeksWiersza = indeksKomunikatu(dane.nazwaRownoleglego, dane.nrKomendy);            
        }
        
        if (dane.nazwaKoncowego != null) {            
            dane.wysokoscBloku = indeksKomunikatu(dane.nazwaKoncowego, dane.nrKomendy) - dane.indeksWiersza + 1;
            if (dane.wysokoscBloku < 1) {
                throw new DiagramException(DiagramException.TypBledu.ODWROCONY_OBSZAR, dane.nrKomendy, dane.nazwa);
            }
        }
        
        ObszarWydzielony nowy = dodajObszarWydzielony(dane.nazwa, dane.komentarz, dane.nazwaObiektu1,
                dane.nazwaObiektu2, dane.indeksWiersza, dane.wysokoscBloku,
                dane.domyslnaRownoleglosc, dane.nrKomendy);
        
        if (idDoUstawienia) {
            nowy.ustawId(dane.identyfikator);
        }
        
    }
    
    /**
     * Dodaje do diagramu obszar wydzielony.
     * @param nazwaObszaru Treść etykiety obszaru.
     * @param nazwaObiektu1 Obiekt wyznaczający brzeg obszaru wydzielonego.
     * @param nazwaObiektu2 Obiekt wyznaczający brzeg obszaru wydzielonego.
     * @param indeksWiersza Indeks wiersza, od którego będzie się zaczynał obszar (jeśli null, obszar zostanie dodany na spodzie diagramu).
     * @throws DiagramException 
     */
    public void dodajObszarWydzielony(String nazwaObszaru, String nazwaObiektu1, String nazwaObiektu2, Integer indeksWiersza, int nrLinii) throws DiagramException {
        dodajObszarWydzielony(nazwaObszaru, "", nazwaObiektu1, nazwaObiektu2, indeksWiersza, null, true, nrLinii);
    }
    
    /**
     * Dodaje do diagramu obszar wydzielony.
     * @param nazwaObszaru Treść etykiety obszaru.
     * @param komentarz Komentarz, który będzie widoczny wewnątrz obszaru.
     * @param nazwaObiektu1 Obiekt wyznaczający brzeg obszaru wydzielonego.
     * @param nazwaObiektu2 Obiekt wyznaczający brzeg obszaru wydzielonego.
     * @param indeksWiersza Indeks wiersza, od którego będzie się zaczynał obszar (jeśli null, obszar zostanie dodany na spodzie diagramu).
     * @param wysokoscBloku Liczba wierszy, które będzie obejmował obszar (jeśli null, obszar przyjmie domyślną wysokość dwóch wierszy).
     * @param domyslnaRownoleglosc Jeśli true, obszar domyślnie będzie występował równolegle z innymi obiektami poziomymi. Jeśli false, doda się w sposób analogiczny dla komunikatu.
     * @throws DiagramException 
     */
    public ObszarWydzielony dodajObszarWydzielony(String nazwaObszaru, String komentarz, String nazwaObiektu1, String nazwaObiektu2, Integer indeksWiersza, Integer wysokoscBloku, boolean domyslnaRownoleglosc, int nrLinii) throws DiagramException {
        
        Obiekt obiektPoczatkowy = znajdzObiekt(nazwaObiektu1, nrLinii);
        Obiekt obiektKoncowy = znajdzObiekt(nazwaObiektu2, nrLinii);
        
        if (indeksWiersza == null) {
            indeksWiersza = zwrocLiczbeZajetychWierszy();
        }
        
        if (wysokoscBloku == null) {
            wysokoscBloku = 2;
        }
        
        if (komentarz == null) {
            komentarz = "";
        }
        
        if (wysokoscBloku < 1) {
            throw new DiagramException(DiagramException.TypBledu.NIEDODATNIA_WYSOKOSC_BLOKU, nrLinii, nazwaObszaru);
        }
        
        ObszarWydzielony nowyObszar = new ObszarWydzielony(nazwaObszaru, komentarz, obiektPoczatkowy, obiektKoncowy, wysokoscBloku, indeksWiersza);
        
        if (domyslnaRownoleglosc) {
            listaSlabychObszarowWydzielonych.add(nowyObszar);
        }
        else {
            listaMocnychObszarowWydzielonych.add(nowyObszar);
        }
        
        return nowyObszar;
        
    }
    
    public void ustawNazwe (String nazwa) {
        this.nazwa = nazwa;
    }
    
    private Obiekt znajdzObiekt(String nazwaObiektu, int nrLinii) throws DiagramException {
        
        Obiekt szukanyObiekt = null;
        
        for (Obiekt o : listaObiektow) {
            if (o.id().equals(nazwaObiektu)) {
                szukanyObiekt = o;
                break;
            }
        }
        
        if (szukanyObiekt == null) {            
            throw new DiagramException(DiagramException.TypBledu.BRAK_OBIEKTU, nrLinii, nazwaObiektu);            
        }
        
        return szukanyObiekt;
    }
    
    public void ustawDomyslnyCzasZyciaObiektow() {
        for (Obiekt o: listaObiektow) {
            o.ustawDomyslnyCzasZycia(listaKomunikatow);
        }
    }
    
    public void ustawCzasyZyciaObiektow() throws DiagramException {
              
        for (DaneObiektu dane : daneDodawanychObiektow) {
            
            if (dane.listaPunktow == null)
                dane.gotowyObiekt.ustawDomyslnyCzasZycia(listaKomunikatow);
            else {
                
                LinkedList<Integer> czasZycia = new LinkedList<>();
                
                for (String s : dane.listaPunktow) {
                    
                    String s2 = s.trim();
                    
                    Integer indeks = null;
                    
                    if (s2.length() > 1) {
                        if (s2.charAt(0) == '%') {
                            try {
                                indeks = Integer.parseInt(s2.substring(1));
                            }
                            catch (NumberFormatException ex) {
                            }
                        }
                    }
                    
                    if (indeks == null) {
                        //TODO: Można rozważyć, czy warto byłoby poszukać także obszarów
                        indeks = indeksKomunikatu(s2, dane.nrKomendy);
                    }
                    
                    // Sprawdzanie, czy obiekt w tym momencie w ogóle żyje                    
                    if ((dane.gotowyObiekt.zwrocPrzesuniecieNaglowka() >= indeks) || 
                            ((dane.gotowyObiekt.zwrocPrzesuniecieKonca() != 0) && 
                            (dane.gotowyObiekt.zwrocPrzesuniecieKonca() <= indeks))) {
                        throw new DiagramException(DiagramException.TypBledu.OBIEKT_NIEFUNKCJONUJACY, dane.gotowyObiekt.liniaKodu, dane.gotowyObiekt.id());
                    }
                    
                    czasZycia.add(indeks);
                    
                }
                
                // TODO: Posortowanie czasów                
                dane.gotowyObiekt.ustawSprecyzowanyCzasZycia(czasZycia);
            }
        }
    }
    
    public Diagram(JezykSkladni jezykS) {
        jezyk = jezykS;
        
        this.nazwa = "Diagram";        
        listaObiektow = new LinkedList<>();
        listaKomunikatow = new LinkedList<>();
        listaMocnychObszarowWydzielonych = new LinkedList<>();
        listaSlabychObszarowWydzielonych = new LinkedList<>();
        daneDodawanychObiektow = new LinkedList<>();
        
        nazwa = jezyk.komendaDiagram();
    }    

    private Obiekt zwrocPunktPoczatkowy() {
     
        Obiekt doZwrotu;
        if (!listaObiektow.isEmpty() && listaObiektow.getFirst().przedstawiaPunktSpecjalny()) {
            doZwrotu = listaObiektow.getFirst();
        }
        
        else {
            doZwrotu = new Obiekt();
            doZwrotu.ustawIndeks(0);
            listaObiektow.addFirst(doZwrotu);
            // Poprawienie indeksów pozostałym obiektom
            for (int i = 1; i < listaObiektow.size(); ++i) {
                listaObiektow.get(i).ustawIndeks(i);
            }
                
        }
        return doZwrotu;
    }

    private Obiekt zwrocPunktKoncowy() {
        Obiekt doZwrotu;
        if (!listaObiektow.isEmpty() && listaObiektow.getLast().przedstawiaPunktSpecjalny()) {
            doZwrotu = listaObiektow.getLast();
        }
        else {
            doZwrotu = new Obiekt();
            doZwrotu.ustawIndeks(listaObiektow.size());
            listaObiektow.addLast(doZwrotu);                
        }
        return doZwrotu;
    }

    public Komunikat dodajObiektTworzonyKomunikatem(String nazwaObiektu, String selektorObiektu, String nazwaKomunikatu, String nazwaObiektuWychodzacego, Integer indeksWiersza, int nrLinii) throws DiagramException {
        
        if (indeksWiersza == null) {
            indeksWiersza = zwrocLiczbeZajetychWierszy();
        }
        
        Obiekt nowyObiekt = new Obiekt(nazwaObiektu, selektorObiektu, ObiektTyp.KLASA, nrLinii);        
        nowyObiekt.ustawIndeks(listaObiektow.size());
        nowyObiekt.ustawPrzesuniecieNaglowka(indeksWiersza);
        listaObiektow.add(nowyObiekt);
        
        Obiekt obiektPoczatkowy;
        if (nazwaObiektuWychodzacego != null) {
            obiektPoczatkowy = znajdzObiekt(nazwaObiektuWychodzacego, nrLinii);
        }
        else {
            obiektPoczatkowy = zwrocPunktPoczatkowy();
        }
        
        Komunikat nowyKomunikat = new Komunikat(nazwaKomunikatu, KomunikatTyp.TWORZENIE, obiektPoczatkowy, nowyObiekt, indeksWiersza);
           
        listaKomunikatow.add(nowyKomunikat);
        ustawDomyslnyCzasZyciaObiektow();
        
        return nowyKomunikat;
        
    }

    private Integer indeksKomunikatu(String nazwa, int nrLinii) throws DiagramException {
        
        Integer indeks = null;
        
        for (Komunikat k : listaKomunikatow) {
            if (k.id().equals(nazwa)) {
                indeks = k.indeksWiersza;
                break;
            }
        }
            
        if (indeks == null) {
            throw new DiagramException(DiagramException.TypBledu.BRAK_KOMUNIKATU, nrLinii, nazwa);
        }    
        
        return indeks;
    }
    
    private ArrayList<ElementPoziomy> listaElementowPoziomych() {
        
        ArrayList<ElementPoziomy> lista = new ArrayList<>();
        
        lista.addAll(listaKomunikatow);
        lista.addAll(listaSlabychObszarowWydzielonych);
        lista.addAll(listaMocnychObszarowWydzielonych);
        
        return lista;
        
    }
}
