/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


/**
 *
 * @author Michal
 */
public class AtrybutKomendy {
    
    String identyfikator;
    String cialo;
    private boolean byloPrzycinane;
    JezykSkladni jezyk;
    int nrLinii;
    
    public AtrybutKomendy(String identyfikator, String cialo, int nrLinii, JezykSkladni jezyk) {
        
        this.identyfikator = identyfikator;
        this.cialo = cialo;
        this.jezyk = jezyk;
        this.byloPrzycinane = false;
        this.nrLinii = nrLinii;
        
        przytnij();
        
    }
    
    private void przytnij() {
        
        if ((cialo.startsWith("\"")) && (cialo.endsWith("\""))) {
            cialo = cialo.substring(1, cialo.length()-1);
            byloPrzycinane = true;
        }
    }
    
    /**
     * Funcja sprawdza, czy jej ciało może być zinterpretowane jako komenda podrzędna.
     * @return Komendę podrzędną lub null.
     */
    public Komenda sprawdzKomendePodrzedna () {
        
        Komenda rezultat = null;
        int dl = cialo.length();
        
        if ( !byloPrzycinane && (dl > 3) && (cialo.charAt(dl-1) == '}') && (cialo.indexOf('{') > 0) ) {
            String[] podzieloneCialo = cialo.substring(0, dl-1).split("[{]", 2);
            rezultat = new Komenda(podzieloneCialo[0], podzieloneCialo[1], nrLinii, jezyk);
        }
        
        return rezultat;
    }
    
    public boolean atrybutNazwy () {
        return identyfikator.equals(jezyk.atrybutNazwa());
    }

    public boolean atrybutSzerokosci() {
        return identyfikator.equals(jezyk.atrybutSzerokosc());
    }

    public boolean atrybutWysokosci() {
        return identyfikator.equals(jezyk.atrybutWysokosc());
    }

    public boolean atrybutNazwyKlasy() {
        return identyfikator.equals(jezyk.atrybutKlasa());
    }

    public boolean atrybutTypu() {
        return identyfikator.equals(jezyk.atrybutTyp());
    }

    boolean atrybutObiektuStartowego() {
        return identyfikator.equals(jezyk.atrybutObiektuStartowego());
    }
    
    boolean atrybutObiektuKoncowego() {
        return identyfikator.equals(jezyk.atrybutObiektuKoncowego());
    }

    boolean atrybutWymuszeniaWiersza() {
        return identyfikator.equals(jezyk.atrybutNrWiersza());
    }

    boolean atrybutEtykiety() {
        return identyfikator.equals(jezyk.atrybutEtykieta());
    }

    boolean atrybutWysokosciBloku() {
        return identyfikator.equals(jezyk.atrybutWysokoscBloku());
    }

    boolean atrybutWymuszeniaRownoleglosci() {
        return identyfikator.equals(jezyk.atrybutSamodzielny());
    }

    boolean atrybutKomentarza() {
        return identyfikator.equals(jezyk.atrybutKomentarz());
    }

    boolean atrybutWzglednegoPolozenia() {
        return identyfikator.equals(jezyk.atrybutObok());
    }

    boolean drugiAtrybutWzglednegoPolozenia() {
        return identyfikator.equals(jezyk.atrybutDoWiersza());
    }

    boolean atrybutIdentyfikatora() {
        return identyfikator.equals(jezyk.atrybutId());
    }

    boolean atrybutZycia() {
        return identyfikator.equals(jezyk.atrybutZycie());
    }
    
}
