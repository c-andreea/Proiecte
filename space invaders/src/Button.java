import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

public class Button extends JButton {
    private Font font;

    public Button(String text, String fontPath) {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        setText(text);
        setOpaque(false);
        setContentAreaFilled(false);
        setFont(font.deriveFont(25f));
        setPreferredSize(new Dimension(100, 30));
    }
}
