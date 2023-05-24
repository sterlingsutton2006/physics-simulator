import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Time;
import java.util.ArrayList;

public class Simulation extends JPanel implements ActionListener {
    ArrayList<Ball> balls;
    float accG;
    Image icon;
    final int PIXELS_PER_METER = 20;
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
        accG = gravitationalAcceleration;
        icon = new ImageIcon("resources/ball.png").getImage();
        setBackground(Color.BLACK);

        Timer timer = new Timer((int) (frameTime * 1000), this);
        timer.start();
    }
    public void updateBalls() {
        for (Ball ball : balls) {
            ball.update(DT, accG);
        }
    }

    public void addBall(float posX, float posY, float velX, float velY, float accX, float accY, float rad, float mass) {
        balls.add(new Ball(posX, posY, velX, velY, accX, accY, rad, mass));
    }


    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);


        for (Ball ball : balls) {
            int radius = (int) (ball.rad * PIXELS_PER_METER);
            int posX = (int) (ball.posX * PIXELS_PER_METER) - radius;
            int posY = getHeight() - (int) (ball.posY * PIXELS_PER_METER) - radius;

            g.drawImage(icon, posX, posY, 2 * radius, 2 * radius, null);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateBalls();
        repaint();
    }
}
