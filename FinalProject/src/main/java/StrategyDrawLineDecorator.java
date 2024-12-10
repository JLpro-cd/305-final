import java.awt.*;

public class StrategyDrawLineDecorator{

    public void createLine(Graphics g, Point start, Point end) {
        g.setColor(Color.RED);
        g.drawLine(start.x, start.y, end.x, end.y);
    }
}

