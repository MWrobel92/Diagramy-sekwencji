/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


/**
 * Klasa reprezentująca pojedynczy atrybut komendy.
 * @author Michal
 */
public class AtrybutKomendy {
    
    String identyfikator;
    String cialo;
    private boolean byloPrzycinane;
    JezykSkladni jezyk;
    int nrLinii;
    
    /**
     * Konstruktor.
     * @param identyfikator Nazwa atrybutu (to, co w tekście źródłowym znajduje się przed znakiem równości)
     * @param cialo Wartość atrybutu (to, co w tekście źródłowym znajduje się za znakiem równości)
     * @param nrLinii Nr linii w kodzie źródłowym programu, w której znajduje się atrybut (potrzebne do dokładnego rzucania wyjątków) 
     * @param jezyk Używany język składni.
     */
    public AtrybutKomendy(String identyfikator, String cialo, int nrLinii, JezykSkladni jezyk) {
        
        this.identyfikator = identyfikator;
        this.cialo = cialo;
        this.jezyk = jezyk;
        this.byloPrzycinane = false;
        this.nrLinii = nrLinii;
        
        przytnij();
        
    }
    
    /**
     * Usuwa początkowy i końcowy cudzysłów (jeśli oba istnieją),
     * a także (w tym samym przypadku) pozbywa się odwróconych ukośników
     */
    private void przytnij() {
        
        if ((cialo.startsWith("\"")) && (cialo.endsWith("\""))) {
            cialo = cialo.substring(1, cialo.length()-1);
            
            StringBuilder noweCialo = new StringBuilder();
            
            boolean ignoruj = false;
            for(char c : cialo.toCharArray()) {
                
                if (ignoruj) {
                    ignoruj = false;
                }
                else if (c == '\\') {
                    ignoruj = true;
                    continue;
                }                
                noweCialo.append(c);
            }
            cialo = noweCialo.toString();
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
    
    /** Sprawdza, czy atrybut jest atrybutem nazwy */
    public boolean atrybutNazwy () {
        return identyfikator.equals(jezyk.atrybutNazwa());
    }

    /** Sprawdza, czy atrybut jest atrybutem szerokości */
    public boolean atrybutSzerokosci() {
        return identyfikator.equals(jezyk.atrybutSzerokosc());
    }

    /** Sprawdza, czy atrybut jest atrybutem wysokości */
    public boolean atrybutWysokosci() {
        return identyfikator.equals(jezyk.atrybutWysokosc());
    }

    /** Sprawdza, czy atrybut jest atrybutem nazwy klasy (selektora) */
    public boolean atrybutNazwyKlasy() {
        return identyfikator.equals(jezyk.atrybutKlasa());
    }

    /** Sprawdza, czy atrybut jest atrybutem typu */
    public boolean atrybutTypu() {
        return identyfikator.equals(jezyk.atrybutTyp());
    }

    /** Sprawdza, czy atrybut jest atrybutem obiektu startowego */
    boolean atrybutObiektuStartowego() {
        return identyfikator.equals(jezyk.atrybutObiektuStartowego());
    }
    
    /** Sprawdza, czy atrybut jest atrybutem obiektu końcowego */
    boolean atrybutObiektuKoncowego() {
        return identyfikator.equals(jezyk.atrybutObiektuKoncowego());
    }

    /** Sprawdza, czy atrybut jest atrybutem wiersza */
    boolean atrybutWymuszeniaWiersza() {
        return identyfikator.equals(jezyk.atrybutNrWiersza());
    }

    /** Sprawdza, czy atrybut jest atrybutem etykiety */
    boolean atrybutEtykiety() {
        return identyfikator.equals(jezyk.atrybutEtykieta());
    }

    /** Sprawdza, czy atrybut jest atrybutem wysokości bloku */
    boolean atrybutWysokosciBloku() {
        return identyfikator.equals(jezyk.atrybutWysokoscBloku());
    }

    /** Sprawdza, czy atrybut jest atrybutem wymuszenia równoległości */
    boolean atrybutWymuszeniaRownoleglosci() {
        return identyfikator.equals(jezyk.atrybutSamodzielny());
    }

    /** Sprawdza, czy atrybut jest atrybutem komentarza */
    boolean atrybutKomentarza() {
        return identyfikator.equals(jezyk.atrybutKomentarz());
    }

    /** Sprawdza, czy atrybut jest atrybutem względnego położenia */
    boolean atrybutWzglednegoPolozenia() {
        return identyfikator.equals(jezyk.atrybutObok());
    }

    /** Sprawdza, czy atrybut jest drugim atrybutem względnego położenia */
    boolean drugiAtrybutWzglednegoPolozenia() {
        return identyfikator.equals(jezyk.atrybutDoWiersza());
    }

    /** Sprawdza, czy atrybut jest atrybutem identyfikatora */
    boolean atrybutIdentyfikatora() {
        return identyfikator.equals(jezyk.atrybutId());
    }

    /** Sprawdza, czy atrybut jest atrybutem życia */
    boolean atrybutZycia() {
        return identyfikator.equals(jezyk.atrybutZycie());
    }
    
}
