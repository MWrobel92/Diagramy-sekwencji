package kontroler;

import widok.PanelDiagramu;
import java.awt.BorderLayout;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.text.DefaultEditorKit;
import model.Diagram;
import model.JezykInterfejsu;
import model.JezykInterfejsuPolski;
import model.JezykSkladniPolski;

/**
 * Klasa odpowiedzialna za interfejs głównego okna programu.
 * @author Michał Wróbel
 */
public class OknoProgramu extends JPanel {
    
    // Elementy składowe odpowiadające za okno
    private KontrolerOkna listener;
    private JFrame frame;    
    private JezykInterfejsu jezyk;
    
    // Kontrolki zawierające napisy zależne od języka
    private JMenu menuPlik;
    private JMenu menuEdycja;
    private JMenu menuDiagram;
    private JMenu menuOpcje;
    private JMenu menuPomoc;
    private JMenuItem menuPlikZapisz;
    private JMenuItem menuPlikZapiszJako;
    private JMenuItem menuPlikWczytaj;
    private JMenuItem menuPlikZamknij;
    private JMenuItem menuEdycjaCofnij;
    private JMenuItem menuEdycjaPonow;
    private JMenuItem menuEdycjaWytnij;
    private JMenuItem menuEdycjaKopiuj;
    private JMenuItem menuEdycjaWklej;
    private JMenu menuEdycjaWstaw;        
    private JMenuItem menuEdycjaWstawAktora;        
    private JMenuItem menuEdycjaWstawKomunikat;        
    private JMenuItem menuEdycjaWstawObszarWydzielony;        
    private JMenuItem menuOpcjeJezyk;
    private JMenuItem menuDiagramGeneruj;
    private JMenuItem menuDiagramZapisz;
    private JMenuItem menuPomocPomoc;
    private JMenuItem menuPomocOprogramie;
    private JCheckBoxMenuItem menuOpcjeAutoodswiezanie;
    private JLabel kontrolkaKonsola;
    private JLabel kontrolkaKodZrodlowy;
    private JLabel kontrolkaDiagram;
    
    // Elementy, do których może być potrzebny dostęp    
    private JTextArea poleKonsoli;
    private JTextArea poleTekstowe;
    private JTextArea kontrolkaLinie;
    private PanelDiagramu panelDiagramu;
        
    private void przygotujIPokazGUI() {        
        
        // Podstawowe ustawienia okna
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Diagram UML");
        frame.addWindowListener(listener);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        // Przygotowanie głównego panelu
        OknoProgramu newContentPane = this;
        newContentPane.setOpaque(true); 
        frame.setContentPane(newContentPane);

        // Wyświetlenie okna 
        frame.setSize(600, 400);
        frame.setVisible(true);
    }
    
