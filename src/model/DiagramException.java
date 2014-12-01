package model;

/**
 * Wyjątek rzucany w przypadku błędu w składni kodu źródłowego.
 * @author Micha
 */
public class DiagramException extends Exception {
    
    public enum TypBledu {
        SKLADNIA, // opis1 - spodziewany znak
        NIEISTNIEJACA_KOMENDA, // opis1 - identyfikator błędnej komendy
        DWUKROTNA_DEFINICJA, // opis1 - identyfikator powtórzonego atrybutu
        WYMAGANA_LICZBA, // opis1 - identyfikator atrybutu, opis2 - błędna treść
        PONIZEJ_50, // opis1 - identyfikator atrybutu, opis2 - błędna treść
        POWTORZONY_IDENTYFIKATOR_OBIEKTU, //opis1 - nazwa powtórzonego identyfikatora
        POWTORZONY_IDENTYFIKATOR_ELEMENTU_POZIOMEGO, //opis1 - nazwa powtórzonego identyfikatora
        NIEISTNIEJACY_TYP_OBIEKTU, // opis1 - nazwa błędnego typu
        NIEISTNIEJACY_TYP_KOMUNIKATU, // opis1 - nazwa błędnego typu
        OBIEKT_NIEUTWORZONY, // opis1 - nazwa komunikatu, opis2 - nazwa obiektu
        OBIEKT_USUNIETY, // opis1 - nazwa komunikatu, opis2 - nazwa obiektu  
        OBIEKT_NIEFUNKCJONUJACY, // opis1 - nazwa obiektu
        DWUKROTNE_USUNIECIE_OBIEKTU, // opis1 - nazwa obiektu
        ODWROCONY_OBSZAR, // opis1 - nazwa obszaru wydzielonego
        NIEDODATNIA_WYSOKOSC_BLOKU, // opis1 - nazwa obszaru wydzielonego
        BRAK_OBIEKTU,  // opis1 - nazwa brakujacego obiektu
        BRAK_KOMUNIKATU, // opis1 - nazwa brakujacego komunikatu
        WARTOSC_NIELICZBOWA, // opis1 - nazwa atrybutu, opis2 - błędna wartość
        WARTOSC_UJEMNA, // opis1 - nazwa atrybutu, opis2 - błędna wartość
        NIEOCZEKIWANY_ATRYBUT, // opis1 - nazwa błędnego atrybutu
        NIEZDEFINIOWANY_ATRYBUT_OBOWIAZKOWY, // opis1 - nazwa brakującego atrybutu
        BLEDNA_LICZBA_PUNKTOW // opis1 - nazwa obiektu
        
    }
    
    public TypBledu typBledu;
    public String opis1;
    public String opis2;
    public int nrLinii;
    
    
    /**
     * Konstruktor z jednym polem opisu.
     * @param typ
     * @param nrLinii
     * @param opis1 
     */
    public DiagramException(TypBledu typ, int nrLinii, String opis1) {
        this.typBledu = typ;
        this.opis1 = opis1;
        this.opis2 = "";
        this.nrLinii = nrLinii;
    }
    
    /**
     * KOnstruktor z dwoma polami opisu.
     * @param typ
     * @param nrLinii
     * @param opis1
     * @param opis2 
     */
    public DiagramException(TypBledu typ, int nrLinii, String opis1, String opis2) {
        this.typBledu = typ;
        this.opis1 = opis1;
        this.opis2 = opis2;
        this.nrLinii = nrLinii;
    }
    
