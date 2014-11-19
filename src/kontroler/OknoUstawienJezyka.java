/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import model.JezykInterfejsu;
import model.JezykInterfejsuPolski;
import model.JezykSkladni;
import model.JezykSkladniPolski;

/**
 *
 * @author Michal
 */
public class OknoUstawienJezyka extends JDialog implements ActionListener {
          
    private boolean zatwierdzoneZmiany;
    
    private ArrayList<JezykInterfejsu> jezykiInterfejsu;
    private ArrayList<JezykSkladni> jezykiSkladni;
    
    JComboBox comboBoxInterfejsu;
    JComboBox comboBoxSkladni;
    
    public JezykInterfejsu pobierzJezykInterfejsu () {
        return jezykiInterfejsu.get(comboBoxInterfejsu.getSelectedIndex());
    }
    
    public JezykSkladni pobierzJezykSkladni () {
        return jezykiSkladni.get(comboBoxSkladni.getSelectedIndex());
    }
    
    public boolean zatwierdzono() {
        return zatwierdzoneZmiany;
    }
    
    public OknoUstawienJezyka (OknoProgramu elementNadrzedny, String naglowek, JezykInterfejsu jezyk) {
        
        super(elementNadrzedny.pobierzRamke(), naglowek, true);
        zatwierdzoneZmiany = false;
        
        this.setSize(300, 200);
        this.setLayout(new GridLayout(3, 2));
        
        comboBoxInterfejsu = new JComboBox();
        comboBoxSkladni = new JComboBox();
        
        jezykiInterfejsu = new ArrayList<>();
        jezykiSkladni = new ArrayList<>();
        
        // DODAWANIE JĘZYKÓW INTERFEJSU
        
        comboBoxInterfejsu.addItem("Polski");
        jezykiInterfejsu.add(new JezykInterfejsuPolski());
        
        // DODAWANIE JĘZYKÓW SKŁADNI
        
        comboBoxSkladni.addItem("Polski");
        jezykiSkladni.add(new JezykSkladniPolski());
        
        // KONIEC DODAWANIA JĘZYKÓW
                
        this.add(new JLabel(jezyk.kontrolkaJezykInterfejsu()));
        this.add(comboBoxInterfejsu);
        this.add(new JLabel(jezyk.kontrolkaJezykSkladni()));
        this.add(comboBoxSkladni);
        
        JButton ok = new JButton("OK");
        ok.setActionCommand("ok");
        ok.addActionListener(this);
        this.add(ok);      
        
        JButton anuluj = new JButton(jezyk.przyciskAnuluj());
        anuluj.setActionCommand("anuluj");
        anuluj.addActionListener(this);
        this.add(anuluj);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getActionCommand().equals("ok")) {
            zatwierdzoneZmiany = true;
        }  
        
        dispose();
        
    }
    
}
