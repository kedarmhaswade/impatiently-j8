package tmp.annotation.corejava;

import java.awt.*;

import javax.swing.*;

/**
 * A frame with a button panel.
 *
 * @author Cay Horstmann
 */
public class ButtonFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    private JPanel panel;
    private JButton yellowButton = new JButton("Yellow");
    private JButton blueButton = new JButton("Blue");
    private JButton redButton = new JButton("Red");
    private ButtonFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        panel = new JPanel();
        add(panel);

        panel.add(yellowButton);
        panel.add(blueButton);
        panel.add(redButton);

        ActionListenerInstaller.processAnnotations(this);
    }

    @ActionListenerFor(source = "yellowButton")
    public void yellowBackground() {
        panel.setBackground(Color.YELLOW);
    }

    @ActionListenerFor(source = "blueButton")
    public void blueBackground() {
        panel.setBackground(Color.BLUE);
    }

    @ActionListenerFor(source = "redButton")
    public void redBackground() {
        panel.setBackground(Color.RED);
    }

    public static void main(String[] args) {
        ButtonFrame mainFrame = new ButtonFrame();
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
