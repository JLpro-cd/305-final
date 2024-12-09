import java.awt.*;

public interface StrategyDraw {

    // This method is called after the mouse is released to finalize the line creation
    public void createLine(Graphics g, Point start, Point end);
}

