package model;

/**
 * Język interfejsu - polski.
 * @author Michał Wróbel
 */
public class JezykInterfejsuPolski implements JezykInterfejsu {

    @Override
    public String menuPlik() {
        return "Plik";
    }

    @Override
    public String menuEdycja() {
        return "Edycja";
    }

    @Override
    public String menuDiagram() {
        return "Diagram";
    }

    @Override
    public String menuPomoc() {
        return "Pomoc";
    }

    @Override
    public String menuPlikWczytaj() {
        return "Otwórz";
    }

    @Override
    public String menuPlikZapisz() {
        return "Zapisz";
    }

    @Override
    public String menuPlikZapiszJako() {
        return "Zapisz jako";
    }
    
    @Override
    public String menuPlikZamknij() {
        return "Zamknij";
    }
    
    @Override
    public String menuEdycjaCofnij() {
        return "Cofnij";
    }

    @Override
    public String menuEdycjaPonow() {
        return "Ponów";
    }
    
    @Override
    public String menuEdycjaWytnij() {
        return "Wytnij";
    }
    
    @Override
    public String menuEdycjaKopiuj() {
        return "Kopiuj";
    }
    
    @Override
    public String menuEdycjaWklej() {
        return "Wklej";
    }

    @Override
    public String menuEdycjaWstaw() {
        return "Wstaw...";
    }

    @Override
    public String menuEdycjaWstawAktora() {
        return "Uczestnika";
    }

    @Override
    public String menuEdycjaWstawKomunikat() {
        return "Komunikat";
    }

    @Override
    public String menuEdycjaWstawObszarWydzielony() {
        return "Blok wydzielony";
    }

    @Override
    public String menuDiagramGeneruj() {    
        return "Generuj";
    }

    @Override
    public String menuDiagramZapisz() {
        return "Zapisz do pliku";
    }

    @Override
    public String menuPomocPomoc() {
        return "Pomoc";
    }

    @Override
    public String menuPomocOProgramie() {
        return "O programie";
    }

    @Override
    public String kontrolkaKodZrodlowy() {
        return "Kod źródłowy";
    }
    
    @Override
    public String kontrolkaKonsola() {
        return "Rezultat kompilacji";
    }

    @Override
    public String przyciskTak() {
        return "Tak";
    }

    @Override
    public String przyciskNie() {
        return "Nie";
    }

    @Override
    public String przyciskAnuluj() {
        return "Anuluj";
    }

    @Override
    public String oknoNiezapisaneNaglowek() {
        return "Niezapisane zmiany";
    }

    @Override
    public String onkoNiezapisaneTresc() {
        return "Aplikacja zawiera niezapisane zmiany. Jeśli ją zakończysz, zmiany te zostaną utracone.\n\nCzy chcesz zapisać zamiany?";
    }
    
    @Override
    public String oknoBladWczytania() {
        return "Wystąpił problem z wczytaniem pliku.";
    }

    @Override
    public String oknoBladZapisu() {
        return "Wystąpił problem z zapisaniem pliku.";}

    @Override
    public String oknoBladKompilacji() {
        return "Diagram nie został wygenerowany, ponieważ kod źródłowy nie jest poprawny.";}

    @Override
    public String komunikatPoprawnaKompilacja() {
        return "KOMPILACJA POPRAWNA";
    }

    @Override
    public String bladNaglowek() {
        return "BŁĄD";
    }

    @Override
    public String bladSkladnia() {
        return "Błąd składni - oczekiwano ";
    }

    @Override
    public String bladNieistniejacaKomenda() {
        return "Nie istnieje komenda o nazwie ";
    }

    @Override
    public String bladDwukrotnaDefinicja() {
        return "Dwukrotna definicja atrybutu ";
    }

    @Override
    public String bladWymaganaLiczba() {
        return "Wartość nieliczbowa w atrybucie ";
    }

    @Override
    public String bladOdnaleziono1() {
        return " (odaleziono ";
    }

    @Override
    public String bladOdnaleziono2() {
        return ")";    
    }

    @Override
    public String bladPonizej50() {
        return "Wartość nie może być mniejsza niż 50. Atrybut: ";
    }

    @Override
    public String bladPowtorzonyIdentyfikatorObiektu() {
        return "Istnieje już obiekt używający identyfikatora ";
    }

