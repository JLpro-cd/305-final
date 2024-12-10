import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * DrawPanel is a JPanel where the user can draw nodes and their connections
 *
 * @author javiergs
 * @version 1.0
 */
public class DrawArea extends JPanel implements PropertyChangeListener {

    private final StrategyDrawLineDecorator strategyDrawLineDecorator = new StrategyDrawLineDecorator();
    private final StrategyDrawLineNode strategyDrawLineNode = new StrategyDrawLineNode();

    public DrawArea() {
        setBackground(Color.WHITE);
    }

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
                strategyDrawLineNode.createLine(g, connectorType, startPoint, endPoint);
            }
        }

        g2.setColor(new Color(74, 136, 98, 255));
        for (int i = 0; i < Blackboard.getInstance().size(); i++) { // Draw Nodes themselves
            Blackboard.getInstance().get(i).draw(g2);
        }

        if (!Blackboard.getInstance().getDecoratorLines().isEmpty()){ // Draw decoration lines
            for (int i = 0; i < Blackboard.getInstance().getDecoratorLines().size(); i++) {
                ArrayList<Point> line = Blackboard.getInstance().getDecoratorLines().get(i);
                strategyDrawLineDecorator.createLine(g, line.get(0), line.get(1));
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }

}
