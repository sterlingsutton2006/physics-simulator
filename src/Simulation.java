import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            handleCollision();
        }
    }

    public void addBall(float posX, float posY, float velX, float velY, float accX, float accY, float rad, float mass) {
        balls.add(new Ball(posX, posY, velX, velY, accX, accY, rad, mass));
    }

    public void handleCollision() {
        /*
        for (int i = 0; i < balls.size(); i++) {
            for (int j = i + 1; j < balls.size(); i++) {
                if (isColliding(balls.get(i), balls.get(j))) {
                    collisionResponse(balls.get(i), balls.get(j));
                }
            }
        }
         */

        for (Ball ball : balls) {
            boundaryCollision(ball);
        }
    }

    public void boundaryCollision(Ball ball) {
        if (ball.posX + ball.rad >= X_BOUND) {
            ball.posX = X_BOUND - ball.rad;
            ball.velX *= -1;
        }
        if (ball.posX - ball.rad <= 0) {
            ball.posX = ball.rad;
            ball.velX *= -1;
        }
        if (ball.posY + ball.rad >= Y_BOUND) {
            ball.posY = Y_BOUND - ball.rad;
            ball.velY *= -1;
        }
        if (ball.posY - ball.rad <= 0) {
            ball.posY = ball.rad;
            ball.velY *= -1;
        }
    }

    public boolean isColliding(Ball a, Ball b) {
        float sumRadiiSquared = a.rad * a.rad + b.rad * b.rad;
        float dx = a.posX - b.posX;
        float dy = a.posY - b.posY;
        float distanceSquared = dx * dx + dy * dy;

        return distanceSquared <= sumRadiiSquared;
    }

    public void collisionResponse(Ball a, Ball b) {
        float dx = a.posX - b.posX;
        float dy = a.posY - b.posY;
        float angle = (float) Math.atan2(dy, dx);


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