    @Override
    public String bladNieistniejacyTypObiektu() {
        return "Nie istieje typ obiektu o nazwie ";
    }

    @Override
    public String bladNieistniejacyTypKomunikatu() {
        return "Nie istnieje typ komunikatu o nazwie ";
    }

    @Override
    public String bladObiektNieutworzony1() {
        return "Komunikat ";
    }

    @Override
    public String bladObiektNieutworzony2() {
        return " nie może być umieszczony w tym miescu, ponieważ obiekt ";
    }

    @Override
    public String bladObiektNieutworzony3() {
        return " nie został jeszcze utworzony";
    }

    @Override
    public String bladObiektUsuniety() {
        return " został wcześniej usunięty";
    }

    @Override
    public String bladPowtorzonyIdentyfikatorElementuPoziomego() {
        return "Istnieje już obiekt używający identyfikatora poziomego ";
    }

    @Override
    public String bladDwukrotneUsuniecieObiektu() {
        return "Dwukrotne usunięcie obiektu ";
    }

    @Override
    public String bladObiektNiefunkcjonujacy() {
        return "W tym momencie jeszcze nie istnieje obiekt o nazwie ";
    }

    @Override
    public String bladOdwroconyObszar() {
        return "Koniec bloku wydzielonego nie możę znajdować się przed jego początkiem - dotyczy bloku o nazwie ";
    }

    @Override
    public String bladNiedodatniaWysokoscBloku() {
        return "Wysokosć bloku wydzielonego musi być dodatnia - dotyczy bloku o nazwie  ";
    }

    @Override
    public String bladBrakObiektu() {
        return "Nie istnieje obiekt o nazwie  ";
    }

    @Override
    public String bladBrakKomunikatu() {
        return "Nie istnieje komunikat o nazwie  ";
    }

    @Override
    public String bladWartoscNieliczbowa() {
        return "Nieliczbowa wartość w atrybucie ";}

    @Override
    public String bladWartoscUjemna() {
        return "Ujemna watość w atrybucie  ";
    }

    @Override
    public String bladNieoczekiwanyAtrybut() {
        return "Nieprawidłowy atrybut - ";
    }

    @Override
    public String bladNiezdefiniowanyAtrybutObowiazkowy() {
        return "Niezdefiniowano obowiązkowago atrybutu o nazwie ";
    }

    @Override
    public String bladBlednaLiczbaPunktow() {
        return "Nieprawidłowa liczba punktów życia";
    }

    @Override
    public String menuOpcje() {
        return "Opcje";
    }

    @Override
    public String menuOpcjeJezyk() {
        return "Język";
    }

    @Override
    public String menuOpcjeAutoodswiezanie() {
        return "Automatyczne odświeżanie";
    }

    @Override
    public String kontrolkaJezykInterfejsu() {
        return "Język interfejsu";
    }

    @Override
    public String kontrolkaJezykSkladni() {
        return "Język składni";
    }

    @Override
    public String bladLinia() {
        return "linia";
    }

    @Override
    public String pomocOProgramie() {
        return "Program do tworzenia diagramów UML na podstawie wprowadznego tekstu.\n\n© Michał Wróbel, 2014";
    }

    @Override
    public String pomocOgolna() {
        return "Pomoc";}

