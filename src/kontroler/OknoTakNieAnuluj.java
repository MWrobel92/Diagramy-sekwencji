/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import model.JezykInterfejsu;

/**
 *
 * @author Michał
 */
public class OknoTakNieAnuluj extends JDialog implements ActionListener {
          
    private JezykInterfejsu jezyk;
    private String wynik;
    
    public String zwrocRezultat() {
        return wynik;
    }
    
    public OknoTakNieAnuluj (OknoProgramu elementNadrzedny, String naglowek, String tresc) {
        
        super(elementNadrzedny.pobierzRamke(), naglowek, true);
        jezyk = elementNadrzedny.pobierzJezyk();
        
        this.setSize(300, 200);
        this.setLayout(new BorderLayout());
        
        JPanel panelPrzyciskow = new JPanel();
        panelPrzyciskow.setLayout(new FlowLayout());
        
        wynik = "anuluj";
        
        // Ustawienie komponentu wyświatlającego tekst w kilku liniach.
        Font domyslnaCzcionkaKomunikatu = (new JLabel()).getFont();        
        JTextArea poleTresci = new JTextArea(tresc);
        poleTresci.setEditable(false);  
        poleTresci.setCursor(null);  
        poleTresci.setOpaque(false);  
        poleTresci.setFocusable(false);
        poleTresci.setLineWrap(true);
        poleTresci.setWrapStyleWord(true);
        poleTresci.setFont(domyslnaCzcionkaKomunikatu);
        this.add(poleTresci, BorderLayout.CENTER);
        
        JButton tak = new JButton(jezyk.przyciskTak());
        tak.setActionCommand("tak");
        tak.addActionListener(this);
        panelPrzyciskow.add(tak);
        
        JButton nie = new JButton(jezyk.przyciskNie());
        nie.setActionCommand("nie");
        nie.addActionListener(this);
        panelPrzyciskow.add(nie);
        
        JButton anuluj = new JButton(jezyk.przyciskAnuluj());
        anuluj.setActionCommand("anuluj");
        anuluj.addActionListener(this);
        panelPrzyciskow.add(anuluj);
        
        this.add(panelPrzyciskow, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        wynik = e.getActionCommand();
        dispose();    
    }
    
}
