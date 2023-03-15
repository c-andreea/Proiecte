import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {
    private BufferedImage image;

    public ImagePanel(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }



    public void displayImage(String imagePath, int x, int y, int width, int height) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            JLabel label = new JLabel(new ImageIcon(resizedImage));
            label.setBounds(x, y, width, height);
            add(label);
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }

}


}
