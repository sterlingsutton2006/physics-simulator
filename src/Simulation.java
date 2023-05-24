import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class Simulation extends JPanel {
    ArrayList<Ball> balls;
    float accG;
    Image icon;
    final int PIXELS_PER_METER = 50;

    public Simulation(float gravitationalAcceleration) {
        super();
        balls = new ArrayList<>();
        balls.add(new Ball(10, 10, 0, 0, 0, 0, 0, 0));
        accG = gravitationalAcceleration;
        icon = new ImageIcon("resources/ball.png").getImage();
        setBackground(Color.BLACK);
    }


    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        for (Ball ball : balls) {
            g.drawImage(icon, (int) (ball.posX * PIXELS_PER_METER), getHeight() - (int) (ball.posY * PIXELS_PER_METER), null);
        }
    }
}
