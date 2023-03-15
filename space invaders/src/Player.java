import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.util.List;
import java.util.Random;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class Player extends JLabel {
    private String imagePath;
    public int x;
    public int y;
    public int width;
    public int height;
    private ImageIcon icon;
    private List<Alien> aliens;
    public Player(String imagePath, int x, int y, int width, int height,List<Alien> aliens) {
        this.imagePath = imagePath;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        icon = new ImageIcon(imagePath);
        setIcon(icon);
        setBounds(x, y, width, height);
        setFocusable(true); // set the Player object to be focusable
        final int[] playerX = {x}; // create a local final variable to store the value of x
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        Sprite sprite = new Sprite( playerX[0]+width/2, y, 5, 10,aliens);
                        JPanel gamePanel = (JPanel) getParent(); // get the parent container of the Player object (assumed to be a JPanel)
                        gamePanel.add(sprite);
                        gamePanel.repaint();
                    }
                else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    // move the player left
                    playerX[0] -= 5; // or however much you want to move the player
                    setBounds(playerX[0], y, width, height);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    // move the player right
                    playerX[0] += 5; // or however much you want to move the player
                    setBounds(playerX[0], y, width, height);
                }
            }
        });
    }
        private void shootSprite() {

        }}