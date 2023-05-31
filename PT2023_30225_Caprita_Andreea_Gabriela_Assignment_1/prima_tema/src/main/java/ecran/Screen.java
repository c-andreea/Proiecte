
package ecran;

import Functii.*;
import Poli.Polinom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Poli.Parsare.parsePolinom;

public class Screen extends JFrame implements ActionListener {

    private final JTextField pol1;
    private final JTextField pol2;

    private final JTextField rezf;

    private final JComboBox<String> deriBox;
    private final JComboBox<String> integBox2;


    public Screen() {
        setTitle("Calculator");
        setPreferredSize(new Dimension(500, 400));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel panel1 = new JPanel(new FlowLayout());

        panel1.add(new JLabel("Polinom 1:"));
        pol1 = new JTextField(20);
        panel1.add(pol1);
        panel.add(panel1);
        JPanel panel2 = new JPanel(new FlowLayout());
        panel2.add(new JLabel("Polinom 2:"));
        pol2 = new JTextField(20);
        panel2.add(pol2);
        panel.add(panel2);

        JPanel bp = new JPanel(new FlowLayout());
        JButton addu = new JButton("Adunare");
        addu.addActionListener(e -> performAdunare());

        JButton scad = new JButton("Scadere");
        scad.addActionListener(e -> performScadere());

        JButton inm = new JButton("Inmultire");
        inm.addActionListener(e -> performInmultire());

        JButton imp = new JButton("Impartire");
        imp.addActionListener(e -> performIpartire());

        bp.add(addu);
        bp.add(scad);
        bp.add(inm);
        bp.add(imp);

        deriBox = new JComboBox<>(new String[]{"Polinom 1", "Polinom 2"});
        JButton der = new JButton("Derivare");
        der.addActionListener(e -> performDerivare());
        JPanel derp = new JPanel(new FlowLayout());
        derp.add(deriBox);
        derp.add(der);

        integBox2 = new JComboBox<>(new String[]{"Polinom 1", "Polinom 2"});
        JButton intr = new JButton("Integrare");
        intr.addActionListener(e -> performInterare());
        JPanel intrp = new JPanel(new FlowLayout());
        intrp.add(integBox2);
        intrp.add(intr);
        bp.add(derp);
        bp.add(intrp);
        panel.add(bp);

        JPanel rezultatPanel = new JPanel(new FlowLayout());
        rezultatPanel.add(new JLabel("Rezultat:"));
        rezf = new JTextField(30);
        rezf.setEditable(false);
        rezultatPanel.add(rezf);
        panel.add(rezultatPanel);
        add(panel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    private void performAdunare() {
        if(pol1.getText().isEmpty() || pol2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completati ambii polinomi");
            return;
        }
        Polinom p1 = parsePolinom(pol1.getText());
        Polinom p2 = parsePolinom(pol2.getText());

        Polinom rez = Adunare.suma(p1, p2);
        rezf.setText(rez.toString());

    }

    private void performScadere() {
        if(pol1.getText().isEmpty() || pol2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completati ambii polinomi");
            return;
        }
        Polinom p1 = parsePolinom(pol1.getText());
        Polinom p2 = parsePolinom(pol2.getText());

        Polinom rez = Scadere.dif(p1, p2);
        rezf.setText(rez.toString());

    }

    private void performInmultire() {
        if(pol1.getText().isEmpty() || pol2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completati ambii polinomi");
            return;
        }
        Polinom p1 = parsePolinom(pol1.getText());
        Polinom p2 = parsePolinom(pol2.getText());

        Polinom rez = Inmultire.inmultire(p1, p2);
        rezf.setText(rez.toString());

    }

    private void performIpartire(){
        int x=1;
        if(x==1) {
            JOptionPane.showMessageDialog(this, "NU MERGE");
            return;
        }
    }

    private void performDerivare() {
        if(pol1.getText().isEmpty() && pol2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completati macar un polinom");
            return;
        }
        Polinom p = null;
        String poll = (String) deriBox.getSelectedItem();

        if (poll.equals("Polinom 1")) {
            if(pol1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Polinomul nu este completat");
                return;
            }
            p = parsePolinom(pol1.getText());
        } else if (poll.equals("Polinom 2")) {
            if(pol2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Polinomul nu este completat");
                return;
            }
            p = parsePolinom((pol2.getText()));
        }

        if (p != null) {
            Polinom rez = Derivare.deriv(p);
            rezf.setText(rez.toString());
        }

    }


    private void performInterare() {

        if(pol1.getText().isEmpty() && pol2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completati macar un polinom");
            return;
        }
        Polinom p = null;
        String poll = (String) integBox2.getSelectedItem();

        if (poll.equals("Polinom 1")) {
            if(pol1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Polinomul nu este completat");
                return;
            }
            p = parsePolinom(pol1.getText());
        } else if (poll.equals("Polinom 2")) {
            if(pol2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Polinomul nu este completat");
                return;
            }
            p = parsePolinom((pol2.getText()));
        }

        if (p != null) {
            Polinom rez = Integrare.integrare(p);
            rezf.setText(rez.toString());
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
