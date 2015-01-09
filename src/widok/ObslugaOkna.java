package widok;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.LinkedList;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import kontroler.KontrolerGenerowania;
import kontroler.KontrolerJezykow;
import kontroler.KontrolerPlikow;
import model.JezykInterfejsu;

/**
 * Klasa będąca obiektem składowym okna programu. Zajmuje się nasłuchiwaniem zdarzeń, które od niego przychodzą.
 * @author Michał Wróbel
 */
public class ObslugaOkna implements ActionListener, CaretListener, WindowListener {
    
    //Deklaracje elementów nadrzędnych, do których potrzebny jest dostęp
    private OknoProgramu elementNadrzedny;
    
    private KontrolerJezykow kontrolerJezyka;
    private KontrolerGenerowania kontrolerGenerowania;
    private KontrolerPlikow kontrolerPlikow;
    
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
   
    /**
     * Konstruktor ustawiający wszystkie komponenty, do których listener musi mieć dostęp.
     * Widoczność komponentów cofania i ponawiania zostaje ustawiona automatycznie.
     * @param elementNadrzedny Okno programu, które ma być sterowane.
     */
    public ObslugaOkna (OknoProgramu elementNadrzedny) {
        
        this.elementNadrzedny = elementNadrzedny;
        
        kontrolerJezyka = elementNadrzedny.kontroler;
        kontrolerGenerowania = new KontrolerGenerowania(kontrolerJezyka);
        kontrolerPlikow = new KontrolerPlikow();
        
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
                if (kontrolerPlikow.jestZapamietanyPlik()) {
                    zapiszPlik();
                }
                else {
                    zapiszPlikJako();
                }
                break;
            case "PlikZapiszJako" :
                zapiszPlikJako();
                break;
            case "PlikZamknij" :
                if (skonsultujZapis()) {
                    elementNadrzedny.pobierzRamke().dispose();
                }
                break;
            case "EdycjaCofnij":
                listaPonawiania.add(poprzedniTekst);
                poprzedniTekst = listaCofania.pollLast();
                poleTekstowe.removeCaretListener(this);
                poleTekstowe.setText(poprzedniTekst);
                uruchomGenerowanieDiagramu(false);
                poleTekstowe.addCaretListener(this);
                ustawWidocznoscCofania();
                break;
            case "EdycjaPonow":
                listaCofania.add(poprzedniTekst);
                poprzedniTekst = listaPonawiania.pollLast();
                poleTekstowe.removeCaretListener(this);
                poleTekstowe.setText(poprzedniTekst);
                uruchomGenerowanieDiagramu(false);
                poleTekstowe.addCaretListener(this);
                ustawWidocznoscCofania();
                break;
            case "EdycjaWstawAktora":
                poleTekstowe.append(kontrolerJezyka.przykladowyUczestnik());
                break;
            case "EdycjaWstawKomunikat":
                poleTekstowe.append(kontrolerJezyka.przykladowyKomunikat());
                break;
            case "EdycjaWstawObszar":
                poleTekstowe.append(kontrolerJezyka.przykladowyObszar());
                break;
            case "OpcjeJezyk" :
                ustawieniaJezyka();
                break;
            case "DiagramGeneruj" :
                uruchomGenerowanieDiagramu(true);
                break;
            case "DiagramZapisz" :
                eksportujDiagram();
                break;
            case "PomocPomoc" :
                OknoPomocy pomoc = new OknoPomocy(kontrolerJezyka.zwrocJezykInterfejsu(), kontrolerJezyka.zwrocJezykSkladni());
                pomoc.setVisible(true);
                break;
            case "PomocOProgramie" :
                JezykInterfejsu jezyk = kontrolerJezyka.zwrocJezykInterfejsu();
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
                uruchomGenerowanieDiagramu(false);
            }
            
            // Obsługa cofania
            listaCofania.add(poprzedniTekst);
            listaPonawiania.clear();
            poprzedniTekst = aktualnyTekst;
            ustawWidocznoscCofania();
            
            // Wyznaczenie linii
            elementNadrzedny.ponumerujLinie();
          
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
    
    private boolean wczytajPlik () {   
        
        boolean plikZostalWczytany = false;        
        int wynikOperacji = oknoObslugiPliku.showOpenDialog(poleTekstowe);
        
        if (wynikOperacji == JFileChooser.APPROVE_OPTION) {
            
            // Wybrano plik do wczytania, można przejść do akcji
            File wybranyPlik = oknoObslugiPliku.getSelectedFile();
            
            kontrolerPlikow.otworzPlik(wybranyPlik);
            String wczytanyTekst = kontrolerPlikow.wczytajPlik();
             
            if (wczytanyTekst != null) {
                poleTekstowe.setText(wczytanyTekst);
                ostatnioZapisanaWersja = wczytanyTekst;
                plikZostalWczytany = true;
            }
            else {
                JOptionPane.showMessageDialog(elementNadrzedny, kontrolerJezyka.zwrocJezykInterfejsu().oknoBladWczytania());
            }
        }
        
        return plikZostalWczytany;
    }
    
    private boolean zapiszPlik() {
      
        boolean plikZostalZapisany = kontrolerPlikow.zapiszPlik(poleTekstowe.getText());
    
        if (plikZostalZapisany) { 
            ostatnioZapisanaWersja = poleTekstowe.getText();
        }
        else {
            JOptionPane.showMessageDialog(elementNadrzedny, kontrolerJezyka.zwrocJezykInterfejsu().oknoBladZapisu());
        }
    
        return plikZostalZapisany;
    }
        
