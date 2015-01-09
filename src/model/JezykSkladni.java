package model;

/**
 * Interfejs odpowiedzialny za składnię tekstu źródłowego - nazwy koment, atrybutów itp.
 * @author Michał Wróbel
 */
public interface JezykSkladni {

    public String nazwaJezyka();
    
    public String atrybutNazwa();
    public String atrybutObiektuStartowego();
    public String atrybutObiektuKoncowego();
    public String atrybutSzerokosc();
    public String atrybutWysokosc();
    public String atrybutKlasa();
    public String atrybutTyp();
    public String atrybutNrWiersza();
    public String atrybutEtykieta();
    public String atrybutWysokoscBloku();
    public String atrybutSamodzielny();
    public String atrybutKomentarz();
    public String atrybutObok();
    public String atrybutDoWiersza();
    public String atrybutId();
    public String atrybutZycie();
    public String komendaDiagram();
    public String komendaObiekt();
    public String komendaKomunikat();
    public String komendaObszar();
    
    public String typKlasa();
    public String typUzytkownik();
    public String typWywolanie();
    public String typPowrot();
    public String typAsynchroniczny();
    public String typUsuwanie();

    public String przykladNazwy();
    
}
