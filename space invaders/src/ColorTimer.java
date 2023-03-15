import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.Timer;

public class ColorTimer {
    private Timer timer;
    private JButton button;

    public ColorTimer(JButton button) {
        this.button = button;
        timer = new Timer(500, new ActionListener() {
            Color color = Color.WHITE;
            public void actionPerformed(ActionEvent e) {
                button.setForeground(color);
                color = (color == Color.WHITE) ? Color.DARK_GRAY : Color.WHITE;
            }
        });
    }

    public void start() {
        timer.start();
    }
}
