package kontroler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Klasa zarządzająca zapisem danych do pliku.
 * @author Michał Wróbel
 */
public class KontrolerPlikow {
       
    private File aktualnyPlik;
    
    /**
     * Konstruktor
     */
    public KontrolerPlikow() {
        aktualnyPlik = null;
    }
    
    /**
     * Sprawdza, czy kontroler posiada zapamiętany plik
     * @return 
     */
    public boolean jestZapamietanyPlik () {        
        boolean rezultat = true;
        if (aktualnyPlik == null) {
            rezultat = false;
        }
        return rezultat;
    }
    
    /**
     * Zapamiętuje plik, do którego mają być zapisywane/odczytywane dane.
     * Ta funkcja musi być wywołana przed operacjami odczytu lub zapisu plików.
     * @param plik Plik do zapamiętania
     */
    public void otworzPlik(File plik) {
        aktualnyPlik = plik;
    }
    
    /**
     * Zapisuje podany tekst do pliku otwartego przy pomocy funkcji otworzPlik.
     * @param tekstDoZapisu
     * @return True, jeśli plik został prawidłowo zapisany
     */
    public boolean zapiszPlik(String tekstDoZapisu) {
        
        boolean plikZostalZapisany = false;
        
        if (aktualnyPlik != null) {    
            try {
                BufferedWriter strumienWyjsciowy = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(aktualnyPlik), "UTF-8")
                        );         

                strumienWyjsciowy.write(tekstDoZapisu);
                strumienWyjsciowy.close();

                plikZostalZapisany = true;
            }
            catch (IOException ex) {
            }        
        }
        
        return plikZostalZapisany;
    }
    
    /**
     * Wczytuje tekst z pliku otwartego przy pomocy funkcji otworzPlik.
     * @return Treść otwartego pliku lub null, jeśli wystąpiły błędy.
     */
    public String wczytajPlik () {
        
        String tekstDoZwrotu = null;
        
        try {
            BufferedReader strumienWejsciowy = new BufferedReader(
                new InputStreamReader(new FileInputStream(aktualnyPlik), "UTF-8")
                );  
                
            StringBuilder wczytanyTekst = new StringBuilder();
            boolean pierwszaLinia = true;
      
            while (true) {
                String liniaTekstu = strumienWejsciowy.readLine();                    
                if (liniaTekstu != null) {
                        
                    // Przejście do nowej linii (z wyjątkiem pierwszej)
                    if (pierwszaLinia) {
                        pierwszaLinia = false;
                    }
                    else {
                        wczytanyTekst.append("\n");
                    }
                        
                    // Właściwe wczytanie linii
                    wczytanyTekst.append(liniaTekstu);
                        
                }
                else {
                    break;
                }                    
            }
                
            strumienWejsciowy.close();
            tekstDoZwrotu = wczytanyTekst.toString();
            
        }
        catch (IOException ex) {
        }
        
        return tekstDoZwrotu;    
    }
}
