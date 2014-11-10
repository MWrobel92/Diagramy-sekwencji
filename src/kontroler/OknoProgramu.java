package kontroler;

import widok.PanelDiagramu;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import model.Diagram;

/**
 *
 * @author Michal
 */
public class OknoProgramu extends JPanel {
    
    private KontrolerOkna listener;
    private JFrame frame;
    
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
        
        // Inicjalizacja modelu
        Diagram modelDiagramu = new Diagram();
        
        // Deklaracje kontrolek GUI
        JMenuBar pasekMenu;
        JPanel panelGlowny;
        JMenu menuPlik;
        JMenu menuEdycja;
        JMenu menuDiagram;
        JMenu menuPomoc;
        JMenuItem menuPlikZapisz;
        JMenuItem menuPlikZapiszJako;
        JMenuItem menuPlikWczytaj;
        JMenuItem menuEdycjaCofnij;
        JMenuItem menuEdycjaPonow;
        JMenuItem menuEdycjaSzablon;
        JMenu menuEdycjaWstaw;        
        JMenuItem menuEdycjaWstawAktora;        
        JMenuItem menuEdycjaWstawKomunikat;        
        JMenuItem menuEdycjaWstawObszarWydzielony;
        JMenuItem menuDiagramGeneruj;
        JMenuItem menuDiagramZapisz;
        JMenuItem menuPomocPomoc;
        JMenuItem menuPomocOprogramie;
        JPanel panelLewy;
        PanelDiagramu panelDiagramu;
        JPanel panelPrawy;
        JScrollPane panelTekstu;
        JScrollPane panelZDiagramem;
        JTextArea poleTekstowe;
        
        // Pasek menu
        pasekMenu = new JMenuBar();
        menuPlik = new JMenu("Plik");
        menuEdycja = new JMenu("Edycja");
        menuDiagram = new JMenu("Diagram");
        menuPomoc = new JMenu("Pomoc");
        menuPlikWczytaj = new JMenuItem("Otwórz");
        menuPlikZapisz = new JMenuItem("Zapisz");
        menuPlikZapiszJako = new JMenuItem("Zapisz jako");
        menuPlik.add(menuPlikWczytaj);
        menuPlik.add(menuPlikZapisz);
        menuPlik.add(menuPlikZapiszJako);
        menuEdycjaCofnij = new JMenuItem("Cofnij");
        menuEdycjaPonow = new JMenuItem("Ponów");
        menuEdycjaSzablon = new JMenuItem("Wstaw szablon");
        menuEdycjaWstaw = new JMenu("Wstaw...");        
        menuEdycjaWstawAktora = new JMenuItem("Aktor");        
        menuEdycjaWstawKomunikat = new JMenuItem("Komunikat");        
        menuEdycjaWstawObszarWydzielony = new JMenuItem("Obszar wydzielony");
        menuEdycjaWstaw.add(menuEdycjaWstawAktora);
        menuEdycjaWstaw.add(menuEdycjaWstawKomunikat);
        menuEdycjaWstaw.add(menuEdycjaWstawObszarWydzielony);
        menuEdycja.add(menuEdycjaCofnij);
        menuEdycja.add(menuEdycjaPonow);
        menuEdycja.add(menuEdycjaSzablon);
        menuEdycja.add(menuEdycjaWstaw);
        menuDiagramGeneruj = new JMenuItem("Generuj");
        menuDiagramZapisz  = new JMenuItem("Zapisz do pliku");
        menuDiagram.add(menuDiagramGeneruj);
        menuDiagram.add(menuDiagramZapisz);
        menuPomocPomoc = new JMenuItem("Pomoc");
        menuPomocOprogramie = new JMenuItem("O programie");
        menuPomoc.add(menuPomocPomoc);
        menuPomoc.add(menuPomocOprogramie);
        pasekMenu.add(menuPlik);
        pasekMenu.add(menuEdycja);
        pasekMenu.add(menuDiagram);
        pasekMenu.add(menuPomoc);
        
        // Panel główny
        panelGlowny = new JPanel();
        
        poleTekstowe = new JTextArea();
        panelTekstu = new JScrollPane(poleTekstowe);
        panelLewy = new JPanel();
        panelLewy.setLayout(new BorderLayout());
        panelLewy.add(new JLabel("Kod źródłówy"), BorderLayout.NORTH);
        panelLewy.add(panelTekstu, BorderLayout.CENTER);
        
        panelDiagramu = new PanelDiagramu(modelDiagramu);
        panelZDiagramem = new JScrollPane(panelDiagramu);
        panelZDiagramem.setPreferredSize(null);

        panelPrawy = new JPanel();
        panelPrawy.setLayout(new BorderLayout());
        panelPrawy.add(new JLabel("Diagram"), BorderLayout.NORTH);
        panelPrawy.add(panelZDiagramem, BorderLayout.CENTER);
        
        GridLayout rozstawienie = new GridLayout(1, 2);
        panelGlowny.setLayout(rozstawienie);
        panelGlowny.add(panelLewy);
        panelGlowny.add(panelPrawy);
        
        // Okno
        setLayout(new BorderLayout());
        add(pasekMenu, BorderLayout.NORTH);
        add(panelGlowny, BorderLayout.CENTER);
        
        // Ustawienie listenera i komend akcji
        listener = new KontrolerOkna(this, poleTekstowe, panelDiagramu, menuEdycjaCofnij, menuEdycjaPonow);
        
        menuPlikWczytaj.setActionCommand("PlikWczytaj");
        menuPlikWczytaj.addActionListener(listener);
        menuPlikZapisz.setActionCommand("PlikZapisz");
        menuPlikZapisz.addActionListener(listener);
        menuPlikZapiszJako.setActionCommand("PlikZapiszJako");
        menuPlikZapiszJako.addActionListener(listener);
        menuEdycjaCofnij.setActionCommand("EdycjaCofnij");
        menuEdycjaCofnij.addActionListener(listener);
        menuEdycjaPonow.setActionCommand("EdycjaPonow");
        menuEdycjaPonow.addActionListener(listener);
        menuEdycjaSzablon.setActionCommand("EdycjaSzablon");
        menuEdycjaSzablon.addActionListener(listener);
        menuEdycjaWstawAktora.setActionCommand("EdycjaWstawAktora");
        menuEdycjaWstawAktora.addActionListener(listener);
        menuEdycjaWstawKomunikat.setActionCommand("EdycjaWstawKomunikat");
        menuEdycjaWstawKomunikat.addActionListener(listener);
        menuEdycjaWstawObszarWydzielony.setActionCommand("EdycjaWstawObszar");
        menuEdycjaWstawObszarWydzielony.addActionListener(listener);
        menuDiagramGeneruj.setActionCommand("DiagramGeneruj");
        menuDiagramGeneruj.addActionListener(listener);
        menuDiagramZapisz.setActionCommand("DiagramZapisz");
        menuDiagramZapisz.addActionListener(listener);
        poleTekstowe.addCaretListener(listener);
        
    }
    
    /**
     * @param args the command line arguments
     */
    public void start() {
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {           
            @Override
            public void run() {
                przygotujIPokazGUI();
            };
        });
    }

    JFrame pobierzRamke() {
        return frame;
    }
}
