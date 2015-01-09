package widok;

import kontroler.KontrolerJezykow;
import widok.OknoProgramu;

/**
 * Klasa, która ma tylko uruchomić okno programu.
 * @author Michał Wróbel
 */
public class KlasaStartowa {
    
    public static void main (String args[]) {
        KontrolerJezykow kontroler = new KontrolerJezykow();
        OknoProgramu okno = new OknoProgramu(kontroler);
        okno.start();
    }
    
}
