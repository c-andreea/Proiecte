//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ecran;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Button extends JButton {
    public Button(String label, ActionListener listener) {
        super(label);
        this.addActionListener(listener);
    }
}
