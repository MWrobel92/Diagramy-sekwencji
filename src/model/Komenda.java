package model;

import java.util.LinkedList;

/**
 * Klasa reprezentująca pojedynczą komendę wyodrębnioną z pliku wejściowego.
 * @author Michal
 */
public class Komenda {
    
    String identyfikator;
    String cialo;
    
    public Komenda (String identyfikator, String cialo) {
        this.identyfikator = identyfikator;
        this.cialo = cialo;
    }
    
    @Override
    public String toString() {
        return "Identyfikator: " + identyfikator + " Ciało: " + cialo + "\n";
    }
    
    public boolean dotyczyDiagramu() {
        return identyfikator.equals("diagram");
    }
    
    public boolean dotyczyObiektu() {
        return identyfikator.equals("obiekt");
    }
    
    public boolean dotyczyKomunikatu() {
        return identyfikator.equals("komunikat");
    }
    
    public boolean dotyczyObszaru() {
        return identyfikator.equals("obszar");
    }
    
    public LinkedList<AtrybutKomendy> przygotujListeAtrybutow () {
        
        LinkedList<AtrybutKomendy> atrybuty = new LinkedList<>();
        
        StringBuilder bufor = new StringBuilder();        
        String tymczasowyIdentyfikator = "";
        
        short liczbaKlamerZagniezdzonych = 0;
                
        boolean wnetrzeIdentyfikatora = false;
        boolean wnetrzeTresci = false;
        boolean ignorujKolejnyZnak = false;
        boolean wnetrzeNapisu = false;
        
        for(char c : cialo.toCharArray()) {
            
            if (ignorujKolejnyZnak) {
                bufor.append(c);
                ignorujKolejnyZnak = false;
                continue;
            }
            
            if (wnetrzeIdentyfikatora) {
                
                if (Character.isWhitespace(c)) {
                    //Zapisujemy identyfikator z pustym ciałem
                    atrybuty.add(new AtrybutKomendy(bufor.toString(), ""));
                    bufor = new StringBuilder();
                    wnetrzeIdentyfikatora = false;
                }
                else if (c == '=') {
                    tymczasowyIdentyfikator = bufor.toString();
                    bufor = new StringBuilder();
                    wnetrzeIdentyfikatora = false;
                    wnetrzeTresci = true;
                }
                else {
                    bufor.append(c);
                }
            }
            else if (wnetrzeTresci) {
                
                if (wnetrzeNapisu) {
                    if (c == '\\') {
                        // Odwrócony ukośnik - kolejny znak należy zignorować
                        ignorujKolejnyZnak = true;
                    }
                    else if (c == '"') {
                        // Cudzysłów zamykający - kończymy napis
                        wnetrzeNapisu = false;
                    }
                    // Jeśli ani jedno, ani drugie - nie robimy nic                    
                }
                else {                    
                    if (c == '"') {
                        // Cudzysłów otwierający - rozpoczynamy napis
                        wnetrzeNapisu = true;
                    }
                    else if (c == '{') {
                        // Znaleźliśmy klamrę zagnieżdżoną
                        ++liczbaKlamerZagniezdzonych;
                    } 
                    else if (c == '}') {                   
                        //Opuszczamy klamrę zagnieżdżoną
                        --liczbaKlamerZagniezdzonych;
                    } 
                    else if ((bufor.length() != 0)&&(Character.isWhitespace(c))&&(liczbaKlamerZagniezdzonych==0)) {
                        //KONIEC ATRYBUTU
                        wnetrzeTresci = false;
                        atrybuty.add(new AtrybutKomendy(tymczasowyIdentyfikator, bufor.toString()));
                        bufor = new StringBuilder();
                        continue;
                    } 
                }                  
                bufor.append(c);
            }
            else if (!Character.isWhitespace(c)) {
                bufor.append(c);
                wnetrzeIdentyfikatora = true;
            }
            
        }
        
        if (wnetrzeTresci) {
            atrybuty.add(new AtrybutKomendy(tymczasowyIdentyfikator, bufor.toString()));
        }
        
        return atrybuty;
        
    }
    
}
