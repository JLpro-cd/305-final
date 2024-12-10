import java.awt.*;

/**
 * Class for drawing lines.
 *
 * @author Josue Lopez
 * @author Brendan Holt
 * @version 1.0
 */

public class DrawLine {

    /**
     * Draws a line connecting two UML classes (Nodes) and their connection type.
     */

    public void createLineNode(Graphics g, String connectorType, Point start, Point end) {
        g.setColor(Color.GREEN);
        g.drawLine(start.x, start.y, end.x, end.y);
        int midX = (start.x + end.x) / 2;
        int midY = (start.y + end.y) / 2;
        g.setColor(Color.BLACK);
        g.drawString(connectorType, midX, midY);
    }

    /**
     * Draws a line connecting two Decorators.
     */

    public void createLineDecorator(Graphics g, Point start, Point end) {
        g.setColor(Color.RED);
        g.drawLine(start.x, start.y, end.x, end.y);
    }
}

