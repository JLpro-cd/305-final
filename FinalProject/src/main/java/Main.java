import javax.swing.*;

/**
 *
 *
 * @author Josue Lopez
 * @author Brendan Holt
 * @author javiergs
 * @version 1.0
 */
public class Main extends JFrame {

    public static void main(String[] args) {
        Main main = new Main();
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setTitle("Project Builder");
        main.setSize(1600, 900);
        main.setLocationRelativeTo(null);
        main.setResizable(false);
        main.setVisible(true);
    }

    public Main() {
        DrawArea drawPanel = new DrawArea();
        JTabbedPane tabPane = new JTabbedPane();
		DrawAreaListener drawAreaListener = new DrawAreaListener();
		drawPanel.addMouseListener(drawAreaListener);
		drawPanel.addMouseMotionListener(drawAreaListener);
        tabPane.add("Draw Area",drawPanel);
        add(tabPane);
        Blackboard.getInstance().addPropertyChangeListener(drawPanel);
    }

}
