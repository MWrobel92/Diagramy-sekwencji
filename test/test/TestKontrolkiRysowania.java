package test;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Diagram;
import model.DiagramException;
import widok.PanelDiagramu;

/**
 *
 * @author Michal
 */
public class TestKontrolkiRysowania extends JPanel {
    
    JPanel panelGlowny;
    PanelDiagramu rysunek;
    
    private static void przygotujIPokazGUI() throws DiagramException {        
        
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Test kontrolki rysowania");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //prepairing panel
        TestKontrolkiRysowania newContentPane = new TestKontrolkiRysowania();
        newContentPane.setOpaque(true); 
        frame.setContentPane(newContentPane);

        //showing window
        frame.pack();
        frame.setSize(500, 600);
        frame.setVisible(true);
    }
    

    /**
     * Konstruktor
     */
    public TestKontrolkiRysowania () throws DiagramException {
        
        panelGlowny = new JPanel(); 
        
        Diagram diagram = TestTworzeniaDiagramow.utworzPrzykladowyDiagram();
        PanelDiagramu panel = new PanelDiagramu(diagram);
        panel.ustawWymiaryKratki(40, 220);
        
        panelGlowny.setLayout(new BorderLayout());
        panelGlowny.add(BorderLayout.CENTER, panel);        
              
        setLayout (new BorderLayout());
        add(BorderLayout.CENTER, panelGlowny);
        
    }
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {           
               @Override
               public void run() {
                   try {
                        przygotujIPokazGUI();
                   }
                   catch (DiagramException ex) {
                   }
                };

            });
        
    }
}
