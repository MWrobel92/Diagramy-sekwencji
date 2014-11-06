package model;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Klasa reprezentująca model diagramu sekwencji
 * @author Michał Wróbel
 */
public class Diagram {
    
    private String nazwa;
    
    private LinkedList<Obiekt> listaObiektow;
    private LinkedList<Komunikat> listaKomunikatow;
    
    /** Obszary wydzielone, które domyślnie mogą istnieć równolegle z komunikatami */
    private LinkedList<ObszarWydzielony> listaSlabychObszarowWydzielonych;
    /** Obszary wydzielone, które domyślnie nie mogą instnieć równolegle z komunikatami */
    private LinkedList<ObszarWydzielony> listaMocnychObszarowWydzielonych;
    
    private LinkedList<String> listaOstrzezen;
    
    /** LIsta danych dodanych obiektów - zapamiętana, żeby dało się ustalić czasy życia */
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
                    throw new DiagramException("Istnieje już obiekt, który używa identyfikatora " + dane.identyfikator);
                }
            }
            idDoUstawienia = true;
            
        }
        else {
            //Trzeba sprawdzić, czy coś nie ma takiego identyfikatora, jak nasza nazwa
            for (Obiekt o : listaObiektow) {
                if (o.identyfikatorMocny() && o.id().equals(dane.nazwa)) {
                    throw new DiagramException("Istnieje już obiekt, który używa identyfikatora " + dane.nazwa);
                }
            }
        }
        
        Obiekt nowy = dodajObiekt(dane.nazwa, dane.nazwaSelektora, dane.typObiektu);
        
        if (idDoUstawienia) {
            nowy.ustawId(dane.identyfikator);
        }
        
        dane.gotowyObiekt = nowy;
        daneDodawanychObiektow.add(dane);
    }
    
    public Obiekt dodajObiekt(String nazwaKlasy, String nazwaSelektora, String typ) throws DiagramException {
        
        ObiektTyp typObiektu;
        
        if (typ.isEmpty()) {
            typ = "klasa"; // Wartość domyślna
        }
        
        switch (typ.toLowerCase()) {
            case "użytkownik" :
                typObiektu = ObiektTyp.UZYTKOWNIK;
                break;
            case "klasa" :
                typObiektu = ObiektTyp.KLASA;
                break;
            default:
                throw new DiagramException("Nie istnieje typ obiektu o nazwie " + typ);                
        }
        
        Obiekt nowyObiekt = new Obiekt(nazwaKlasy, nazwaSelektora, typObiektu);
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
                    throw new DiagramException("Istnieje już element poziomy, który używa identyfikatora " + dane.identyfikator);
                }
            }
            idDoUstawienia = true;
            
        }
        else {
            //Trzeba sprawdzić, czy coś nie ma takiego identyfikatora, jak nasza nazwa
            for (ElementPoziomy o : listaElementowPoziomych()) {
                if (o.identyfikatorMocny() && o.id().equals(dane.nazwa)) {
                    throw new DiagramException("Istnieje już obiekt, który używa identyfikatora " + dane.nazwa);
                }
            }
        }
        
        //Względna równoległość
        if (dane.nazwaRownoleglego != null) {            
            dane.indeksWiersza = indeksKomunikatu(dane.nazwaRownoleglego);            
        }
        
        Komunikat nowy;
        if (dane.daneObiektuZagniezdzonego == null) {
            nowy = dodajKomunikat(dane.nazwa, dane.typKomunikatu, dane.nazwaObiektu1, dane.nazwaObiektu2, dane.indeksWiersza);
        }
        else {
            nowy = dodajObiektTworzonyKomunikatem(dane.daneObiektuZagniezdzonego.nazwa,
                    dane.daneObiektuZagniezdzonego.nazwaSelektora,
                    dane.nazwa, dane.nazwaObiektu1, dane.indeksWiersza);
            
            dane.daneObiektuZagniezdzonego.gotowyObiekt = nowy.obiektKoncowy;
            daneDodawanychObiektow.add(dane.daneObiektuZagniezdzonego);
        }
        
        if (idDoUstawienia) {
            nowy.ustawId(dane.identyfikator);
        }
        
    }
    
    public Komunikat dodajKomunikat(String nazwaKomunikatu, String typ, String nazwaObiektu1, String nazwaObiektu2, Integer indeksWiersza) throws DiagramException {
        
        KomunikatTyp typKomunikatu;
        
        if (listaObiektow.isEmpty()) {
            throw new DiagramException("Nie mozna dodać komunikatu do diagramu, który nie zawiera żadnych obiektów.");
        }
   
        Obiekt obiektPoczatkowy;
        if (nazwaObiektu1 != null) {
            obiektPoczatkowy = znajdzObiekt(nazwaObiektu1);
        }
        else {
            obiektPoczatkowy = zwrocPunktPoczatkowy();
        }
        
        Obiekt obiektKoncowy;
        if (nazwaObiektu2 != null) {
            obiektKoncowy = znajdzObiekt(nazwaObiektu2);
        }
        else {
            obiektKoncowy = zwrocPunktKoncowy();
        }
        
        if (typ.isEmpty()) {
            typ = "wywołanie"; // Wartość domyślna
        }
        
        switch (typ.toLowerCase()) {
            case "wywołanie" :
                typKomunikatu = KomunikatTyp.WYWOLANIE;
                break;
            case "powrót" :
                typKomunikatu = KomunikatTyp.POWROT;
                break;
            case "asynchroniczny" :
                typKomunikatu = KomunikatTyp.ASYNCHRONICZNY;
                break;
            case "usuwanie" :
                typKomunikatu = KomunikatTyp.USUWANIE;
                break;
            default:
                throw new DiagramException("Nie istnieje typ komunikatu o nazwie " + typ);                
        }
        
        Komunikat nowyKomunikat;
        
        if (indeksWiersza == null)  { // Indeks wiersza nie jest wymuszony - dopisujemy na końcu
            indeksWiersza = zwrocLiczbeZajetychWierszy();           
        }
        
        //Sprawdzenie, czy obiekty w tym momencie w ogóle istnieją
        if ((obiektPoczatkowy.zwrocPrzesuniecieNaglowka() >= indeksWiersza) || (obiektKoncowy.zwrocPrzesuniecieNaglowka() >= indeksWiersza)) {
            throw new DiagramException("Nie można dodać komunikatu " + nazwaKomunikatu + " w żądanym miejscu, ponieważ obiekt " 
                    + obiektPoczatkowy.id() + " jeszcze nie istnieje.");
        }
        
        if ((obiektKoncowy.zwrocPrzesuniecieKonca() != 0)) { // Obiekt był niszczony
            
            if (typKomunikatu == KomunikatTyp.USUWANIE) {
                throw new DiagramException("Próba dwukrotnego usunięcia obiektu " + obiektKoncowy.id() + ".");
            }
            if (((obiektPoczatkowy.zwrocPrzesuniecieKonca() != 0) && (obiektPoczatkowy.zwrocPrzesuniecieKonca() <= indeksWiersza)) || (obiektKoncowy.zwrocPrzesuniecieKonca() <= indeksWiersza)) {
                throw new DiagramException("Nie można dodać komunikatu " + nazwaKomunikatu + " w żądanym miejscu, ponieważ obiekt " 
                    + obiektPoczatkowy.id() + " został wcześniej zniszczony.");
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
                    throw new DiagramException("Istnieje już element poziomy, który używa identyfikatora " + dane.identyfikator);
                }
            }
            idDoUstawienia = true;
            
        }
        else {
            //Trzeba sprawdzić, czy coś nie ma takiego identyfikatora, jak nasza nazwa
            for (ElementPoziomy o : listaElementowPoziomych()) {
                if (o.identyfikatorMocny() && o.id().equals(dane.nazwa)) {
                    throw new DiagramException("Istnieje już obiekt, który używa identyfikatora " + dane.nazwa);
                }
            }
        }
        
        //Względna równoległość
        if (dane.nazwaRownoleglego != null) {            
            dane.indeksWiersza = indeksKomunikatu(dane.nazwaRownoleglego);            
        }
        
        if (dane.nazwaKoncowego != null) {            
            dane.wysokoscBloku = indeksKomunikatu(dane.nazwaKoncowego) - dane.indeksWiersza + 1;
            if (dane.wysokoscBloku < 1) {
                throw new DiagramException("Koniec obszaru wydzielonego nie może znajdować się przed jego początkiem.");
            }
        }
        
        ObszarWydzielony nowy = dodajObszarWydzielony(dane.nazwa, dane.komentarz, dane.nazwaObiektu1,
                dane.nazwaObiektu2, dane.indeksWiersza, dane.wysokoscBloku,
                dane.domyslnaRownoleglosc);
        
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
    public void dodajObszarWydzielony(String nazwaObszaru, String nazwaObiektu1, String nazwaObiektu2, Integer indeksWiersza) throws DiagramException {
        dodajObszarWydzielony(nazwaObszaru, "", nazwaObiektu1, nazwaObiektu2, indeksWiersza, null, true);
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
    public ObszarWydzielony dodajObszarWydzielony(String nazwaObszaru, String komentarz, String nazwaObiektu1, String nazwaObiektu2, Integer indeksWiersza, Integer wysokoscBloku, boolean domyslnaRownoleglosc) throws DiagramException {
        
        Obiekt obiektPoczatkowy = znajdzObiekt(nazwaObiektu1);
        Obiekt obiektKoncowy = znajdzObiekt(nazwaObiektu2);
        
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
            throw new DiagramException("Obszar wydzielony " + nazwaObszaru + " ma błędną wysokość (mniejszą niż 1)");
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
    
    private Obiekt znajdzObiekt(String nazwaObiektu) throws DiagramException {
        
        Obiekt szukanyObiekt = null;
        
        for (Obiekt o : listaObiektow) {
            if (o.id().equals(nazwaObiektu)) {
                szukanyObiekt = o;
                break;
            }
        }
        
        if (szukanyObiekt == null) {            
            throw new DiagramException("Brak obiektu o nazwie " + nazwaObiektu);            
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
                        indeks = indeksKomunikatu(s2);
                    }
                    
                    // Sprawdzanie, czy obiekt w tym momencie w ogóle żyje                    
                    if ((dane.gotowyObiekt.zwrocPrzesuniecieNaglowka() >= indeks) || 
                            ((dane.gotowyObiekt.zwrocPrzesuniecieKonca() != 0) && 
                            (dane.gotowyObiekt.zwrocPrzesuniecieKonca() <= indeks))) {
                        throw new DiagramException("Obiekt nie istnieje w momencie, w którym miałby zmienić się jego status.");
                    }
                    
                    czasZycia.add(indeks);
                    
                }
                
                // TODO: Posortowanie czasów                
                dane.gotowyObiekt.ustawSprecyzowanyCzasZycia(czasZycia);
            }
        }
    }
    
    public Diagram () {
        
        this.nazwa = "Diagram";        
        listaObiektow = new LinkedList<>();
        listaKomunikatow = new LinkedList<>();
        listaMocnychObszarowWydzielonych = new LinkedList<>();
        listaSlabychObszarowWydzielonych = new LinkedList<>();
        listaOstrzezen = new LinkedList<>();
        daneDodawanychObiektow = new LinkedList<>();
        
        
    }    

    private Obiekt zwrocPunktPoczatkowy() {
     
        Obiekt doZwrotu;
        if (listaObiektow.getFirst().przedstawiaPunktSpecjalny()) {
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
        if (listaObiektow.getLast().przedstawiaPunktSpecjalny()) {
            doZwrotu = listaObiektow.getLast();
        }
        else {
            doZwrotu = new Obiekt();
            doZwrotu.ustawIndeks(listaObiektow.size());
            listaObiektow.addLast(doZwrotu);                
        }
        return doZwrotu;
    }

    public Komunikat dodajObiektTworzonyKomunikatem(String nazwaObiektu, String selektorObiektu, String nazwaKomunikatu, String nazwaObiektuWychodzacego, Integer indeksWiersza) throws DiagramException {
        
        if (indeksWiersza == null) {
            indeksWiersza = zwrocLiczbeZajetychWierszy();
        }
        
        Obiekt nowyObiekt = new Obiekt(nazwaObiektu, selektorObiektu, ObiektTyp.KLASA);        
        nowyObiekt.ustawIndeks(listaObiektow.size());
        nowyObiekt.ustawPrzesuniecieNaglowka(indeksWiersza);
        listaObiektow.add(nowyObiekt);
        
        Obiekt obiektPoczatkowy;
        if (nazwaObiektuWychodzacego != null) {
            obiektPoczatkowy = znajdzObiekt(nazwaObiektuWychodzacego);
        }
        else {
            obiektPoczatkowy = zwrocPunktPoczatkowy();
        }
        
        Komunikat nowyKomunikat = new Komunikat(nazwaKomunikatu, KomunikatTyp.TWORZENIE, obiektPoczatkowy, nowyObiekt, indeksWiersza);
           
        listaKomunikatow.add(nowyKomunikat);
        ustawDomyslnyCzasZyciaObiektow();
        
        return nowyKomunikat;
        
    }

    private Integer indeksKomunikatu(String nazwa) throws DiagramException {
        
        Integer indeks = null;
        
        for (Komunikat k : listaKomunikatow) {
            if (k.id().equals(nazwa)) {
                indeks = k.indeksWiersza;
                break;
            }
        }
            
        if (indeks == null) {
            throw new DiagramException("Nie istnieje komunikat o nazwie " + nazwa);
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
