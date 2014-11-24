/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Michal
 */
public class JezykSkladniPolski implements JezykSkladni {

    @Override
    public String atrybutNazwa() {
        return "nazwa";
    }

    @Override
    public String atrybutObiektuStartowego() {
        return "od";
    }

    @Override
    public String atrybutObiektuKoncowego() {
        return "do";
    }

    @Override
    public String atrybutSzerokosc() {
        return "szerokość_kolumny";
    }

    @Override
    public String atrybutWysokosc() {
        return "wysokość_wiersza";
    }

    @Override
    public String atrybutKlasa() {
        return "selektor";
    }

    @Override
    public String atrybutTyp() {
        return "typ";
    }

    @Override
    public String atrybutNrWiersza() {
        return "nr_wiersza";
    }

    @Override
    public String atrybutEtykieta() {
        return "etykieta";
    }

    @Override
    public String atrybutWysokoscBloku() {
        return "wysokość";
    }

    @Override
    public String atrybutSamodzielny() {
        return "samodzielny";
    }

    @Override
    public String atrybutKomentarz() {
        return "komentarz";
    }

    @Override
    public String atrybutObok() {
        return "obok";
    }

    @Override
    public String atrybutDoWiersza() {
        return "do_wiersza";
    }

    @Override
    public String atrybutId() {
        return "id";
    }

    @Override
    public String atrybutZycie() {
        return "życie";
    }

    @Override
    public String komendaDiagram() {
        return "diagram";
    }

    @Override
    public String komendaObiekt() {
        return "obiekt";
    }

    @Override
    public String komendaKomunikat() {
        return "komunikat";
    }

    @Override
    public String komendaObszar() {
        return "blok";
    }

    @Override
    public String typKlasa() {
        return "klasa";
    }

    @Override
    public String typUzytkownik() {
        return "użytkownik";
    }

    @Override
    public String typWywolanie() {
        return "wywołanie";
    }

    @Override
    public String typPowrot() {
        return "powrót";
    }

    @Override
    public String typAsynchroniczny() {
        return "asynchroniczny";
    }

    @Override
    public String typUsuwanie() {
        return "usuwanie";
    }

    @Override
    public String przykladNazwy() {
        return "\"przykład nazwy\"";
    }
    
}
