import java.awt.*;
import java.io.Serializable;

/**
 * Abstract class which extends Component, a common ancestor of it and Node, which its children decorate. Extended
 * further by concrete decorations.
 *
 * @author Josue Lopez
 * @author Brendan Holt
 * @version 1.0
 */

public abstract class Decorator extends Component implements Serializable {

    protected Component component;
    private int x;
    private int y;
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;

    Decorator(){this.component = null;}

    public Decorator(Component component) {
        this.component = component;
    }

    /**
     * Returns the base Node within a Decorator or the Node itself if it's already a Node object. Parameterless version
     * used only internally by concrete decorators.
     */

    public Node getBaseNode() {
        if (component instanceof Node) {
            return (Node) component;
        } else if (component instanceof Decorator) {
            return ((Decorator) component).getBaseNode();
        }
        throw new IllegalStateException("Base node not found...");
    }

    /**
     * Returns the base node within a Decorator or the Node itself if it's already a Node object. General use version
     * used by other classes, taking in a Component to handle casting issues.
     */

    public static Node getBaseNode(Component comp) { // General version for external uses; handles the casting portion
        if (comp instanceof Node) {
            return (Node) comp;
        } else {
            return getBaseNode(((Decorator) comp).component);
        }
    }

    /**
     * Sets the Decorator's x coordinate.
     */

    public void setX(int x){
        this.x = x;
    }

    /**
     * Sets the Decorator's y coordinate.
     */

    public void setY(int y){
        this.y = y;
    }

    /**
     * Returns the Decorator's x coordinate.
     */

    public int getX(){
        return x;
    }

    /**
     * Returns the Decorator's y coordinate.
     */

    public int getY(){
        return y;
    }

    /**
     * Calculates and returns the Decorator's center point based on its coordinates.
     */

    public Point getCenterPoint() {
        return new Point(x + WIDTH / 2, y + HEIGHT / 2);
    }

    /**
     * Draws the Decorator's component.
     */

    public void draw(Graphics g) {
        if (component != null) {
            component.draw(g);
        }
    }
}
