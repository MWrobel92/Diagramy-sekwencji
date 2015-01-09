package kontroler;

import java.util.LinkedList;
import model.Diagram;
import model.JezykInterfejsu;
import model.JezykInterfejsuPolski;
import model.JezykSkladni;
import model.JezykSkladniPolski;

/**
 * Klasa zarządzająca ustawieniami językowymi aplikacji
 * @author Michał Wróbel
 */
public class KontrolerJezykow {   
    
    private LinkedList<JezykInterfejsu> listaJezykowInterfejsu;
    private LinkedList<JezykSkladni> listaJezykowSkladni;
            
    JezykInterfejsu jezykInterfejsu;
    JezykSkladni jezykSkladni;
    
    String rezultatOstatniejKompilacji;
    Diagram wygenerowanyDiagram;
    
    /**
     * Konstruktor
     */
    public KontrolerJezykow () {
        
        listaJezykowInterfejsu = new LinkedList<>();
        listaJezykowSkladni = new LinkedList<>();
        
        // DODAWANIE WERSJI JĘZYKOWYCH
        // W przypadku dodania nowej wersji językowej ten fragment kodu należy zmodyfikować.
        
        listaJezykowInterfejsu.add(new JezykInterfejsuPolski());
        
        listaJezykowSkladni.add(new JezykSkladniPolski());
        
        // Ustawienie domyślnych języków
        jezykInterfejsu = listaJezykowInterfejsu.getFirst();
        jezykSkladni = listaJezykowSkladni.getFirst();
    }

    /**
     * Akcesor do obecnie używanego języka interfejsu.
     * @return 
     */
    public JezykInterfejsu zwrocJezykInterfejsu() {
        return jezykInterfejsu;
    }
    
    /**
     * Funkcja zmieniająca język interfejsu. 
     * @param indeksJezyka Indeks nowego języka na liście, którą można pobrać
     * za pomocą funkcji zwrocListeJezykowInterfejsu().
     */
    public void zmienJezykInterfejsu(int indeksJezyka) {
        if (indeksJezyka < listaJezykowInterfejsu.size()) {
            jezykInterfejsu = listaJezykowInterfejsu.get(indeksJezyka);
        }
    }
    
    /**
     * @return Listę dostępnych języków interfejsu.
     */
    public LinkedList<JezykInterfejsu> zwrocListeJezykowInterfejsu() {
        return listaJezykowInterfejsu;
    }
    
    /**
     * Akcesor do obecnie używanego języka skladni.
     * @return 
     */
    public JezykSkladni zwrocJezykSkladni() {
        return jezykSkladni;
    }
    
    /**
     * Funkcja zmieniająca język składni. 
     * @param indeksJezyka Indeks nowego języka na liście, którą można pobrać
     * za pomocą funkcji zwrocListeJezykowSkladni().
     */
    public void zmienJezykSkladni(int indeksJezyka) {
        if (indeksJezyka < listaJezykowSkladni.size()) {
        jezykInterfejsu = listaJezykowInterfejsu.get(indeksJezyka);
        }
    }
    
    /**
     * @return Listę dostępnych języków składni.
     */
    public LinkedList<JezykSkladni> zwrocListeJezykowSkladni() {
        return listaJezykowSkladni;
    }
    
    /**
     * @return Przukładową komendę dodającą obiekt do diagramu. 
     */
    public String przykladowyUczestnik() {
        StringBuilder komenda = new StringBuilder("\n");
        komenda.append(jezykSkladni.komendaObiekt());
        komenda.append('{');
        komenda.append(jezykSkladni.atrybutNazwa());
        komenda.append('=');
        komenda.append(jezykSkladni.przykladNazwy());
        komenda.append(' ');
        komenda.append(jezykSkladni.atrybutTyp());
        komenda.append('=');
        komenda.append(jezykSkladni.typKlasa());
        komenda.append("}\n");        
        return komenda.toString();
    }
    
    /**
     * @return Przukładową komendę dodającą komunikat diagramu. 
     */
    public String przykladowyKomunikat() {
        StringBuilder komenda = new StringBuilder("\n");
        komenda.append(jezykSkladni.komendaKomunikat());
        komenda.append('{');
        komenda.append(jezykSkladni.atrybutNazwa());
        komenda.append('=');
        komenda.append(jezykSkladni.przykladNazwy());
        komenda.append(' ');
        komenda.append(jezykSkladni.atrybutTyp());
        komenda.append('=');
        komenda.append(jezykSkladni.typWywolanie());
        komenda.append(' ');
        komenda.append(jezykSkladni.atrybutObiektuStartowego());
        komenda.append('=');
        komenda.append(jezykSkladni.przykladNazwy());
        komenda.append(' ');
        komenda.append(jezykSkladni.atrybutObiektuKoncowego());
        komenda.append('=');
        komenda.append(jezykSkladni.przykladNazwy());
        komenda.append("}\n");         
        return komenda.toString();
    }
    
    /**
     * @return Przukładową komendę dodającą blok wydzielony do diagramu. 
     */
    public String przykladowyObszar() {
        StringBuilder komenda = new StringBuilder("\n");
        komenda.append(jezykSkladni.komendaObszar());
        komenda.append('{');
        komenda.append(jezykSkladni.atrybutNazwa());
        komenda.append('=');
        komenda.append(jezykSkladni.przykladNazwy());
        komenda.append(' ');
        komenda.append(jezykSkladni.atrybutObiektuStartowego());
        komenda.append('=');
        komenda.append(jezykSkladni.przykladNazwy());
        komenda.append(' ');
        komenda.append(jezykSkladni.atrybutObiektuKoncowego());
        komenda.append('=');
        komenda.append(jezykSkladni.przykladNazwy());
        komenda.append("}\n");         
        return komenda.toString();
    }
}
