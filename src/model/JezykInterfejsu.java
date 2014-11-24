package model;

/**
 * Interfejs odpowiedzialny za język napisów na kontrolkach, komunikatach itp.
 * @author Michał Wróbel
 */
public interface JezykInterfejsu {
    
    public String menuPlik ();
    public String menuEdycja ();
    public String menuDiagram ();
    public String menuPomoc();
    public String menuPlikWczytaj();
    public String menuPlikZapisz();
    public String menuPlikZapiszJako();
    public String menuEdycjaCofnij();
    public String menuEdycjaPonow();
    public String menuEdycjaWstaw();
    public String menuEdycjaWstawAktora();
    public String menuEdycjaWstawKomunikat();
    public String menuEdycjaWstawObszarWydzielony();
    public String menuOpcje();
    public String menuOpcjeJezyk();
    public String menuOpcjeAutoodswiezanie();
    public String menuDiagramGeneruj();
    public String menuDiagramZapisz();
    public String menuPomocPomoc();
    public String menuPomocOProgramie();
    
    public String kontrolkaKodZrodlowy();
    public String kontrolkaKonsola();
    public String kontrolkaJezykInterfejsu();
    public String kontrolkaJezykSkladni();
    
    public String przyciskTak();
    public String przyciskNie();
    public String przyciskAnuluj();
    
    public String oknoNiezapisaneNaglowek();
    public String onkoNiezapisaneTresc();
    public String oknoBladWczytania();
    public String oknoBladZapisu();
    public String oknoBladKompilacji();

    public String komunikatPoprawnaKompilacja();
    
    public String bladNaglowek();
    public String bladSkladnia();
    public String bladNieistniejacaKomenda();
    public String bladDwukrotnaDefinicja();
    public String bladWymaganaLiczba();
    public String bladOdnaleziono1();
    public String bladOdnaleziono2();
    public String bladPonizej50();
    public String bladPowtorzonyIdentyfikatorObiektu();
    public String bladNieistniejacyTypObiektu();
    public String bladNieistniejacyTypKomunikatu();
    public String bladObiektNieutworzony1();
    public String bladObiektNieutworzony2();
    public String bladObiektNieutworzony3();
    public String bladObiektUsuniety();
    public String bladPowtorzonyIdentyfikatorElementuPoziomego();
    public String bladDwukrotneUsuniecieObiektu();
    public String bladObiektNiefunkcjonujacy();
    public String bladOdwroconyObszar();
    public String bladNiedodatniaWysokoscBloku();
    public String bladBrakObiektu();
    public String bladBrakKomunikatu();
    public String bladWartoscNieliczbowa();
    public String bladWartoscUjemna();
    public String bladNieoczekiwanyAtrybut();
    public String bladNiezdefiniowanyAtrybutObowiazkowy();
    public String bladBlednaLiczbaPunktow();
    public String bladLinia();

    public String pomocOProgramie();
    public String pomocOgolna();
    public String pomocOgolnaTresc(JezykSkladni jezyk);
    public String pomocPodstawoweInformacje();
    public String pomocPodstawoweInformacjeTresc(JezykSkladni jezyk);
    public String pomocSkladnia();
    public String pomocSkladniaTresc(JezykSkladni jezyk);
    public String pomocUstawieniaProgramu();
    public String pomocUstawieniaProgramuTresc(JezykSkladni jezyk);
    public String pomocObiekty();
    public String pomocObiektyTresc(JezykSkladni jezyk);
    public String pomocKomunikaty();
    public String pomocKomunikatyTresc(JezykSkladni jezyk);
    public String pomocBloki();
    public String pomocBlokiTresc(JezykSkladni jezyk);

    

}
