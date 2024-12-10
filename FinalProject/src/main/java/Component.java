import java.awt.*;

/**
 * Abstract class used as a common ancestor of Node and Decorator as part of the Decorator design pattern.
 *
 * @author Josue Lopez
 * @author Brendan Holt
 * @version 1.0
 */

public abstract class Component {

    /**
     * Draws the component on the canvas.
     */

    public abstract void draw(Graphics g);
}