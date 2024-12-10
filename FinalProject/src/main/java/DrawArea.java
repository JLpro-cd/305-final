import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * Holds the canvas onto which all graphics are drawn on. Observes Blackboard.
 *
 * @author Josue Lopez
 * @author Brendan Holt
 * @version 1.0
 */

public class DrawArea extends JPanel implements PropertyChangeListener {

    private final StrategyDrawLine strategyDrawLine = new StrategyDrawLine();

    public DrawArea() {
        setBackground(Color.WHITE);
    }

    /**
     * Called by Blackboard, it refreshes the Canvas by iterating through every object (Nodes, Decorators, and their
     * respective lines) and drawing them appropriately.
     */

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw grid as background
        g.setColor(new Color(250, 250, 250, 255));
        for (int i = 0; i < getWidth(); i += 20) {
            g.drawLine(i, 0, i, getHeight());
        }
        for (int i = 0; i < getHeight(); i += 20) {
            g.drawLine(0, i, getWidth(), i);
        }

        Graphics2D g2 = (Graphics2D) g;
        if (!Blackboard.getInstance().getNodeLines().isEmpty()) { // Draw node lines
            for (int i = 0; i < Blackboard.getInstance().getNodeLines().size(); i++) {
                Point startPoint = Blackboard.getInstance().getNodeLines().get(i).getStart();
                Point endPoint = Blackboard.getInstance().getNodeLines().get(i).getEnd();
                String connectorType = Blackboard.getInstance().getNodeLines().get(i).getConnectionType();
                strategyDrawLine.createLineNode(g, connectorType, startPoint, endPoint);
            }
        }

        g2.setColor(new Color(74, 136, 98, 255));
        for (int i = 0; i < Blackboard.getInstance().size(); i++) { // Draw Nodes themselves
            Blackboard.getInstance().get(i).draw(g2);
        }

        if (!Blackboard.getInstance().getDecoratorLines().isEmpty()){ // Draw decoration lines
            for (int i = 0; i < Blackboard.getInstance().getDecoratorLines().size(); i++) {
                ArrayList<Point> line = Blackboard.getInstance().getDecoratorLines().get(i);
                strategyDrawLine.createLineDecorator(g, line.get(0), line.get(1));
            }
        }
    }

    /**
     * Triggers paintComponent when Blackbaord calls repaint().
     */

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }

}
