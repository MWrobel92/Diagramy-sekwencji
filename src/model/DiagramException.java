package model;

/**
 * Wyjątek rzucany w przypadku pojawienia się czegoś, co nie jest mozliwe do wygenerowania
 * @author Michal
 */
public class DiagramException extends Exception {
    
    public String opisBledu;
    
    public DiagramException(String opisBledu) {
        this.opisBledu = opisBledu;
    }
}
