/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.AnalizatorTekstu;
import model.Diagram;
import model.DiagramException;
import widok.PanelDiagramu;

/**
 *
 * @author Michal
 */
public class KontrolerOkna implements ActionListener, CaretListener, WindowListener {
    
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
    
    //Deklaracje związane z obsługą plików
    JFileChooser oknoObslugiPliku;
    JFileChooser oknoEksportuDiagramu;
    String ostatnioZapisanaWersja;
    File aktualnyPlik;
   
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
        ostatnioZapisanaWersja = "";
        ustawWidocznoscCofania();
        ustawOknaObslugiPlikow();
                
    }
    
    @Override
    public void actionPerformed (ActionEvent e) {
        
        switch (e.getActionCommand())
        {
            case "PlikWczytaj":
                wczytajPlik();
                break;
            case "PlikZapisz":
                if (aktualnyPlik != null) {
                    zapiszPlik();
                }
                else {
                    zapiszPlikJako();
                }
                break;
            case "PlikZapiszJako" :
                zapiszPlikJako();
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
            case "DiagramZapisz" :
                eksportujDiagram();
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
    
    private boolean wczytajPlik () {   
        
        boolean plikZostalWczytany = false;
        
        int wynikOperacji = oknoObslugiPliku.showOpenDialog(poleTekstowe);
        
        if (wynikOperacji == JFileChooser.APPROVE_OPTION) {
            
            // Wybrano plik do wczytania, można przejść do akcji
            File wybranyPlik = oknoObslugiPliku.getSelectedFile();
            
            try {
                BufferedReader strumienWejsciowy = new BufferedReader(new FileReader(wybranyPlik));                
                StringBuilder wczytanyTekst = new StringBuilder();
                boolean pierwszaLinia = true;
                
                aktualnyPlik = wybranyPlik;
      
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
                poleTekstowe.setText(wczytanyTekst.toString());
                ostatnioZapisanaWersja = wczytanyTekst.toString();
                plikZostalWczytany = true;
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(elementNadrzedny, "Problem z wczytaniem pliku.");
            }
        }
        
        return plikZostalWczytany;
    }
    
    private boolean zapiszPlik() {
      
        boolean plikZostalZapisany = false;
    
        try {
            BufferedWriter strumienWyjsciowy = new BufferedWriter(new FileWriter(aktualnyPlik));                                
            strumienWyjsciowy.write(poleTekstowe.getText());
            strumienWyjsciowy.close();
  
            ostatnioZapisanaWersja = poleTekstowe.getText();
            plikZostalZapisany = true;
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(elementNadrzedny, "Problem z zapisaniem pliku.");
        }
    
        return plikZostalZapisany;
    }
        
    private boolean zapiszPlikJako () {   
        
        boolean plikZostalZapisany = false;
        
        int wynikOperacji = oknoObslugiPliku.showSaveDialog(poleTekstowe);
        
        if (wynikOperacji == JFileChooser.APPROVE_OPTION) {            
            // Wybrano plik do wczytania, można przejść do akcji
            aktualnyPlik = oknoObslugiPliku.getSelectedFile();            
            plikZostalZapisany = zapiszPlik();
        }
        
        return plikZostalZapisany;
    }

    private boolean eksportujDiagram() {
        
        boolean plikZostalZapisany = false;
        
        int wynikOperacji = oknoEksportuDiagramu.showSaveDialog(poleTekstowe);
        
        if (wynikOperacji == JFileChooser.APPROVE_OPTION) {
            
            // Wybrano plik do wczytania, można przejść do akcji
            aktualnyPlik = oknoEksportuDiagramu.getSelectedFile();
                        
            try {
                panelDiagramu.ekspotrujPlik(aktualnyPlik);
                plikZostalZapisany = true;
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(elementNadrzedny, "Problem z zapisaniem pliku.");
            }
        }
        
        return plikZostalZapisany;
        
    }

    private void ustawOknaObslugiPlikow() {
        
        ostatnioZapisanaWersja = "";
        aktualnyPlik = null;
        
        oknoObslugiPliku = new JFileChooser(); 
        
        oknoEksportuDiagramu = new JFileChooser();
        FileFilter filtrJpg = new FileNameExtensionFilter("Plik JPG", "jpg", "jpeg");
        FileFilter filtrPng = new FileNameExtensionFilter("Plik PNG", "png");
        FileFilter filtrGif = new FileNameExtensionFilter("Plik GIF", "gif");
      
        oknoEksportuDiagramu.setFileFilter(filtrGif);
        oknoEksportuDiagramu.setFileFilter(filtrJpg);
        oknoEksportuDiagramu.setFileFilter(filtrPng);
        
    }
    
    /**
     * Funkcja sprawdza, czy aktualny tekst różni się od porzednio zapisanego i ewentualnie
     * wywołuje okno dialogowe z pytaniem, czy zapisać tekst do pliku.
     * @return True jeśli akcja powinna być kontynuowana, false w przeciwnym wypadku.
     */
    private boolean skonsultujZapis () {
        
        boolean kontynuuj = false;
        
        String tekst = poleTekstowe.getText();
        if (tekst.equals(ostatnioZapisanaWersja)) {
            kontynuuj = true;
        }
        else {            
            OknoTakNieAnuluj oknoDialogowe = new OknoTakNieAnuluj(elementNadrzedny.pobierzRamke(), "Zapisz plik", "Są niezapisane zmiany");
            oknoDialogowe.setVisible(true);
            
            switch (oknoDialogowe.zwrocRezultat()) {
                case "tak" :
                    boolean zapisano;
                    if (aktualnyPlik != null) {
                        zapisano = zapiszPlik();
                    }
                    else {
                        zapisano = zapiszPlikJako();
                    }
                    if (zapisano) {
                        kontynuuj = true;
                    }                    
                    break;
                case "nie" :
                    kontynuuj = true;
                    break;
            }
        }
        return kontynuuj;
    }
    ////////// OBSŁUGA ZDARZEŃ OKNA //////////

    @Override
    public void windowOpened(WindowEvent e) {
        //Niepotrzebne
    }

    @Override
    public void windowClosing(WindowEvent e) {
        
        if (skonsultujZapis()) {
            elementNadrzedny.pobierzRamke().dispose();
        }
        
    }

    @Override
    public void windowClosed(WindowEvent e) {
        // Niepotrzebne
    }

    @Override
    public void windowIconified(WindowEvent e) {
        //Niepotrzebne
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        //Niepotrzebne
    }

    @Override
    public void windowActivated(WindowEvent e) {
        //Niepotrzebne
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        //Niepotrzebne
    }
  
}
