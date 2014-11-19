package test;

import java.util.List;
import model.AnalizatorTekstu;
import model.DiagramException;
import model.JezykInterfejsuPolski;
import model.JezykSkladniPolski;
import model.Komenda;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 *
 * @author Michal
 */
public class TestAnalizyTekstu {
    
    public List<Komenda> przygotujKomendy(String tekst) throws DiagramException {
        AnalizatorTekstu analizatorTekstu = new AnalizatorTekstu(new JezykSkladniPolski());
        return analizatorTekstu.wyodrebnijKomendy(tekst);
    }
    
    @Test
    public void przeanalizujPrawidlowyTekst() {
                
        try {
            String tekst = "obiekt{nazwa=hgfhgtyp={{}}} \nkomunikat{tekst=\"aa\\\"a{}\"}obszar{typ=\"użytkownik\"}";
            List<Komenda> komendy = przygotujKomendy(tekst);
            for(Komenda k : komendy) {
                System.out.print(k.toString());
            }
            
            assertEquals("Nieprawidłowa liczba komend.", 3, komendy.size());
    
        }
        catch (DiagramException ex) {
            fail("Złapano wyjątek:" + ex.wypiszBlad(new JezykInterfejsuPolski()));
        }
        
    }
    
    @Test
    public void przeanalizujBledneTeksty() {
                
        try {
            String tekst = "obiekt{typ={{}}} \nkomunikat{tekst=\"aa\\\"a{}\"obszar{typ=\"użytkownik\"}";
            przygotujKomendy(tekst);
            fail("Nie rzucono wyjątkiem w przypadku niedomkniętej klamry.");    
        }
        catch (DiagramException ex) { }      
    
        try {
            String tekst = "obiekt{typ={{}}} \nkomunikat{tekst=\"aa\\\"a{}}obszar{typ=\"użytkownik\"}";
            przygotujKomendy(tekst);
            fail("Nie rzucono wyjątkiem w przypadku niedomknięgo czudzysłowu.");    
        }
        catch (DiagramException ex) { }
        
    } 
    
    @Test
    public void utworzDiagramNaPostrawieTekstu() {
        try {
            AnalizatorTekstu analizatorTekstu = new AnalizatorTekstu(new JezykSkladniPolski());
            analizatorTekstu.przygotujDiagram("obiekt{nazwa=ssad} \nkomunikat{nazwa=\"dvfdv\" }obszar{nazwa=fsdf od=ssad do=ssad typ=\"użytkownik\"}");
        } catch (DiagramException ex) {
            fail("Przechwycono wyjątek: " + ex.wypiszBlad(new JezykInterfejsuPolski()));
        }
        
    }
}
