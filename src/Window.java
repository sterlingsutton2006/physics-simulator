import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame implements WindowListener {
    private Simulation simulation;
    public final int WINDOW_WIDTH;
    public final int WINDOW_HEIGHT;
    public final int FRAME_RATE;
    private boolean isRunning;
    public Window(int width, int height, int fps) {
        isRunning = true;

        addWindowListener(this);

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

        new Thread(this::startSimulationLoop);
        //startSimulationLoop();

    }
    private void startSimulationLoop() {
        float frameTime = 1000f / FRAME_RATE;

        while (isRunning) {
            simulation.updateBalls();

            simulation.repaint();

            try {
                Thread.sleep((long) frameTime);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Window(1280, 720, 60));
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        isRunning = false;
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