    @Override
    public String pomocOgolnaTresc() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><p>");
        sb.append("Aplikacja umożliwia generowanie diagramów sekwencji UML na podstawie tekstu wprowadzanego w polu edycyjnym.");
        sb.append("</p><p>");        
        sb.append("Okno programu podzielone jest na trzy części. Rozmiary poszczegółnych części można zmieniać poprzez przeciąganie belek, które je oddzielają.");
        sb.append("</p><ul><li>");        
        sb.append("Z prawej strony znajduje się panel, na którym rysowany jest diagram.");
        sb.append("</li><li>");
        sb.append("Z lewej strony znajduje się pole tekstowe służące do wprowadzania kodu źródłowego.");
        sb.append("</li><li>");  
        sb.append("Pod polem tekstowym znajduje się konsola, w której wypisywany jest rezultat generowania diagramu (komunikat o poprawności lub rodzaj błędu).");
        sb.append("</li></ul></html>");
        return sb.toString();
    }

    @Override
    public String pomocSkladnia() {
        return "Składnia";
    }

    @Override
    public String pomocSkladniaTresc(JezykSkladni jezyk) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><p>");
        sb.append("Wszystkie elementy diagramu sekwencji dodawane są przy pomocy odpowiednich komend. Ogólna postać komendy wygląda następująco:");
        sb.append("</p><p>");        
        sb.append("nazwa_komendy{atrybut1=rezultat atrybut2=”inny rezultat”}");
        sb.append("</p><p>");        
        sb.append("Komenda rozpoczyna się od nazwy komendy. Od razu po nazwie w nawiasach klamrowych należy podać atrybuty komendy. Zapis atrybutu składa się z nazwy atrybutu, znaku równości i wybranego parametru. ");
        sb.append("Atrybuty rozdzielane są białymi znakami. Jeśli parametr atrybutu zawiera białe znaki, trzeba wziąć go w cudzysłów.");
        sb.append("</p><p>");        
        sb.append("Wewnątrz tekstów zawartych w cudzysłowach można używać znaku odwróconego ukośnika (\\), który powoduje, że kolejny znak nie będzie traktowany jako element składni (przykładowo, napis ”asd\\”asd” spowoduje wyświetlenie napisu asd”asd).");
        sb.append("</p><p>");        
        sb.append("W kodzie można zamieszczać także komentarze. Komentarze należy rozpoczynać i kończyć znakiem gwiazdki  (*).");
        sb.append("</p></html>");
        return sb.toString();
    }

    @Override
    public String pomocObiekty() {        
        return "Obiekty (uczestnicy)";
    }

    @Override
    public String pomocObiektyTresc(JezykSkladni jezyk) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("Obiekty dodawane są do diagramu komendą „");
        sb.append(jezyk.komendaObiekt());
        sb.append("”. Komenda ta przyjmuje następujące atrybuty:");
        sb.append("<ul><li>");
        sb.append("<b>");
        sb.append(jezyk.atrybutNazwa());
        sb.append("</b> – nazwa obiektu wyświetlana na diagramie (atrybut obowiązkowy)");
        sb.append("</li><li>");
        sb.append("<b>");
        sb.append(jezyk.atrybutId());
        sb.append("</b> – nazwa wykorzystywana do identyfikacji obiektu. Jeśli identyfikator nie zostanie zdefiniowany, do identyfikacji będzie wykorzystywana nazwa. ");
        sb.append("Identyfikator – w odróżnieniu od nazwy – musi być unikalny. Nie może on powtarzać się z nazwą, którą identyfikuje się inny obiekt. Identyfikator nie jest widoczny na diagramie.");
        sb.append("</li><li>");
        sb.append("<b>");
        sb.append(jezyk.atrybutTyp());
        sb.append("</b> – typ danego uczestnika. Ten atrybut może przyjąć następujące wartości: ");
        sb.append("<ul><li>");
        sb.append("<b>");
        sb.append(jezyk.typKlasa());
        sb.append("</b> – obiekt oznaczający klasę bądź inny moduł systemu (wartość domyślna),");
        sb.append("</li><li>");
        sb.append("<b>");
        sb.append(jezyk.typUzytkownik());
        sb.append("</b> – obiekt oznaczający aktora, czyli osobę korzystającą z systemu.");
        sb.append("</li></ul>");
        sb.append("</li><li>");
        sb.append("<b>");
        sb.append(jezyk.atrybutKlasa());
        sb.append("</b>  – nazwa selektora danego obiektu (np. definiującego klasę obiektu), tekst wyświetlany w nagłówku obiektu przed dwukropkiem.");
        sb.append("</li><li>");
        sb.append("<b>");
        sb.append(jezyk.atrybutZycie());
        sb.append("</b> – atrybut definiujący, które fragmenty linii życia mają zostać oznaczone jako czas życia obiektu. W tym atrybucie należy podać identyfikatory komunikatów, w których zaczyna się lub kończy czas życia obiektu. ");
        sb.append("Liczba tych identyfikatorów musi być parzysta. Identyfikatory należy oddzielać przecinkami. W przypadku braku zdefiniowania tego atrybutu, za domyślny czas życia zostanie przyjęty okres od obsłużenia pierwszego do obsłużenia ostatniego komunikatu dotyczącego danego obiektu. ");
        sb.append("Ustalanie czasu życia obiektów następuje po wygenerowaniu wszystkich elementów diagramu, dlatego w atrybucie tym wyjątkowo można podawać identyfikatory komunikatów, które zostaną zdefiniowane po definicji obiektu.");
        sb.append("</li></ul>");
        sb.append("</html>");
        return sb.toString();
    }

    @Override
    public String pomocKomunikaty() {
        return "Komunikaty";
    }

    @Override
    public String pomocKomunikatyTresc(JezykSkladni jezyk) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("Komunikaty dodawane są do diagramu komendą „");
        sb.append(jezyk.komendaKomunikat());
        sb.append("”. Komenda ta przyjmuje następujące atrybuty:");
        sb.append("<ul><li>");
        sb.append("<b>");
        sb.append(jezyk.atrybutNazwa());
        sb.append("</b> – nazwa komunikatu wyświetlana na diagramie (atrybut obowiązkowy)");
        sb.append("</li><li>");
        sb.append("<b>");
        sb.append(jezyk.atrybutId());
        sb.append("</b> – nazwa wykorzystywana do identyfikacji komunikatu. Jeśli identyfikator nie zostanie zdefiniowany, do identyfikacji będzie wykorzystywana nazwa. ");
        sb.append("Identyfikator – w odróżnieniu od nazwy – musi być unikalny. Nie może on powtarzać się z nazwą, którą identyfikuje się inny komunikat. Identyfikator nie jest widoczny na diagramie.");
        sb.append("</li><li>");
        sb.append("<b>");
        sb.append(jezyk.atrybutTyp());
        sb.append("</b> – typ danego komunikatu. Ten atrybut może przyjąć następujące wartości: ");
        sb.append("<ul><li>");
        sb.append("<b>");
        sb.append(jezyk.typWywolanie());
        sb.append("</b> – strzałka narysowana linią ciągłą, z wypełnionym grotem (wartość domyślna),");
        sb.append("</li><li>");
        sb.append("<b>");
        sb.append(jezyk.typPowrot());
        sb.append("</b> – strzałka narysowana linią przerywaną, z pustym grotem,");
        sb.append("</li><li>");
        sb.append("<b>");
        sb.append(jezyk.typAsynchroniczny());
        sb.append("</b> – strzałka narysowana linią ciągłą, z pustym grotem,");
        sb.append("</li><li>");
        sb.append("<b>");
        sb.append(jezyk.typUsuwanie());
        sb.append("</b> – strzałka wyglądająca tak samo, jak w przypadku typu „");
        sb.append(jezyk.typWywolanie());
        sb.append("”. Dodatkowo obiekt, do którego trafia komunikat, zostaje usunięty tzn. nie można już używać go do generowania i odbierania komunikatów.");
        sb.append("</li></ul>");
        sb.append("</li><li>");
        sb.append("<b>");
        sb.append(jezyk.atrybutObiektuStartowego());
        sb.append("</b>  – identyfikator obiektu, z którego zostaje wysłany komunikat. Jeśli nie zostanie zdefiniowany, komunikat będzie uznany za przychodzący spoza diagramu.");
        sb.append("</li><li>");
        sb.append("<b>");
        sb.append(jezyk.atrybutObiektuKoncowego());
        sb.append("</b> – identyfikator obiektu, do którego przychodzi komunikat. Może to być ten sam obiekt, z którego został wysłany komunikat. Jeśli nie zostanie zdefiniowany, komunikat będzie uznany za wychodzący poza diagram. ");
        sb.append("Zamiast nazwy obiektu parametr ten może przyjąć całą komendę tworzącą obiekt – wówczas do diagramu zostanie dodany nowy obiekt utworzony nie na wysokości nagłówka, a na wysokości tego komunikatu (atrybut określający typ jest wówczas bez znaczenia).");
        sb.append("</li><li>");
        sb.append("<b>");
        sb.append(jezyk.atrybutNrWiersza());
        sb.append("</b> – wysokość wyrażona jako indeks wiersza, na której ma zostać narysowany komunikat. Wartość ta musi być liczbą.");
        sb.append("</li><li>");
        sb.append("<b>");
        sb.append(jezyk.atrybutObok());
        sb.append("</b> – identyfikator obiektu, z którego zostaje wysłany komunikat. Jeśli nie zostanie zdefiniowany, komunikat będzie uznany za przychodzący spoza diagramu.");
        sb.append("</li></ul>");
        sb.append("</html>");
        return sb.toString();
    }

    @Override
    public String pomocBloki() {
        return "Bloki wydzielone";
    }

    @Override
    public String pomocBlokiTresc(JezykSkladni jezyk) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("Bloki (obszary) wydzielone dodawane są do diagramu komendą „");
        sb.append(jezyk.komendaObszar());
        sb.append("”. Komenda ta przyjmuje następujące atrybuty:");
        sb.append("<ul><li>");
        sb.append("<b>");
        sb.append(jezyk.atrybutNazwa());
        sb.append("</b> – nazwa bloku wyświetlana na diagramie wewnątrz etykiety dnajdującej się w lewym górnym rogu bloku wydzielonego (atrybut obowiązkowy)");
        sb.append("</li><li>");
        sb.append("<b>");
        sb.append(jezyk.atrybutId());
        sb.append("</b> – nazwa wykorzystywana do identyfikacji bloku. Jeśli identyfikator nie zostanie zdefiniowany, do identyfikacji będzie wykorzystywana nazwa. ");
        sb.append("Identyfikator – w odróżnieniu od nazwy – musi być unikalny. Nie może on powtarzać się z nazwą, którą identyfikuje się inny blok wydzielony. Identyfikator nie jest widoczny na diagramie.");
        sb.append("</li><li>");
        sb.append("<b>");
        sb.append(jezyk.atrybutKomentarz());
        sb.append("</b> – napis wyświetlony wewnątrz bloku wydzielonego.");
        sb.append("</li><li>");
        sb.append("<b>");
        sb.append(jezyk.atrybutObiektuStartowego());
        sb.append("</b>  – identyfikator skrajnego obiektu objętego przez blok wydzielony (atrybut obowiązkowy).");
        sb.append("</li><li>");
        sb.append("<b>");
        sb.append(jezyk.atrybutObiektuKoncowego());
        sb.append("</b> – identyfikator drugiego skrajnego obiektu objętego przez blok wydzielony. Może to być ten sam obiekt, który został podany w poprzednim atrybucie (atrybut obowiązkowy).");
        sb.append("</li><li>");
        sb.append("<b>");
        sb.append(jezyk.atrybutNrWiersza());
        sb.append("</b> – wysokość wyrażona jako indeks wiersza, na której ma rozpoczynać się blok wydzielony. Wartość ta musi być liczbą.");
        sb.append("</li><li>");
        sb.append("<b>");
        sb.append(jezyk.atrybutObok());
        sb.append("</b> – identyfikator komunikatu, na wysokości którego ma rozpoczynać się blok wydzielony.");
        sb.append("</li><li>");
        sb.append("<b>");
        sb.append(jezyk.atrybutWysokoscBloku());
        sb.append("</b> – wysokość bloku wydzielonego wyrażona w liczbie wierszy. Wartość ta musi być liczbą. Domyślnie blok przyjmuje wartość 2.");
        sb.append("</li><li>");
        sb.append("<b>");
        sb.append(jezyk.atrybutDoWiersza());
        sb.append("</b> – identyfikator komunikatu, na wysokości którego ma kończyć się blok wydzielony.");
        sb.append("</li><li>");
        sb.append("<b>");
        sb.append(jezyk.atrybutSamodzielny());
        sb.append("</b> – atrybut pomagający w rozmieszczeniu elementów na diagramie. Jeśli nie jest zdefiniowany, kolejne komunikaty będą domyślnie dodawane tak, jakby blok w ogóle nie istniał (od jego wysokości włącznie). ");
        sb.append("Jeśli natomiast jest zdefiniowany, komunikaty będą domyślnie dodawane pod blokiem. Atrybut ten nie wymaga żadnych parametrów.");
        sb.append("</li></ul>");
        sb.append("</html>");
        return sb.toString();
    }  

    @Override
    public String pomocDiagram() {
        return "Ustawienia diagramu";
    }

    @Override
    public String pomocDiagramTresc(JezykSkladni jezyk) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("Przy pomocy specjalnej komendy „");
        sb.append(jezyk.komendaObszar());
        sb.append("” możliwe jest wprowadzenie kilku ogólnych ustawień dotyczących diagramu. Komenda ta przyjmuje następujące atrybuty:");
        sb.append("<ul><li>");
        sb.append("<b>");
        sb.append(jezyk.atrybutNazwa());
        sb.append("</b> – nazwa diagramu wyświetlana w jego lewym górnym rogu. Wartość domyślna: „");
        sb.append(jezyk.komendaDiagram());
        sb.append("”.");
        sb.append("</li><li>");
        sb.append("<b>");
        sb.append(jezyk.atrybutWysokosc());
        sb.append("</b>  – wysokość pojedynczego wiersza diagramu wyrażona w pikselach. Minimalna wartość tego atrybutu wynosi 50.");
        sb.append("</li><li>");
        sb.append("<b>");
        sb.append(jezyk.atrybutSzerokosc());
        sb.append("</b> – szerokość pojedynczej kolumny diagramu. Minimalna wartość tego atrybutu wynosi  50.");
        sb.append("</li></ul>");
        sb.append("</html>");
        return sb.toString();}

    @Override
    public String pomocPliki() {
        return "Pliki";
    }

    @Override
    public String pomocPlikiTresc() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("Tekst źródłowy może być wczytywany z pliku i do niego zapisywany. Do obsługi tych funkcji służą pola „");
        sb.append(this.menuPlikWczytaj());
        sb.append("”, „");
        sb.append(this.menuPlikZapisz());
        sb.append("” i „");
        sb.append(this.menuPlikZapiszJako());
        sb.append("” znajdujące się w menu „");
        sb.append(this.menuPlik());
        sb.append("”. Możliwe jest także wyeksportowanie aktualnie widzianego diagramu do pliku PNG. Służy do tego opcja „");
        sb.append(this.menuDiagramZapisz());
        sb.append("” w menu „");
        sb.append(this.menuDiagram());
        sb.append("”.");
        sb.append("</html>");
        return sb.toString();
    }

    @Override
    public String pomocGenerowanieDiagramu() {
        return "Generowanie diagramu";}

    @Override
    public String pomocGenerowanieDiagramuTresc() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><p>");
        sb.append("Diagram generowany jest na bieżąco, po każdej modyfikacji wykonanej w polu tekstowym. Jeśli kod źródłowy jest poprawny, diagram zostaje natychmiast odświeżony, a w konsoli błędów pojawia się komunikat „");
        sb.append(komunikatPoprawnaKompilacja());
        sb.append("”. Jeśli wystąpił błąd, pozostawiony jest ostatnio wygenerowany diagram, a w konsoli błędów wypisany zostaje krótki opis błędu.");
        sb.append("</p><p>");        
        sb.append("Generowanie diagramu można również wymusić ręcznie poprzez wybranie opcji „");
        sb.append(menuDiagramGeneruj());
        sb.append("” w menu „");
        sb.append(menuDiagram());
        sb.append("”. Wówczas w przypadku błędu użytkownik zostaje dodatkowo powiadomiony o tym fakcie poprzez wyskakujące okno dialogowe. Jeśli użytkownik nie życzy sobie automatycznego generowania diagramu (np. w celu oszczędzania zasobów procesora), opcję tę można wyłączyć poprzez odhaczenie pola „");
        sb.append(this.menuOpcjeAutoodswiezanie());
        sb.append("” w menu „");
        sb.append(this.menuOpcje());
        sb.append("”.");
        sb.append("</p></html>");
        return sb.toString();
    }

    @Override
    public String etykietaNazwaPliku() {
        return "Nazwa pliku:";
    }

    @Override
    public String etykietaTypPliku() {
        return "Pliki typu:";
    }

    @Override
    public String etykietaModyfikuj() {
        return "Modyfikuj";
    }

    @Override
    public String etykietaWszystkiePliki() {
        return "Wszystkie pliki";
    }

    @Override
    public String etykietaPrzegladaj() {
        return "Przeglądaj:";
    }

    @Override
    public String etykietaZapiszDo() {
        return "Zapisz do:";
    }

    @Override
    public String etykietaWGore() {
        return "W górę o jeden poziom:";
    }

    @Override
    public String etykietaPulpit() {
        return "Folder domowy";
    }

    @Override
    public String etykietaNowyFolder() {
        return "Utwórz nowy folder";
    }

    @Override
    public String etykietaLista() {
        return "Lista";
    }

    @Override
    public String etykietaSzczegoly() {
        return "Szczegóły";
    }
    
    @Override
    public String etykietaPlikUml() {
        return "Plik diagramu UML";
    }
    
    @Override
    public String etykietaPlikPng() {
        return "Plik PNG";
    }
    
}
