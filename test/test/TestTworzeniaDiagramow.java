package test;



import model.Diagram;
import model.DiagramException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Klasa testująca sposób tworzenia diagramów.
 * @author Michal
 */
public class TestTworzeniaDiagramow {
    
    public static Diagram utworzPrzykladowyDiagram() throws DiagramException {
        
        Diagram diagram = new Diagram();
        diagram.ustawNazwe("Diagram testowy");
        diagram.dodajObiekt("Pierwszy obiekt", "", "Użytkownik");
        diagram.dodajObiekt("Drugi obiekt", "Cośtam", "Klasa");
        diagram.dodajObiekt("Trzeci obiekt", "Cośtam", "Klasa");
        diagram.dodajKomunikat("Wejście", "Wywołanie",  null, "Pierwszy obiekt", null);
        diagram.dodajKomunikat("Tworzenie drugiego obiektu", "Wywołanie", "Pierwszy obiekt", "Drugi obiekt", null);
        diagram.dodajKomunikat("Informacja zwrotna", "Asynchroniczny", "Drugi obiekt", "Pierwszy obiekt", null);
        diagram.dodajKomunikat("Tworzenie trzeciego obiektu", "POWRÓT", "Drugi obiekt", "Trzeci obiekt", 2);
        diagram.dodajObszarWydzielony("alt", "Skomplikowane operacje", "Pierwszy obiekt", "Drugi obiekt", null, null, false);
        diagram.dodajObiektTworzonyKomunikatem("Obiekcik", "Aaa", "Tworzenie", "Trzeci obiekt", null);
        diagram.dodajKomunikat("Powrót z trzeciego obiektu", "asynchroniczny", "Trzeci obiekt", "Drugi obiekt", null);
        diagram.dodajObszarWydzielony("loop", "Drugi obiekt", "Drugi obiekt", 7);
        diagram.dodajKomunikat("Pętelka", "", "Pierwszy obiekt", "Pierwszy obiekt", null);
        diagram.dodajKomunikat("Usunięcie", "USUWANIE", "Drugi obiekt", "Obiekcik", null);
        diagram.dodajKomunikat("Powrót z drugiego obiektu", "powrót", "Drugi obiekt", "Pierwszy obiekt", null);
        diagram.dodajKomunikat("Wyjście", "Wywołanie", "Pierwszy obiekt", null,  null);
        return diagram;

    }
    
    @Test
    public void utworzPrzykladowyDiagramTest() {
                
        try {
            Diagram diagram = utworzPrzykladowyDiagram();
        }
        catch (DiagramException ex) {
            fail("Wyjątek w sytuacji, której być nie powinno.");
        }
        
    }
    
    @Test
    public void blednyDiagramTest() {
           
        Diagram diagram = new Diagram();
        
        //Rzeczy poprawne
        try {
            diagram.dodajObiekt("Pierwszy obiekt", "", "Użytkownik");
            diagram.dodajObiekt("Drugi obiekt", "Cośtam", "Klasa");
        }
        catch (DiagramException ex) {
            fail("Wyjątek w sytuacji, której być nie powinno.");
        }
        
        //Rzeczy błędne
        try {
            diagram.dodajObiekt("Pierwszy obiekt", "Selektor", "Samochód");
            fail("Nie reaguje na zły typ obiektu.");
        }
        catch (DiagramException ex) { }
        
        try {
            diagram.dodajKomunikat("Komunikat", "", "Błędny obiekt", "Drugi obiekt", null);
            fail("Nie reaguje na nieistniejący obiekt w komunikacie.");
        }
        catch (DiagramException ex) { }
        
        try {
            diagram.dodajKomunikat("Komunikat", "Qwerty", "Błędny obiekt", "Drugi obiekt", null);
            fail("Nie reaguje na zły typ komunikatu.");
        }
        catch (DiagramException ex) { }
        
        try {
            diagram.dodajObszarWydzielony("Obszar", "Pierwszy obiekt", "Błędny obiekt", 1);
            fail("Nie reaguje na nieistniejący obiekt w obszarze.");
        }
        catch (DiagramException ex) { }
        
        try {
            diagram.dodajObszarWydzielony("Obszar", "Kom", "Pierwszy obiekt", "Drugi obiekt", 2, 0, true);
            fail("Nie reaguje na nieprawidłowy rozmiar obiektu.");
        }
        catch (DiagramException ex) { }        
        
    }
    
}