    private boolean zapiszPlikJako () {   
        
        boolean plikZostalZapisany = false;
        
        int wynikOperacji = oknoObslugiPliku.showSaveDialog(poleTekstowe);
        
        if (wynikOperacji == JFileChooser.APPROVE_OPTION) {  
                        
            // Dopisanie rozszerzenia jeśli jest taka potrzeba
            String wybranyFiltr = oknoObslugiPliku.getFileFilter().getDescription();
                    
            if(wybranyFiltr.equals(kontrolerJezyka.zwrocJezykInterfejsu().etykietaPlikUml()) && 
                    !oknoObslugiPliku.getSelectedFile().getAbsolutePath().endsWith(".uml")) {
                kontrolerPlikow.otworzPlik(new File(oknoObslugiPliku.getSelectedFile() + ".uml"));
            }
            else {
                // Wybrano plik do wczytania, można przejść do akcji
                kontrolerPlikow.otworzPlik(oknoObslugiPliku.getSelectedFile()); 
            }
            
            plikZostalZapisany = zapiszPlik();
        }
        
        return plikZostalZapisany;
    }

    private boolean eksportujDiagram() {
        
        boolean plikZostalZapisany = false;
        
        int wynikOperacji = oknoEksportuDiagramu.showSaveDialog(poleTekstowe);
        
        if (wynikOperacji == JFileChooser.APPROVE_OPTION) {
            
            JezykInterfejsu jezyk = kontrolerJezyka.zwrocJezykInterfejsu();
            
            // Wybrano plik do wczytania, można przejść do akcji
            File plikDiagramu = oknoEksportuDiagramu.getSelectedFile();
            
            // Dopisanie rozszerzenia jeśli jest taka potrzeba
            String wybranyFiltr = oknoEksportuDiagramu.getFileFilter().getDescription();
                    
            if(wybranyFiltr.equals(jezyk.etykietaPlikPng()) && 
                    !oknoEksportuDiagramu.getSelectedFile().getAbsolutePath().endsWith(".png")) {
                plikDiagramu = new File(oknoEksportuDiagramu.getSelectedFile() + ".png");
            }
                        
            try {
                panelDiagramu.ekspotrujPlik(plikDiagramu);
                plikZostalZapisany = true;
            }
            catch (Exception | OutOfMemoryError ex) {
                JOptionPane.showMessageDialog(elementNadrzedny, jezyk.oknoBladZapisu());
            }
        }
        
        return plikZostalZapisany;
        
    }

    private void ustawOknaObslugiPlikow() {
        
        JezykInterfejsu jezyk = kontrolerJezyka.zwrocJezykInterfejsu();
        
        ostatnioZapisanaWersja = "";
        
        oknoObslugiPliku = new JFileChooser();        
        FileFilter filtrUml = new FileNameExtensionFilter(jezyk.etykietaPlikUml(), "uml");
        oknoObslugiPliku.setFileFilter(filtrUml);
        
        oknoEksportuDiagramu = new JFileChooser();
        FileFilter filtrPng = new FileNameExtensionFilter(jezyk.etykietaPlikPng(), "png");
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
            JezykInterfejsu jezyk = kontrolerJezyka.zwrocJezykInterfejsu();
            OknoTakNieAnuluj oknoDialogowe = new OknoTakNieAnuluj(elementNadrzedny, jezyk.oknoNiezapisaneNaglowek(), jezyk.onkoNiezapisaneTresc());
            oknoDialogowe.setVisible(true);
            
            switch (oknoDialogowe.zwrocRezultat()) {
                case "tak" :
                    boolean zapisano;
                    if (kontrolerPlikow.jestZapamietanyPlik()) {
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


    private void ustawieniaJezyka() {
        
        OknoUstawienJezyka okno = new OknoUstawienJezyka(elementNadrzedny, kontrolerJezyka);
        okno.setVisible(true);
        if (okno.zatwierdzono()) {
            elementNadrzedny.revalidate();
        }
    }

    /**
     * Funkcja generująca diagram w osobnym wątku.
     * @param oknoPotwierdzenia Jeśli true, błąd w generowaniu wykresu zostanie wyświetlony w oknie dialogowym
     */
    private void uruchomGenerowanieDiagramu(boolean oknoPotwierdzenia) {
        
        final boolean okno = oknoPotwierdzenia;        
        Thread watek = new Thread(new Runnable() {          
                    
            @Override
            public void run() {
                    
                if (kontrolerGenerowania.generujDiagram(poleTekstowe.getText()) == true) {
                    
                    //Kompilacja przebiegła prawidłowo
                    panelDiagramu.ladujPonownie(kontrolerGenerowania.pobierzOstatnioWygenerowanyDiagram());
                    poleKonsoli.setText(kontrolerGenerowania.pobierzOstatniRezultat());
            
                } else {
                    
                    //Kompilacja przebiegła nieprawidłowo
                    poleKonsoli.setText(kontrolerGenerowania.pobierzOstatniRezultat());
                    if (okno) {
                        JOptionPane.showMessageDialog(elementNadrzedny,
                                kontrolerJezyka.zwrocJezykInterfejsu().oknoBladKompilacji(),
                                kontrolerJezyka.zwrocJezykInterfejsu().bladNaglowek(),
                                JOptionPane.WARNING_MESSAGE);
                    }                        
                }
            }
        });
        
        watek.start();
    }
    
}
