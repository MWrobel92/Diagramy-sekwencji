/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import model.JezykInterfejsu;
import model.JezykSkladni;

/**
 *
 * @author Michal
 */
public class OknoPomocy extends JDialog implements TreeSelectionListener {
    
    private class informacjeWezla {
        
        private String naglowek;
        private String tresc;
        
        informacjeWezla(String naglowek, String tresc) {
            this.naglowek = naglowek;
            this.tresc = tresc;
        }
        
        public String pobierzTresc() {
            return tresc;
        }
        
        @Override
        public String toString() {
            return naglowek;
        }
    }
    
    private JTree drzewoWyboruStony;
    JTextArea panelTekstu;

    public OknoPomocy (JezykInterfejsu jezyk, JezykSkladni jezykS) {
        
        // Ogólne ostawienia okna
        super((Frame)null, jezyk.menuPomoc(), false);
        this.setSize(500, 400);
        
        // Przygotowanie drzewa
        DefaultMutableTreeNode korzen = new DefaultMutableTreeNode(new informacjeWezla(jezyk.pomocOgolna(), jezyk.pomocOgolnaTresc()));
        drzewoWyboruStony = new JTree(korzen);       
        drzewoWyboruStony.addTreeSelectionListener(this);
        
        DefaultMutableTreeNode podstawoweInformacje = new DefaultMutableTreeNode(new informacjeWezla(jezyk.pomocPodstawoweInformacje(), jezyk.pomocPodstawoweInformacjeTresc()));
        korzen.add(podstawoweInformacje);
        DefaultMutableTreeNode elementy = new DefaultMutableTreeNode(new informacjeWezla(jezyk.pomocSkladnia(), jezyk.pomocSkladniaTresc()));
        korzen.add(elementy);
        DefaultMutableTreeNode obiekty = new DefaultMutableTreeNode(new informacjeWezla(jezyk.pomocObiekty(), jezyk.pomocObiektyTresc()));
        elementy.add(obiekty);
        DefaultMutableTreeNode komunikaty = new DefaultMutableTreeNode(new informacjeWezla(jezyk.pomocKomunikaty(), jezyk.pomocKomunikatyTresc()));
        elementy.add(komunikaty);
        DefaultMutableTreeNode bloki = new DefaultMutableTreeNode(new informacjeWezla(jezyk.pomocBloki(), jezyk.pomocBlokiTresc()));
        elementy.add(bloki);
        DefaultMutableTreeNode ustawieniaProgramu = new DefaultMutableTreeNode(new informacjeWezla(jezyk.pomocUstawieniaProgramu(), jezyk.pomocUstawieniaProgramuTresc()));
        korzen.add(ustawieniaProgramu);
        
        // Układ okna        
        panelTekstu = new JTextArea();
        panelTekstu.setEditable(false);
        JScrollPane panelLewy = new JScrollPane(drzewoWyboruStony);
        JScrollPane panelPrawy = new JScrollPane(panelTekstu);
        JSplitPane panelGlowny = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelLewy, panelPrawy);
        panelGlowny.setDividerLocation(150);
        this.add(panelGlowny);
        
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        
        DefaultMutableTreeNode zaznaczonyWezel = (DefaultMutableTreeNode)drzewoWyboruStony.getLastSelectedPathComponent();

        if (zaznaczonyWezel != null) {
            
            String informacje;
            try {
                informacje = ((informacjeWezla)zaznaczonyWezel.getUserObject()).pobierzTresc();
            }
            catch (Exception ex) {
                informacje = ex.getMessage();
            }
            
            panelTekstu.setText(informacje);
            
        }
    }
    
}