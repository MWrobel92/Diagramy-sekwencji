package kontroler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.AnalizatorTekstu;
import model.Diagram;
import model.DiagramException;
import model.JezykInterfejsu;
import model.JezykSkladni;
import model.JezykSkladniPolski;
import widok.PanelDiagramu;

/**
 * Klasa będąca obiektem składowym okna programu. Zajmuje się nasłuchiwaniem zdarzeń, które od niego przychodzą.
 * @author Michał Wróbel
 */
public class KontrolerOkna implements ActionListener, CaretListener, WindowListener {
    
    //Deklaracje elementów nadrzędnych, do których potrzebny jest dostęp
    private JezykInterfejsu jezyk;
    private JezykSkladni jezykS;
    private OknoProgramu elementNadrzedny;
    
    //Deklaracje kontrolek, do których listener musi mieć dostęp.
    private JTextArea poleTekstowe;
    private JTextArea poleKonsoli;
    private PanelDiagramu panelDiagramu;
    private JComponent przyciskCofania;
    private JComponent przyciskPonawiania;
    
    //Deklaracje list z historią zmian
    private LinkedList<String> listaCofania;
    private LinkedList<String> listaPonawiania;
    private String poprzedniTekst;
    
    //Deklaracje związane z obsługą plików
    private JFileChooser oknoObslugiPliku;
    private JFileChooser oknoEksportuDiagramu;
    private String ostatnioZapisanaWersja;
    private File aktualnyPlik;
   
    /**
     * Konstruktor ustawiający wszystkie komponenty, do których listener musi mieć dostęp.
     * Widoczność komponentów cofania i ponawiania zostaje ustawiona automatycznie.
     * @param elementNadrzedny Okno programu, które ma być sterowane.
     */
    public KontrolerOkna (OknoProgramu elementNadrzedny) {
        
        this.elementNadrzedny = elementNadrzedny;
        
        jezyk = elementNadrzedny.pobierzJezyk();
        jezykS = new JezykSkladniPolski();
        
        this.poleKonsoli = elementNadrzedny.pobierzPoleKonsoli();                
        this.poleTekstowe = elementNadrzedny.pobierzPoleTekstowe();
        this.panelDiagramu = elementNadrzedny.pobierzPanelDiagramu();
        this.przyciskCofania = elementNadrzedny.pobierzPrzyciskCofania();
        this.przyciskPonawiania = elementNadrzedny.pobierzPrzyciskPonawiania();
        
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
                poleTekstowe.removeCaretListener(this);
                poleTekstowe.setText(poprzedniTekst);
                uruchomGenerowanieDiagramu();
                poleTekstowe.addCaretListener(this);
                ustawWidocznoscCofania();
                break;
            case "EdycjaPonow":
                listaCofania.add(poprzedniTekst);
                poprzedniTekst = listaPonawiania.pollLast();
                poleTekstowe.removeCaretListener(this);
                poleTekstowe.setText(poprzedniTekst);
                uruchomGenerowanieDiagramu();
                poleTekstowe.addCaretListener(this);
                ustawWidocznoscCofania();
                break;
            case "EdycjaWstawAktora":
                dopiszUczestnika();
                break;
            case "EdycjaWstawKomunikat":
                dopiszKomunikat();
                break;
            case "EdycjaWstawObszar":
                dopiszObszar();
                break;
            case "OpcjeJezyk" :
                ustawieniaJezyka();
                break;
            case "DiagramGeneruj" :
                generujDiagram(true);
                break;
            case "DiagramZapisz" :
                eksportujDiagram();
                break;
            case "PomocPomoc" :
                OknoPomocy pomoc = new OknoPomocy(jezyk, jezykS);
                pomoc.setVisible(true);
                break;
            case "PomocOProgramie" :
                JOptionPane.showMessageDialog(elementNadrzedny, jezyk.pomocOProgramie(), jezyk.menuPomocOProgramie(), JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }
    
    @Override
    public void caretUpdate(CaretEvent e) {
              
        String aktualnyTekst = poleTekstowe.getText();
        if (!aktualnyTekst.equals(poprzedniTekst)) {
            
            // Generowanie diagramu (w osobnym wątku)
            if (elementNadrzedny.autoodswiezanie()) {
                uruchomGenerowanieDiagramu();
            }
            
            // Obsługa cofania
            listaCofania.add(poprzedniTekst);
            listaPonawiania.clear();
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
    
    /**
     * 
     * @param oknoPotwierdzenia Jeśli true, wystąpienie błędu będzie zasygnalizowane messageboksem.
     */    
    private void generujDiagram(boolean oknoPotwierdzenia) {
        
        try {
            AnalizatorTekstu analizator = new AnalizatorTekstu(jezykS);
           
            Diagram modelDiagramu = analizator.przygotujDiagram(poleTekstowe.getText());
            panelDiagramu.ladujPonownie(modelDiagramu);
            poleKonsoli.setText(jezyk.komunikatPoprawnaKompilacja());
            
        } catch (DiagramException ex) {
            poleKonsoli.setText(ex.wypiszBlad(jezyk));
            if (oknoPotwierdzenia) {
                JOptionPane.showMessageDialog(elementNadrzedny, jezyk.oknoBladKompilacji(), jezyk.bladNaglowek(), JOptionPane.WARNING_MESSAGE);
            }                        
        }
    }
    
    private boolean wczytajPlik () {   
        
        boolean plikZostalWczytany = false;
        
        int wynikOperacji = oknoObslugiPliku.showOpenDialog(poleTekstowe);
        
        if (wynikOperacji == JFileChooser.APPROVE_OPTION) {
            
            // Wybrano plik do wczytania, można przejść do akcji
            File wybranyPlik = oknoObslugiPliku.getSelectedFile();
            
            try {
                BufferedReader strumienWejsciowy = new BufferedReader(
                    new InputStreamReader(new FileInputStream(wybranyPlik), "UTF-8")
                    );  
                
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
                JOptionPane.showMessageDialog(elementNadrzedny, jezyk.oknoBladWczytania());
            }
        }
        
        return plikZostalWczytany;
    }
    
    private boolean zapiszPlik() {
      
        boolean plikZostalZapisany = false;
    
        try {
            BufferedWriter strumienWyjsciowy = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(aktualnyPlik), "UTF-8")
                    );         
            
            strumienWyjsciowy.write(poleTekstowe.getText());
            strumienWyjsciowy.close();
  
            ostatnioZapisanaWersja = poleTekstowe.getText();
            plikZostalZapisany = true;
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(elementNadrzedny, jezyk.oknoBladZapisu());
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
                JOptionPane.showMessageDialog(elementNadrzedny, jezyk.oknoBladZapisu());
            }
        }
        
        return plikZostalZapisany;
        
    }

