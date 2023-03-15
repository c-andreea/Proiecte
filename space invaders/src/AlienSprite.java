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

public class AlienSprite extends JLabel {
    public final int x;
    public int y;
    public final int width;
    public final int height;
    private final List<Alien> aliens;
    private final Timer timer;
    private boolean running = true;
    public AlienSprite(int x, int y, int width, int height, List<Alien> aliens) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.aliens = aliens;
        setBounds(x, y, width, height);
        setOpaque(true); // make the sprite visible
        setBackground(Color.RED); // set the sprite color to blue
        final int[] spritey = {y};
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (spritey[0] < 0) {
                    ((Timer) e.getSource()).stop();

                    JPanel gamePanel = (JPanel) getParent();
                    gamePanel.remove(AlienSprite.this);
                    gamePanel.repaint();
                }
            }
        });
        timer.start();
    }
    public void moveDown() {
        y += 3; // Move the sprite down by 5 pixels
        setBounds(x, y, width, height); // Update the sprite's position
    }
    public void shootRandomAlien() {
        Random random = new Random();
        int index = random.nextInt(aliens.size());
        Alien alien = aliens.get(index);
        alien.shoot();
    }
    public void run() {
        while (running) {
            shootRandomAlien();
            try {
                Thread.sleep(1000); // pause for 1 second before shooting again
            } catch (InterruptedException e) {
                // do nothing
            }
        }
    }

}
