/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Michał
 */
public class OknoTakNieAnuluj extends JDialog implements ActionListener {
          
    private String wynik;
    
    public String zwrocRezultat() {
        return wynik;
    }
    
    public OknoTakNieAnuluj (JFrame oknoNadrzedne, String naglowek, String tresc) {
        
        super(oknoNadrzedne, naglowek, true);
        
        this.setSize(300, 200);
        this.setLayout(new FlowLayout());
        
        wynik = "";        
        this.add(new JLabel(tresc));
        
        JButton tak = new JButton("Tak");
        tak.setActionCommand("tak");
        tak.addActionListener(this);
        this.add(tak);
        
        JButton nie = new JButton("Nie");
        nie.setActionCommand("nie");
        nie.addActionListener(this);
        this.add(nie);
        
        JButton anuluj = new JButton("Anuluj");
        anuluj.setActionCommand("anuluj");
        anuluj.addActionListener(this);
        this.add(anuluj);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        wynik = e.getActionCommand();
        dispose();    
    }
    
}
