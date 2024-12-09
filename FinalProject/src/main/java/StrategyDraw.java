import java.awt.*;

public interface StrategyDraw {
    // This method is called during the dragging (mouse move while holding)
    public void drawLineWhileDragging(Graphics G, Point start, Point end);

    // This method is called after the mouse is released to finalize the line creation
    public void createLine(Graphics g, Point start, Point end);
}

