package kontroler;

import model.AnalizatorTekstu;
import model.Diagram;
import model.DiagramException;

/**
 * Klasa pośrednicząca między klasami generującymi diagram a widokiem.
 * @author Michał Wróbel
 */
public class KontrolerGenerowania {
    
    KontrolerJezykow kontrolerJezyka;    
    String rezultatOstatniejKompilacji;
    Diagram wygenerowanyDiagram;
    
    /**
     * Konstruktor
     * @param kontrolerJezyka 
     */
    public KontrolerGenerowania(KontrolerJezykow kontrolerJezyka) {
        this.kontrolerJezyka = kontrolerJezyka;
    }
    
    /**
     * Generuje diagram na podstawie tekstu źródłowego. W przypadku poprawnego wygenerowania diagramu
     * można pobrać go przy pomocy funkcji pobierzOstatnioWygenerowanyDiagram().
     * @param tekstZrodlowy
     * @return Informację o poprawności tekstu źródłowego.
     */
    public boolean generujDiagram(String tekstZrodlowy) {
                                 
        boolean poprawnoscKompilacji = false;
                
        try {
            AnalizatorTekstu analizator = new AnalizatorTekstu(kontrolerJezyka.zwrocJezykSkladni());
            wygenerowanyDiagram = analizator.przygotujDiagram(tekstZrodlowy);                    
            poprawnoscKompilacji = true;
            rezultatOstatniejKompilacji = kontrolerJezyka.zwrocJezykInterfejsu().komunikatPoprawnaKompilacja();
        } catch (DiagramException ex) {
            rezultatOstatniejKompilacji = ex.wypiszBlad(kontrolerJezyka.zwrocJezykInterfejsu());                       
        }
            
        return poprawnoscKompilacji;
    }    
    
    public Diagram pobierzOstatnioWygenerowanyDiagram() {
        return wygenerowanyDiagram;
    }
    
    public String pobierzOstatniRezultat() {
        return rezultatOstatniejKompilacji;
    }
    
}
