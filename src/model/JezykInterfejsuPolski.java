/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


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
    public String menuEdycjaCofnij() {
        return "Cofnij";
    }

    @Override
    public String menuEdycjaPonow() {
        return "Ponów";
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
        return "Nie istieje typ komunikatu o nazwie ";
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
    public String pomocOgolnaTresc(JezykSkladni jezyk) {
        return "Tu będzie podstawowa pomoc dotycząca obsługi programu";
    }

    @Override
    public String pomocPodstawoweInformacje() {
        return "Informacje podstawowe";
    }

    @Override
    public String pomocPodstawoweInformacjeTresc(JezykSkladni jezyk) {
        return "Tu będzie podstawowa pomoc dotycząca tworzenia wykresów";
    }

    @Override
    public String pomocSkladnia() {
        return "Składnia";
    }

    @Override
    public String pomocSkladniaTresc(JezykSkladni jezyk) {
        return "Tu będzie podstawowa pomoc dotycząca składni kodu źródłowego używanego do generowania diagramów.";
    }

    @Override
    public String pomocUstawieniaProgramu() {
        return "Ustawienia programu";
    }

    @Override
    public String pomocUstawieniaProgramuTresc(JezykSkladni jezyk) {
        return "Tu będzie pomoc dotycząca ustawień programu";
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
        return "Tu będzie pomoc dotycząca dodawania komunikatów do diagramu";
    }

    @Override
    public String pomocBloki() {
        return "Pliki (obszary) wydzielone";
    }

    @Override
    public String pomocBlokiTresc(JezykSkladni jezyk) {
        return "Tu będzie pomoc dotycząca dodawania bloków wydzielonych do diagramu";
    }

  
    
}