    /**
     * Konstruktor.
     */
    public OknoProgramu () {
        
        // Inicjalizacja modelu i języka
        Diagram modelDiagramu = new Diagram(new JezykSkladniPolski());
        jezyk = new JezykInterfejsuPolski();
        
        // Deklaracje kontenerów GUI
        JMenuBar pasekMenu;
        JSplitPane panelGlowny;
        JPanel panelKonsoli;
        JScrollPane wewnetrznyPanelKonsoli;
        JSplitPane panelLewy;
        JPanel panelLewyZagniezdzony;
        JPanel panelPrawy;
        JPanel panelTekstuZagniezdzony;
        JScrollPane panelTekstu;
        JScrollPane panelZDiagramem;
        
        // Pasek menu
        pasekMenu = new JMenuBar();
        menuPlik = new JMenu();
        menuEdycja = new JMenu();
        menuDiagram = new JMenu();
        menuPomoc = new JMenu();
        menuPlikWczytaj = new JMenuItem();
        menuPlikZapisz = new JMenuItem();
        menuPlikZapiszJako = new JMenuItem();
        menuPlikZamknij = new JMenuItem();
        menuPlik.add(menuPlikWczytaj);
        menuPlik.add(menuPlikZapisz);
        menuPlik.add(menuPlikZapiszJako);
        menuPlik.addSeparator();
        menuPlik.add(menuPlikZamknij);
        menuEdycjaCofnij = new JMenuItem();
        menuEdycjaPonow = new JMenuItem();
        menuEdycjaWytnij = new JMenuItem(new DefaultEditorKit.CutAction());
        menuEdycjaKopiuj = new JMenuItem(new DefaultEditorKit.CopyAction());
        menuEdycjaWklej = new JMenuItem(new DefaultEditorKit.PasteAction());        
        menuEdycjaWstaw = new JMenu();
        menuEdycjaWstawAktora = new JMenuItem();
        menuEdycjaWstawKomunikat = new JMenuItem(); 
        menuEdycjaWstawObszarWydzielony = new JMenuItem();
        menuEdycjaWstaw.add(menuEdycjaWstawAktora);
        menuEdycjaWstaw.add(menuEdycjaWstawKomunikat);
        menuEdycjaWstaw.add(menuEdycjaWstawObszarWydzielony);
        menuEdycja.add(menuEdycjaCofnij);
        menuEdycja.add(menuEdycjaPonow);
        menuEdycja.addSeparator();
        menuEdycja.add(menuEdycjaWytnij);
        menuEdycja.add(menuEdycjaKopiuj);
        menuEdycja.add(menuEdycjaWklej);  
        menuEdycja.addSeparator();
        menuEdycja.add(menuEdycjaWstaw);
        menuOpcje = new JMenu();
        menuOpcjeJezyk = new JMenuItem();
        menuOpcjeAutoodswiezanie = new JCheckBoxMenuItem();
        menuOpcjeAutoodswiezanie.setSelected(true);
        menuOpcje.add(menuOpcjeJezyk);
        menuOpcje.add(menuOpcjeAutoodswiezanie);
        menuDiagramGeneruj = new JMenuItem();
        menuDiagramZapisz  = new JMenuItem();
        menuDiagram.add(menuDiagramGeneruj);
        menuDiagram.add(menuDiagramZapisz);
        menuPomocPomoc = new JMenuItem();
        menuPomocOprogramie = new JMenuItem();
        menuPomoc.add(menuPomocPomoc);
        menuPomoc.add(menuPomocOprogramie);
        pasekMenu.add(menuPlik);
        pasekMenu.add(menuEdycja);
        pasekMenu.add(menuOpcje);
        pasekMenu.add(menuDiagram);
        pasekMenu.add(menuPomoc);
        
        // Panel główny       
        panelKonsoli = new JPanel();
        panelKonsoli.setLayout(new BorderLayout());
        poleKonsoli = new JTextArea();
        poleKonsoli.setRows(3);
        poleKonsoli.setEditable(false);
        wewnetrznyPanelKonsoli = new JScrollPane(poleKonsoli);
        kontrolkaKonsola = new JLabel();
        panelKonsoli.add(kontrolkaKonsola, BorderLayout.NORTH);
        panelKonsoli.add(wewnetrznyPanelKonsoli, BorderLayout.CENTER);        
        
        poleTekstowe = new JTextArea();
        panelTekstuZagniezdzony = new JPanel();
        panelTekstuZagniezdzony.setLayout(new BorderLayout());
        panelTekstuZagniezdzony.add(poleTekstowe, BorderLayout.CENTER);
        kontrolkaLinie = new JTextArea();
        kontrolkaLinie.setEditable(false);
        kontrolkaLinie.setCursor(null);  
        kontrolkaLinie.setOpaque(false);  
        kontrolkaLinie.setFocusable(false);
        ponumerujLinie();
        panelTekstuZagniezdzony.add(kontrolkaLinie, BorderLayout.WEST);
        panelTekstu = new JScrollPane(panelTekstuZagniezdzony);
        panelLewyZagniezdzony = new JPanel();
        panelLewyZagniezdzony.setLayout(new BorderLayout());
        kontrolkaKodZrodlowy = new JLabel();
        panelLewyZagniezdzony.add(kontrolkaKodZrodlowy, BorderLayout.NORTH);
        panelLewyZagniezdzony.add(panelTekstu, BorderLayout.CENTER);
        panelLewy = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelLewyZagniezdzony, panelKonsoli);
        panelLewy.setDividerLocation(280);
        
        panelDiagramu = new PanelDiagramu(modelDiagramu);
        panelZDiagramem = new JScrollPane(panelDiagramu);
        panelZDiagramem.setPreferredSize(null);        
        
        panelZDiagramem.getViewport().setOpaque(false);