    /**
     * Funkcja wypisująca komunikat o błędzie.
     * @param jezyk Język interfejsu, w którym ma zostać wygenerowany komunikat.
     * @return 
     */
    public String wypiszBlad (JezykInterfejsu jezyk) {
        
        StringBuilder komunikatBledu = new StringBuilder(jezyk.bladNaglowek());
        komunikatBledu.append(" (");
        komunikatBledu.append(jezyk.bladLinia());
        komunikatBledu.append(' ');
        komunikatBledu.append(nrLinii);
        komunikatBledu.append("): ");
        switch (typBledu.toString()) {
            
            case "SKLADNIA" :
                komunikatBledu.append(jezyk.bladSkladnia());
                komunikatBledu.append(opis1);
                break;
            case "NIEISTNIEJACA_KOMENDA" :
                komunikatBledu.append(jezyk.bladNieistniejacaKomenda());
                komunikatBledu.append(opis1);
                break;
            case "DWUKROTNA_DEFINICJA" :
                komunikatBledu.append(jezyk.bladDwukrotnaDefinicja());
                komunikatBledu.append(opis1);
                break;
            case "WYMAGANA_LICZBA" :
                komunikatBledu.append(jezyk.bladWymaganaLiczba());                
                komunikatBledu.append(opis1);
                komunikatBledu.append(jezyk.bladOdnaleziono1()); 
                komunikatBledu.append(opis2);
                komunikatBledu.append(jezyk.bladOdnaleziono2()); 
                break;
            case "PONIZEJ_50" :
                komunikatBledu.append(jezyk.bladPonizej50());                
                komunikatBledu.append(opis1);
                komunikatBledu.append(jezyk.bladOdnaleziono1()); 
                komunikatBledu.append(opis2);
                komunikatBledu.append(jezyk.bladOdnaleziono2()); 
                break;
            case "POWTORZONY_IDENTYFIKATOR_OBIEKTU" :
                komunikatBledu.append(jezyk.bladPowtorzonyIdentyfikatorObiektu());
                komunikatBledu.append(opis1);
                break;
            case "POWTORZONY_IDENTYFIKATOR_ELEMENTU_POZIOMEGO" :
                komunikatBledu.append(jezyk.bladPowtorzonyIdentyfikatorElementuPoziomego());
                komunikatBledu.append(opis1);
                break;
            case "NIEISTNIEJACY_TYP_OBIEKTU" :
                komunikatBledu.append(jezyk.bladNieistniejacyTypObiektu());
                komunikatBledu.append(opis1);
                break;
            case "NIEISTNIEJACY_TYP_KOMUNIKATU" :
                komunikatBledu.append(jezyk.bladNieistniejacyTypKomunikatu());
                komunikatBledu.append(opis1);
                break;
            case "OBIEKT_NIEUTWORZONY" :
                komunikatBledu.append(jezyk.bladObiektNieutworzony1());
                komunikatBledu.append(opis1);
                komunikatBledu.append(jezyk.bladObiektNieutworzony2()); 
                komunikatBledu.append(opis2);
                komunikatBledu.append(jezyk.bladObiektNieutworzony3()); 
                break;
            case "OBIEKT_USUNIETY" :
                komunikatBledu.append(jezyk.bladObiektNieutworzony1());
                komunikatBledu.append(opis1);
                komunikatBledu.append(jezyk.bladObiektNieutworzony2()); 
                komunikatBledu.append(opis2);
                komunikatBledu.append(jezyk.bladObiektUsuniety()); 
                break;
            case "OBIEKT_NIEFUNKCJONUJACY" :
                komunikatBledu.append(jezyk.bladObiektNiefunkcjonujacy());
                komunikatBledu.append(opis1);
                break;
            case "DWUKROTNE_USUNIECIE_OBIEKTU" :
                komunikatBledu.append(jezyk.bladDwukrotneUsuniecieObiektu());
                komunikatBledu.append(opis1);
                break;
            case "ODWROCONY_OBSZAR" :
                komunikatBledu.append(jezyk.bladOdwroconyObszar());
                komunikatBledu.append(opis1);
                break;
            case "NIEDODATNIA_WYSOKOSC_BLOKU" :
                komunikatBledu.append(jezyk.bladNiedodatniaWysokoscBloku());
                komunikatBledu.append(opis1);
                break;
            case "BRAK_OBIEKTU" :
                komunikatBledu.append(jezyk.bladBrakObiektu());
                komunikatBledu.append(opis1);
                break;
            case "BRAK_KOMUNIKATU" :
                komunikatBledu.append(jezyk.bladBrakKomunikatu());
                komunikatBledu.append(opis1);
                break;
            case "WARTOSC_NIELICZBOWA" :
                komunikatBledu.append(jezyk.bladWartoscNieliczbowa());
                komunikatBledu.append(opis1);
                komunikatBledu.append(" - ");
                komunikatBledu.append(opis2);
                break;
            case "WARTOSC_UJEMNA" :
                komunikatBledu.append(jezyk.bladWartoscUjemna());
                komunikatBledu.append(opis1);
                komunikatBledu.append(" - ");
                komunikatBledu.append(opis2);
                break;
            case "NIEOCZEKIWANY_ATRYBUT" :
                komunikatBledu.append(jezyk.bladNieoczekiwanyAtrybut());
                komunikatBledu.append(opis1);
                break;
            case "NIEZDEFINIOWANY_ATRYBUT_OBOWIAZKOWY" :
                komunikatBledu.append(jezyk.bladNiezdefiniowanyAtrybutObowiazkowy());
                komunikatBledu.append(opis1);
                komunikatBledu.append(" - ");
                komunikatBledu.append(opis2);
                break;
            case "BLEDNA_LICZBA_PUNKTOW" :
                komunikatBledu.append(jezyk.bladBlednaLiczbaPunktow());
                break;
                
        }
        
        komunikatBledu.append(".\n");
        return komunikatBledu.toString();
    }
}
