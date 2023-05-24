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
        balls.add(new Ball(0, 0, 10, 10, 0, 0, 0, 0));
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


    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);


        for (Ball ball : balls) {
            int posX = (int) (ball.posX * PIXELS_PER_METER);
            int posY = getHeight() - (int) (ball.posY * PIXELS_PER_METER);
            g.drawImage(icon, posX, posY, null);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateBalls();
        repaint();
    }
}
