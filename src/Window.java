import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame {
    private Simulation simulation;
    public final int WINDOW_WIDTH;
    public final int WINDOW_HEIGHT;
    public final int FRAME_RATE;

    public Window(int width, int height, int fps) {

        this.WINDOW_WIDTH = width;
        this.WINDOW_HEIGHT = height;
        this.FRAME_RATE = fps;

        setTitle("Physics Simulation");
        setIconImage(new ImageIcon("resources/ball.png").getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        simulation = new Simulation(-9.81f, width, height, 1f / FRAME_RATE);
        add(simulation);

        pack();
        setResizable(false);
        setVisible(true);

        simulation.addBall(0, 0, 10, 25, 0, 0, 1, 1);
        simulation.addBall(60, 0, -10, 25, 0, 0, 1, 1);
        simulation.addBall(30, 10, 0, 20, 0, 0, 2, 3);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Window(1280, 720, 60));
    }
}