        panelPrawy = new JPanel();
        panelPrawy.setLayout(new BorderLayout());
        kontrolkaDiagram = new JLabel();
        panelPrawy.add(kontrolkaDiagram, BorderLayout.NORTH);
        panelPrawy.add(panelZDiagramem, BorderLayout.CENTER);
        
        // Panel główny
        panelGlowny = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelLewy, panelPrawy);
        panelGlowny.setDividerLocation(300);
        
        // Okno        
        setLayout(new BorderLayout());
        add(pasekMenu, BorderLayout.NORTH);
        add(panelGlowny, BorderLayout.CENTER);
        ustawNapisyNaKontrolkach();
        
        // Ustawienie listenera i komend akcji
        listener = new KontrolerOkna(this);
        
        menuPlikWczytaj.setActionCommand("PlikWczytaj");
        menuPlikWczytaj.addActionListener(listener);
        menuPlikZapisz.setActionCommand("PlikZapisz");
        menuPlikZapisz.addActionListener(listener);
        menuPlikZapiszJako.setActionCommand("PlikZapiszJako");
        menuPlikZapiszJako.addActionListener(listener);
        menuPlikZamknij.setActionCommand("PlikZamknij");
        menuPlikZamknij.addActionListener(listener);
        menuEdycjaCofnij.setActionCommand("EdycjaCofnij");
        menuEdycjaCofnij.addActionListener(listener);
        menuEdycjaPonow.setActionCommand("EdycjaPonow");
        menuEdycjaPonow.addActionListener(listener);
        menuEdycjaWstawAktora.setActionCommand("EdycjaWstawAktora");
        menuEdycjaWstawAktora.addActionListener(listener);
        menuEdycjaWstawKomunikat.setActionCommand("EdycjaWstawKomunikat");
        menuEdycjaWstawKomunikat.addActionListener(listener);
        menuEdycjaWstawObszarWydzielony.setActionCommand("EdycjaWstawObszar");
        menuEdycjaWstawObszarWydzielony.addActionListener(listener);
        menuOpcjeJezyk.setActionCommand("OpcjeJezyk");
        menuOpcjeJezyk.addActionListener(listener);
        menuDiagramGeneruj.setActionCommand("DiagramGeneruj");
        menuDiagramGeneruj.addActionListener(listener);
        menuDiagramZapisz.setActionCommand("DiagramZapisz");
        menuDiagramZapisz.addActionListener(listener);
        menuPomocPomoc.setActionCommand("PomocPomoc");
        menuPomocPomoc.addActionListener(listener);
        menuPomocOprogramie.setActionCommand("PomocOProgramie");
        menuPomocOprogramie.addActionListener(listener);
        poleTekstowe.addCaretListener(listener);
    }
    
    /**
     * Fukncja uruchamiająca okno.
     */
    public void start() {
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {           
            @Override
            public void run() {
                przygotujIPokazGUI();
            };
        });
    }

    /**
     * Akcesor do ramki głównego okna programu.
     * @return 
     */
    JFrame pobierzRamke() {
        return frame;
    }

    /**
     * Akcesor do języka interfejsu programu.
     * @return 
     */
    JezykInterfejsu pobierzJezyk() {
        return jezyk;
    }
    
    /**
     * Funkcja zwracająca informację, czy użytkownik życzy sobie odświeżania diagramu po każdej modyfikacji.
     * @return 
     */
    boolean autoodswiezanie() {
        return menuOpcjeAutoodswiezanie.getState();
    }

    /** 
     * Funkcja zmieniająca język interfejsu programu.
     * @param 
     */
    void ustawJezyk(JezykInterfejsu nowyJezyk) {
        jezyk = nowyJezyk;
        ustawNapisyNaKontrolkach();
        repaint();
    }
    
    /**
     * Funkcja dodaje napisy do kontrolek głównego okna programu.
     */
    private void ustawNapisyNaKontrolkach() {        
        
        menuPlik.setText(jezyk.menuPlik());
        menuEdycja.setText(jezyk.menuEdycja());
        menuDiagram.setText(jezyk.menuDiagram());        
        menuPomoc.setText(jezyk.menuPomoc());
        menuPlikWczytaj.setText(jezyk.menuPlikWczytaj());        
        menuPlikZapisz.setText(jezyk.menuPlikZapisz());        
        menuPlikZapiszJako.setText(jezyk.menuPlikZapiszJako());
        menuPlikZamknij.setText(jezyk.menuPlikZamknij());
        menuEdycjaCofnij.setText(jezyk.menuEdycjaCofnij());
        menuEdycjaPonow.setText(jezyk.menuEdycjaPonow());
        menuEdycjaWytnij.setText(jezyk.menuEdycjaWytnij());
        menuEdycjaKopiuj.setText(jezyk.menuEdycjaKopiuj());
        menuEdycjaWklej.setText(jezyk.menuEdycjaWklej());
        menuEdycjaWstaw.setText(jezyk.menuEdycjaWstaw());        
        menuEdycjaWstawAktora.setText(jezyk.menuEdycjaWstawAktora());
        menuEdycjaWstawKomunikat.setText(jezyk.menuEdycjaWstawKomunikat());        
        menuEdycjaWstawObszarWydzielony.setText(jezyk.menuEdycjaWstawObszarWydzielony());        
        menuOpcje.setText(jezyk.menuOpcje());        
        menuOpcjeJezyk.setText(jezyk.menuOpcjeJezyk());
        menuOpcjeAutoodswiezanie.setText(jezyk.menuOpcjeAutoodswiezanie());
        menuDiagramGeneruj.setText(jezyk.menuDiagramGeneruj());        
        menuDiagramZapisz.setText(jezyk.menuDiagramZapisz());
        menuPomocPomoc.setText(jezyk.menuPomocPomoc());
        menuPomocOprogramie.setText(jezyk.menuPomocOProgramie());
        kontrolkaKonsola.setText(jezyk.kontrolkaKonsola());
        kontrolkaKodZrodlowy.setText(jezyk.kontrolkaKodZrodlowy());
        kontrolkaDiagram.setText(jezyk.menuDiagram());
        
        UIManager.put("FileChooser.openDialogTitleText", jezyk.menuPlikWczytaj());
        UIManager.put("FileChooser.saveDialogTitleText", jezyk.menuPlikZapisz());        
        UIManager.put("FileChooser.fileNameLabelText", jezyk.etykietaNazwaPliku());
        UIManager.put("FileChooser.filesOfTypeLabelText", jezyk.etykietaTypPliku());
        UIManager.put("FileChooser.saveButtonText", jezyk.menuPlikZapisz());
        UIManager.put("FileChooser.openButtonText", jezyk.menuPlikWczytaj());
        UIManager.put("FileChooser.directoryOpenButtonText", jezyk.menuPlikWczytaj());
        UIManager.put("FileChooser.cancelButtonText", jezyk.przyciskAnuluj());
        UIManager.put("FileChooser.updateButtonText", jezyk.etykietaModyfikuj());        
        UIManager.put("FileChooser.acceptAllFileFilterText", jezyk.etykietaWszystkiePliki());        
        UIManager.put("FileChooser.lookInLabelText", jezyk.etykietaPrzegladaj());
        UIManager.put("FileChooser.saveInLabelText", jezyk.etykietaZapiszDo());                 
        UIManager.put("FileChooser.upFolderToolTipText", jezyk.etykietaWGore());
        UIManager.put("FileChooser.homeFolderToolTipText", jezyk.etykietaPulpit());
        UIManager.put("FileChooser.newFolderToolTipText", jezyk.etykietaNowyFolder());
        UIManager.put("FileChooser.listViewButtonToolTipText", jezyk.etykietaLista());
        UIManager.put("FileChooser.detailsViewButtonToolTipText", jezyk.etykietaSzczegoly());
        
    }

    JTextArea pobierzPoleKonsoli() {
        return poleKonsoli;
    }

    JTextArea pobierzPoleTekstowe() {
        return poleTekstowe;
    }

    PanelDiagramu pobierzPanelDiagramu() {
        return panelDiagramu;
    }

    JComponent pobierzPrzyciskCofania() {
        return menuEdycjaCofnij;
    }

    JComponent pobierzPrzyciskPonawiania() {
        return menuEdycjaPonow;
    }

    void ponumerujLinie() {
        int liczbaLinii = poleTekstowe.getLineCount();
        StringBuilder numeryLinii = new StringBuilder();
        for(int i=1; i<=liczbaLinii; ++i) {
            numeryLinii.append(i);
            numeryLinii.append("\n");
        }
        kontrolkaLinie.setText(numeryLinii.toString());
    }
}
