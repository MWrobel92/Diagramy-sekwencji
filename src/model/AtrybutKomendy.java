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
    
    public AtrybutKomendy(String identyfikator, String cialo) {
        
        this.identyfikator = identyfikator;
        this.cialo = cialo;
        this.byloPrzycinane = false;
        
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
            rezultat = new Komenda(podzieloneCialo[0], podzieloneCialo[1]);
        }
        
        return rezultat;
    }
    
    public boolean atrybutNazwy () {
        return identyfikator.equals("nazwa");
    }

    public boolean atrybutSzerokosci() {
        return identyfikator.equals("szerokość_kolumny");
    }

    public boolean atrybutWysokosci() {
        return identyfikator.equals("wysokość_wiersza");
    }

    public boolean atrybutNazwyKlasy() {
        return identyfikator.equals("klasa");
    }

    public boolean atrybutTypu() {
        return identyfikator.equals("typ");
    }

    boolean atrybutObiektuStartowego() {
        return identyfikator.equals("od");
    }
    
    boolean atrybutObiektuKoncowego() {
        return identyfikator.equals("do");
    }

    boolean atrybutWymuszeniaWiersza() {
        return identyfikator.equals("nr_wiersza");
    }

    boolean atrybutEtykiety() {
        return identyfikator.equals("etykieta");
    }

    boolean atrybutWysokosciBloku() {
        return identyfikator.equals("wysokość");
    }

    boolean atrybutWymuszeniaRownoleglosci() {
        return identyfikator.equals("samodzielny");
    }

    boolean atrybutKomentarza() {
        return identyfikator.equals("komentarz");
    }

    boolean atrybutWzglednegoPolozenia() {
        return identyfikator.equals("obok");
    }

    boolean drugiAtrybutWzglednegoPolozenia() {
        return identyfikator.equals("do_wiersza");
    }

    boolean atrybutIdentyfikatora() {
        return identyfikator.equals("id");
    }

    boolean atrybutZycia() {
        return identyfikator.equals("życie");
    }
    
}
