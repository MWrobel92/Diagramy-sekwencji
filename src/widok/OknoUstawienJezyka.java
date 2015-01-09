package widok;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import kontroler.KontrolerJezykow;
import model.JezykInterfejsu;
import model.JezykInterfejsuPolski;
import model.JezykSkladni;
import model.JezykSkladniPolski;

/**
 * Okno dialogowe, w którym użytkownik może wybrać język składnii i interfejsu.
 * @author Michał Wróbel
 */
public class OknoUstawienJezyka extends JDialog implements ActionListener {
          
    private boolean zatwierdzoneZmiany;
    private KontrolerJezykow kontrolerJezykow;
    
    private List<JezykInterfejsu> jezykiInterfejsu;
    private List<JezykSkladni> jezykiSkladni;
    
    private JComboBox comboBoxInterfejsu;
    private JComboBox comboBoxSkladni;
    
    /**
     * Akcesor do wybranego języka interfejsu.
     * @return 
     */
    public JezykInterfejsu pobierzJezykInterfejsu () {
        return jezykiInterfejsu.get(comboBoxInterfejsu.getSelectedIndex());
    }
    
    /**
     * Akcesor do wybranego języka składnii.
     * @return 
     */
    public JezykSkladni pobierzJezykSkladni () {
        return jezykiSkladni.get(comboBoxSkladni.getSelectedIndex());
    }
    
    /**
     * Fonkcja zwracająca informację, czy użytkowik zatwierdził wprowadzone ustawienia, czy też je odrzucił.
     * @return 
     */
    public boolean zatwierdzono() {
        return zatwierdzoneZmiany;
    }
    
    /**
     * Konstruktor
     * @param elementNadrzedny Okno, z którego zostało wywołane okienko dialogowe.
     * @param naglowek Nazwa okna.
     * @param jezyk Aktualny język interfejsu.
     */
    public OknoUstawienJezyka (OknoProgramu elementNadrzedny, KontrolerJezykow kontroler) {
        
        super(elementNadrzedny.pobierzRamke(),kontroler.zwrocJezykInterfejsu().menuOpcjeJezyk(), true);
        kontrolerJezykow = kontroler;
        zatwierdzoneZmiany = false;
        
        this.setSize(300, 200);
        this.setLayout(new GridLayout(3, 2));
        
        comboBoxInterfejsu = new JComboBox();
        comboBoxSkladni = new JComboBox();
        
        jezykiInterfejsu = kontroler.zwrocListeJezykowInterfejsu();
        jezykiSkladni = kontroler.zwrocListeJezykowSkladni();
        
        // Dodawanie języków do ComboBo
        
        for (JezykInterfejsu j : jezykiInterfejsu) {
            comboBoxInterfejsu.addItem(j.nazwaJezyka());
        }
        
        for (JezykSkladni j : jezykiSkladni) {
            comboBoxSkladni.addItem(j.nazwaJezyka());
        }
        
        // Ułożenie kontrolek
                
        this.add(new JLabel(kontroler.zwrocJezykInterfejsu().kontrolkaJezykInterfejsu()));
        this.add(comboBoxInterfejsu);
        this.add(new JLabel(kontroler.zwrocJezykInterfejsu().kontrolkaJezykSkladni()));
        this.add(comboBoxSkladni);
        
        JButton ok = new JButton("OK");
        ok.setActionCommand("ok");
        ok.addActionListener(this);
        this.add(ok);      
        
        JButton anuluj = new JButton(kontroler.zwrocJezykInterfejsu().przyciskAnuluj());
        anuluj.setActionCommand("anuluj");
        anuluj.addActionListener(this);
        this.add(anuluj);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getActionCommand().equals("ok")) {
            
            zatwierdzoneZmiany = true;
            kontrolerJezykow.zmienJezykInterfejsu(comboBoxInterfejsu.getSelectedIndex());
            kontrolerJezykow.zmienJezykSkladni(comboBoxSkladni.getSelectedIndex());
        }  
        
        dispose();
        
    }
    
}
