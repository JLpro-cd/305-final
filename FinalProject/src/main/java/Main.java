import javax.swing.*;
import java.awt.*;

/**
 * Driver class for the project
 *
 * @author Josue Lopez
 * @author Brendan Holt
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
        JLayeredPane lPane = new JLayeredPane();
        DrawArea drawPanel = new DrawArea();
        CodeArea codePanel = new CodeArea();
        MenuBar menuBar = new MenuBar();
        JTabbedPane tabPane = new JTabbedPane();
		DrawAreaListener drawAreaListener = new DrawAreaListener();

        setLayout(new BorderLayout());
        add(lPane, BorderLayout.CENTER);
		drawPanel.addMouseListener(drawAreaListener);
		drawPanel.addMouseMotionListener(drawAreaListener);
        tabPane.add("Draw Area",drawPanel);
        tabPane.add("Code Area",codePanel);
        tabPane.setBounds(50, 50, 1500, 800);
        lPane.add(tabPane, JLayeredPane.DEFAULT_LAYER);
        menuBar.setBounds(35, 0, 600, 30);
        lPane.add(menuBar, JLayeredPane.PALETTE_LAYER);
        add(lPane);
        Blackboard.getInstance().addPropertyChangeListener(drawPanel);

    }

}
