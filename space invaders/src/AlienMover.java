import java.lang.Thread;
import java.util.ArrayList;
import java.util.Random;

public class AlienMover extends Thread {
    private ArrayList<Alien> alien;
    private boolean running = true;

    public AlienMover(ArrayList<Alien> alien) {
        this.alien = alien;
    }

    public void run() {

        int direction = 5;
        int dropDistance = 20;

        while (running) {

            boolean atEdge = false;
            for (Alien alien1 : alien) {
                if (alien1.x + alien1.width >= 460 || alien1.x <= 20) {
                    atEdge = true;
                    break;
                }
            }


            if (atEdge) {
                for (Alien dropAlien : alien) {
                    dropAlien.y += dropDistance;
                }
                direction *= -1;
            }

            // Move all aliens horizontally
            for (Alien alien1 : alien) {
                alien1.x += direction;
                alien1.setBounds(alien1.x, alien1.y, alien1.width, alien1.height);
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // Do nothing
            }
        }
    }
}
