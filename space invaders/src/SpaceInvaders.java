import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;


public class SpaceInvaders {
    public static void main(String[] args) throws IOException, FontFormatException {
        ImagePanel panel = new ImagePanel("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\360_F_264279006_WDXxV3OHjAOoHqH7iiLDrg23p0947g7U.jpg");
        panel.setLayout(new BorderLayout());

        JFrame frame = new JFrame("Button Over Background Example");
        frame.add(panel);
        frame.setSize(500, 700);

        Button button = new Button("PLAY GAME!", "C:\\Users\\andre\\IdeaProjects\\calculator\\src\\ARCADECLASSIC.ttf");
        panel.add(button, BorderLayout.CENTER);

        ColorTimer timer = new ColorTimer(button);
        timer.start();

        ArrayList<Alien> aliens = new ArrayList<Alien>();



        aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\alien-of-game (1).png",140, 70, 32, 32,aliens));
        aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\alien-of-game (1).png",100, 70, 32, 32,aliens));
        aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\alien-of-game (1).png",180, 70, 32, 32,aliens));
        aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\alien-of-game (1).png",220, 70, 32, 32,aliens));
        aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\alien-of-game (1).png",260, 70, 32, 32,aliens));
        aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\alien-of-game (1).png",300, 70, 32, 32,aliens));
        aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\alien-of-game (1).png",340, 70, 32, 32,aliens));



        aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\pixelated-alien.png",100, 100, 32, 32,aliens));
        aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\pixelated-alien.png",140, 100, 32, 32,aliens));
        aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\pixelated-alien.png",180, 100, 32, 32,aliens));
        aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\pixelated-alien.png",220, 100, 32, 32,aliens));
        aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\pixelated-alien.png",260, 100, 32, 32,aliens));
        aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\pixelated-alien.png",300, 100, 32, 32,aliens));
        aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\pixelated-alien.png",340, 100, 32, 32,aliens));


        aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\pixelated-alien.png",100, 130, 32, 32,aliens));
        aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\pixelated-alien.png",140, 130, 32, 32,aliens));
        aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\pixelated-alien.png",180, 130, 32, 32,aliens));
        aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\pixelated-alien.png",220, 130, 32, 32,aliens));
        aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\pixelated-alien.png",260, 130, 32, 32,aliens));
        aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\pixelated-alien.png",300, 130, 32, 32,aliens));
        aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\pixelated-alien.png",340, 130, 32, 32,aliens));


        aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\alien-pixelated-shape-of-a-digital-game.png",100, 160, 32, 32,aliens));
        aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\alien-pixelated-shape-of-a-digital-game.png",140, 160, 32, 32,aliens));
        aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\alien-pixelated-shape-of-a-digital-game.png",180, 160, 32, 32,aliens));
        aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\alien-pixelated-shape-of-a-digital-game.png",220, 160, 32, 32,aliens));
        aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\alien-pixelated-shape-of-a-digital-game.png",260, 160, 32, 32,aliens));
        aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\alien-pixelated-shape-of-a-digital-game.png",300, 160, 32, 32,aliens));
        aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\alien-pixelated-shape-of-a-digital-game.png",340, 160, 32, 32,aliens));


       aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\alien-pixelated-shape-of-a-digital-game.png",100, 190, 32, 32,aliens));
       // aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\alien-pixelated-shape-of-a-digital-game.png",140, 190, 32, 32,aliens));
       /// aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\alien-pixelated-shape-of-a-digital-game.png",180, 190, 32, 32,aliens));
       // aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\alien-pixelated-shape-of-a-digital-game.png",220, 190, 32, 32,aliens));
      //  aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\alien-pixelated-shape-of-a-digital-game.png",260, 190, 32, 32,aliens));
       // aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\alien-pixelated-shape-of-a-digital-game.png",300, 190, 32, 32,aliens));
       //  aliens.add(new Alien("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\alien-pixelated-shape-of-a-digital-game.png",340, 190, 32, 32,aliens  ));
        Player player = new Player("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\icons8-spaceship-64.png", 220, 550, 64, 64,aliens);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.remove(button);
                panel.validate();
                panel.repaint();
             //   panel.displayImage("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\love-always-wins.png",450, 10, 30, 30);
              //  panel.displayImage("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\love-always-wins.png",425, 10, 30, 30);
              //   panel.displayImage("C:\\Users\\andre\\IdeaProjects\\calculator\\src\\love-always-wins.png",400, 10, 30, 30);


                for (Alien alien : aliens) {
                    panel.add(alien);

                } panel.add(player);
                player.requestFocusInWindow();

                AlienMover mover = new AlienMover(aliens);

                mover.start();
               // //AlienShooter shooter = new AlienShooter(aliens);
               // shooter.start();

            }
        });

        frame.setVisible(true);
    }
}