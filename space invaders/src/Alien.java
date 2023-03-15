import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Alien extends JLabel {
    private String imagePath;
    public int x;
    public int y;
    public int width;
    public int height;
    private List<Alien> aliens;
    private Timer timer;
    public Alien(String imagePath, int x, int y, int width, int height,List<Alien> aliens) {
        this.imagePath = imagePath;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.aliens= aliens;
        setIcon(new ImageIcon(imagePath));
        setBounds(x, y, width, height);
    }
    private List<AlienSprite> sprites = new ArrayList<>();

    // A method to return the list of sprites
    public List<AlienSprite> getSprites() {
        return sprites;
    }
    public void removeFromContainer() {
        Container container = this.getParent();
        JPanel gamePanel = (JPanel) getParent();
        gamePanel.remove(this);
        if (aliens != null) {
            aliens.remove(this);
        }
        container.repaint();


    }

    public void shoot() {
        //Create the sprite and set its initial position
        AlienSprite sprite = new AlienSprite(this.x + (this.width / 2), this.y + this.height, 10, 10, aliens);

        // Add the sprite to the list of sprites
        List<AlienSprite> sprites = getSprites();
        sprites.add(sprite);


        // Add the sprite to the game panel
       JPanel gamePanel = (JPanel) getParent();
        gamePanel.add(sprite);

        // Repaint the game panel to show the sprite
       gamePanel.repaint();
        Timer shooterTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Choose a random alien to shoot the sprite
                Random random = new Random();
                int index = random.nextInt(aliens.size());
                Alien alien = aliens.get(index);
                alien.shoot();

                // Move all sprites down
                List<AlienSprite> sprites = alien.getSprites();
                for (AlienSprite sprite : sprites) {
                    sprite.moveDown();
                }
            }
        });
        shooterTimer.start();

    }
}
