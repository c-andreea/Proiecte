import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.util.Random;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class Sprite extends JLabel {
    public final int x;
    public int y;
    public final int width;
    public final int height;
    private final List<Alien> aliens;
    private final Timer timer;

    public Sprite(int x, int y, int width, int height, List<Alien> aliens) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.aliens = aliens;
        setBounds(x, y, width, height);
        setOpaque(true); // make the sprite visible
        setBackground(Color.BLUE); // set the sprite color to blue
        final int[] spritey = {y};
   timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spritey[0] -= 5;
                setBounds(x, spritey[0], width, height);
                checkCollisions();
                if (spritey[0] < 0) {
                    ((Timer) e.getSource()).stop();

                    JPanel gamePanel = (JPanel) getParent();
                    gamePanel.remove(Sprite.this);
                    gamePanel.repaint();
                }
            }
        });
        timer.start();
    }
    public void moveDown() {
        y += 5; // Move the sprite down by 5 pixels
        setBounds(x, y, width, height); // Update the sprite's position
    }
    private void checkCollisions() {
        Rectangle spriteBounds = getBounds();
        for (Alien alien : aliens) {
            Rectangle alienBounds = alien.getBounds();

            if (spriteBounds.intersects(alienBounds)) {
                alien.removeFromContainer();
                aliens.remove(alien);

                JPanel gamePanel = (JPanel) getParent();
                timer.stop();
                gamePanel.remove(this);
                gamePanel.repaint();
                break; // exit the loop since we only need to remove one Alien object
            }
        }
    }
}
