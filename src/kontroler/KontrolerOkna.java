/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import model.AnalizatorTekstu;
import model.Diagram;
import model.DiagramException;
import widok.PanelDiagramu;

/**
 *
 * @author Michal
 */
public class KontrolerOkna implements ActionListener, CaretListener {
    
    //Deklaracje kontrolek, do których listener musi mieć dostęp.
    private JTextArea poleTekstowe;
    private PanelDiagramu panelDiagramu;
    private JComponent przyciskCofania;
    private JComponent przyciskPonawiania;
    
    private OknoProgramu elementNadrzedny;
    
    //Deklaracje list z historią zmian
    private LinkedList<String> listaCofania;
    private LinkedList<String> listaPonawiania;
    private String poprzedniTekst;
    
   
    /**
     * Konstruktor ustawiający wszystkie komponenty, do których listener musi mieć dostęp.
     * Widoczność komponentów cofania i ponawiania zostaje ustawiona automatycznie.
     * @param poleTekstowe
     * @param panelDiagramu
     * @param przyciskCofnij
     * @param przyciskPonow 
     */
    public KontrolerOkna (OknoProgramu elementNadrzedny, JTextArea poleTekstowe, PanelDiagramu panelDiagramu, JComponent przyciskCofnij, JComponent przyciskPonow) {
        
        this.elementNadrzedny = elementNadrzedny;
        
        this.poleTekstowe = poleTekstowe;
        this.panelDiagramu = panelDiagramu;
        this.przyciskCofania = przyciskCofnij;
        this.przyciskPonawiania = przyciskPonow;
        
        listaCofania = new LinkedList<>();
        listaPonawiania = new LinkedList<>();
        
        poprzedniTekst = poleTekstowe.getText();
        ustawWidocznoscCofania();
        
    }
    
    @Override
    public void actionPerformed (ActionEvent e) {
        
        switch (e.getActionCommand())
        {
            case "PlikWczytaj":
                //TODO: Obsługa zdarzenia.
                break;
            case "PlikZapisz":
                //TODO: Obsługa zdarzenia.
                break;
            case "EdycjaCofnij":
                listaPonawiania.add(poprzedniTekst);
                poprzedniTekst = listaCofania.pollLast();
                poleTekstowe.setText(poprzedniTekst);
                ustawWidocznoscCofania();
                break;
            case "EdycjaPonow":
                listaCofania.add(poprzedniTekst);
                poprzedniTekst = listaPonawiania.pollLast();
                poleTekstowe.setText(poprzedniTekst);
                ustawWidocznoscCofania();
                break;
            case "EdycjaSzablon":
                //TODO: Obsługa zdarzenia.
                break;
            case "EdycjaWstawAktora":
                //TODO: Obsługa zdarzenia.
                break;
            case "EdycjaWstawKomunikat":
                //TODO: Obsługa zdarzenia.
                break;
            case "EdycjaWstawObszar":
                //TODO: Obsługa zdarzenia.
                break;
            case "DiagramGeneruj" :
                generujDiagram();
                break;
        }
    }
    
    @Override
    public void caretUpdate(CaretEvent e) {
              
        String aktualnyTekst = poleTekstowe.getText();
        if (!aktualnyTekst.equals(poprzedniTekst)) {
            listaCofania.add(poprzedniTekst);
            poprzedniTekst = aktualnyTekst;
            ustawWidocznoscCofania();
        }
        
    }
    
    private void ustawWidocznoscCofania() {
        
        if (listaCofania.isEmpty()) {
            przyciskCofania.setEnabled(false);
        }
        else {
            przyciskCofania.setEnabled(true);
        }
        
        if (listaPonawiania.isEmpty()) {
            przyciskPonawiania.setEnabled(false);
        }
        else {
            przyciskPonawiania.setEnabled(true);
        }
        
    }

    private void generujDiagram() {
        
        try {
            Diagram modelDiagramu = AnalizatorTekstu.przygotujDiagram(poleTekstowe.getText());
            panelDiagramu.ladujPonownie(modelDiagramu);
        } catch (DiagramException ex) {
            JOptionPane.showMessageDialog(elementNadrzedny, ex.opisBledu);
        }
    }
    
}