    private void ustawOknaObslugiPlikow() {
        
        ostatnioZapisanaWersja = "";
        aktualnyPlik = null;
        
        oknoObslugiPliku = new JFileChooser(); 
        
        oknoEksportuDiagramu = new JFileChooser();
        FileFilter filtrPng = new FileNameExtensionFilter("Plik PNG", "png");
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
            OknoTakNieAnuluj oknoDialogowe = new OknoTakNieAnuluj(elementNadrzedny, jezyk.oknoNiezapisaneNaglowek(), jezyk.onkoNiezapisaneTresc());
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

    private void dopiszUczestnika() {
        
        StringBuilder komenda = new StringBuilder("\n");
        komenda.append(jezykS.komendaObiekt());
        komenda.append('{');
        komenda.append(jezykS.atrybutNazwa());
        komenda.append('=');
        komenda.append(jezykS.przykladNazwy());
        komenda.append(' ');
        komenda.append(jezykS.atrybutTyp());
        komenda.append('=');
        komenda.append(jezykS.typKlasa());
        komenda.append("}\n");        
        poleTekstowe.append(komenda.toString());
        
    }

    private void dopiszKomunikat() {
        StringBuilder komenda = new StringBuilder("\n");
        komenda.append(jezykS.komendaKomunikat());
        komenda.append('{');
        komenda.append(jezykS.atrybutNazwa());
        komenda.append('=');
        komenda.append(jezykS.przykladNazwy());
        komenda.append(' ');
        komenda.append(jezykS.atrybutTyp());
        komenda.append('=');
        komenda.append(jezykS.typWywolanie());
        komenda.append(' ');
        komenda.append(jezykS.atrybutObiektuStartowego());
        komenda.append('=');
        komenda.append(jezykS.przykladNazwy());
        komenda.append(' ');
        komenda.append(jezykS.atrybutObiektuKoncowego());
        komenda.append('=');
        komenda.append(jezykS.przykladNazwy());
        komenda.append("}\n");        
        poleTekstowe.append(komenda.toString());}

    private void dopiszObszar() {
        StringBuilder komenda = new StringBuilder("\n");
        komenda.append(jezykS.komendaObszar());
        komenda.append('{');
        komenda.append(jezykS.atrybutNazwa());
        komenda.append('=');
        komenda.append(jezykS.przykladNazwy());
        komenda.append(' ');
        komenda.append(jezykS.atrybutObiektuStartowego());
        komenda.append('=');
        komenda.append(jezykS.przykladNazwy());
        komenda.append(' ');
        komenda.append(jezykS.atrybutObiektuKoncowego());
        komenda.append('=');
        komenda.append(jezykS.przykladNazwy());
        komenda.append("}\n");        
        poleTekstowe.append(komenda.toString());
    }

    private void ustawieniaJezyka() {
        OknoUstawienJezyka okno = new OknoUstawienJezyka(elementNadrzedny, jezyk.menuOpcjeJezyk(), jezyk);
        okno.setVisible(true);
        if (okno.zatwierdzono()) {
            elementNadrzedny.ustawJezyk(okno.pobierzJezykInterfejsu());
            jezykS = okno.pobierzJezykSkladni();
        }
    }

    private void uruchomGenerowanieDiagramu() {
        Thread watek = new Thread(new Runnable() {                
                    
            @Override
                public void run() {
                    generujDiagram(false);
                };
            });
            watek.start();}
}
