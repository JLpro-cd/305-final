import java.awt.*;
import java.awt.geom.QuadCurve2D;

/**
 *
 *
 * @author Josue Lopez
 * @author Brendan Holt
 * @version 1.0
 */

public class StrategyDrawLineNode {

    public void createLineNode(Graphics g, String connectorType, Point start, Point end) {
        g.setColor(Color.GREEN);
        g.drawLine(start.x, start.y, end.x, end.y);
        int midX = (start.x + end.x) / 2;
        int midY = (start.y + end.y) / 2;
        g.setColor(Color.BLACK);
        g.drawString(connectorType, midX, midY);
    }
}
