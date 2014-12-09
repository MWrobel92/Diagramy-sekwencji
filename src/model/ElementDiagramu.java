package model;

/**
 * Klasa abstrakcyjna przedstawiająca dowolny element diagramu.
 * @author Michał Wróbel
 */
public abstract class ElementDiagramu {
    
    protected String nazwa;    
    private String identyfikator;
    
    ElementDiagramu () {
        identyfikator = null;
    }
    
    /**
     * Akcesor do nazwy diagramu
     * @return 
     */
    public String nazwa() {
        return nazwa.substring(0);
    }
    
    /**
     * Funkcja zwracająca identifikator obiektu, a jeśli nie został on zdefiniowany, jago nazwę.
     * @return 
     */
    public String id() {
        
        if (identyfikator == null) {
            return nazwa;
        }
        else {
            return identyfikator;
        }
        
    }
    
    void ustawId(String identyfikator) {
        this.identyfikator = identyfikator;
    }
    
    public boolean identyfikatorMocny () {
        return identyfikator != null;
    }
}
