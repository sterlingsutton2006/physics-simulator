import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Simulation extends JPanel implements ActionListener {
    ArrayList<Ball> balls;
    float accG;
    Image icon;
    final int PIXELS_PER_METER = 10;
    final float X_BOUND;
    final float Y_BOUND;
    final float DT;

    public Simulation(float gravitationalAcceleration, int width, int height, float frameTime) {
        super();
        DT = frameTime;
        X_BOUND = (float) width / PIXELS_PER_METER;
        Y_BOUND = (float) height / PIXELS_PER_METER;
        balls = new ArrayList<>();
        accG = gravitationalAcceleration;
        icon = new ImageIcon("resources/ball.png").getImage();
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(width, height));

        Timer timer = new Timer((int) (frameTime * 1000), this);
        timer.start();
    }
    public void updateBalls() {
        for (Ball ball : balls) {
            ball.update(DT, accG);
        }
        handleCollision();
    }

    public void addBall(float posX, float posY, float velX, float velY, float accX, float accY, float rad, float mass) {
        balls.add(new Ball(posX, posY, velX, velY, accX, accY, rad, mass));
    }

    public void handleCollision() {

        for (Ball ball : balls) {
            boundaryCollision(ball);
        }

        for (int a = 0; a < balls.size(); a++) {
            for (int b = a + 1; b < balls.size(); b++) {
                if (balls.get(a) != balls.get(b) && isColliding(balls.get(a), balls.get(b))) {
                    collisionResponse(balls.get(a), balls.get(b));
                }
            }
        }
    }

    public void boundaryCollision(Ball ball) {
        float multiplier = -1;

        if (ball.posX + ball.rad >= X_BOUND) {
            ball.posX = X_BOUND - ball.rad;
            ball.velX *= multiplier;
        }
        if (ball.posX - ball.rad <= 0) {
            ball.posX = ball.rad;
            ball.velX *= multiplier;
        }
        if (ball.posY + ball.rad >= Y_BOUND) {
            ball.posY = Y_BOUND - ball.rad;
            ball.velY *= multiplier;
        }
        if (ball.posY - ball.rad <= 0) {
            ball.posY = ball.rad;
            ball.velY *= multiplier;
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

        // new position
        float dx = (a.posX - b.posX);
        float dy = (a.posY - b.posY);
        double angle = Math.atan2(dy, dx);
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        float distanceMinusRadii = distance - a.rad - b.rad;
        float moveX = (float) (distanceMinusRadii * Math.cos(angle) / 2f);
        float moveY = (float) (distanceMinusRadii * Math.sin(angle) / 2f);

        b.posX += moveX;
        b.posY += moveY;
        a.posX -= moveX;
        a.posY -= moveY;

        // new velocity
        float newVelXA = (a.velX * (a.mass - b.mass) + (2 * b.mass * b.velX)) / (a.mass + b.mass);
        float newVelYA = (a.velY * (a.mass - b.mass) + (2 * b.mass * b.velY)) / (a.mass + b.mass);
        float newVelXB = (b.velX * (b.mass - a.mass) + (2 * a.mass * a.velX)) / (a.mass + b.mass);
        float newVelYB = (b.velY * (b.mass - a.mass) + (2 * a.mass * a.velY)) / (a.mass + b.mass);

        a.velX = newVelXA;
        a.velY = newVelYA;
        b.velX = newVelXB;
        b.velY = newVelYB;

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
