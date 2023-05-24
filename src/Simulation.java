import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class Simulation extends JPanel {
    ArrayList<Ball> balls;
    float accG;
    Image icon;
    final int PIXELS_PER_METER = 50;
    final float X_BOUND;
    final float Y_BOUND;
    final float DT;

    public Simulation(float gravitationalAcceleration, int width, int height, float frameTime) {
        super();
        DT = frameTime;
        X_BOUND = (float) width / PIXELS_PER_METER;
        Y_BOUND = (float) height / PIXELS_PER_METER;
        setPreferredSize(new Dimension(width, height));
        balls = new ArrayList<>();
        balls.add(new Ball(10, 10, 0, 0, 0, 0, 0, 0));
        accG = gravitationalAcceleration;
        icon = new ImageIcon("resources/ball.png").getImage();
        setBackground(Color.BLACK);
    }
    public void updateBalls() {
        for (Ball ball : balls) {
            ball.update(DT, accG);
        }
    }


    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        int posX = 0;
        int posY = 0;

        for (Ball ball : balls) {
            posX = (int) (ball.posX * PIXELS_PER_METER);
            posY = getHeight() - (int) (ball.posY * PIXELS_PER_METER);
            g.drawImage(icon, posX, posY, null);
        }
    }
}
