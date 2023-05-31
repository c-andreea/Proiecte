package ecran;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Button extends JButton {

    public Button(String label, ActionListener listener) {
        super(label);
        addActionListener(listener);
    }
}