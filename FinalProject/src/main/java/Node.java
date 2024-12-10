import java.awt.*;

/**
 * Representation of a class in a UML diagram. Is decorated by Decorator's children, the concrete decorators.
 *
 * @author Josue Lopez
 * @author Brendan Holt
 * @version 1.0
 */

public class Node extends Component {

    private final Rectangle bounds;
    private String label;
    private final static int DEFAULT_SIZE = 100;
    private final DecoratorHolder decorators = new DecoratorHolder();

    public Node(String label, int x, int y, int w, int h) {
        bounds = new Rectangle(x, y, w, h);
        this.label = label;
    }

    public Node (String label, int x, int y) {
		this(label, x, y, DEFAULT_SIZE, DEFAULT_SIZE);
    }

    /**
     * Returns the Node's x coordinate.
     */

    public int getX() {
        return bounds.x;
    }

    /**
     * Returns the Node's y coordinate.
     */

    public int getY() {
        return bounds.y;
    }

    /**
     * Returns the Node's list of Decorators
     */

    public DecoratorHolder getDecoratorHolder() {
        return decorators;
    }

    /**
     * Iterates through the Node's list of Decorators and prints them. Used for debugging.
     */

    public void printDecorators() {
        System.out.println("printing decorators: size = " + decorators.getDecorators().length);
        for (Decorator decorator : decorators.getDecorators()) {
            System.out.println(decorator);
        }
    }

    /**
     * Returns true if a Decorator's type exists in the Node's list of Decorators and false otherwise.
     */

    public boolean checkIfExists(Decorator decorator) {
        for (Decorator existingDecorator : decorators.getDecorators()) {
            if (existingDecorator == null) {continue;}
            if (existingDecorator.getClass().equals(decorator.getClass())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Draws the Node on DrawArea along with its label in the center.
     */

    @Override
    public void draw(Graphics g) {
        int x = bounds.x, y = bounds.y, h = bounds.height, w = bounds.width;
        g.setColor(Color.black);
        g.drawRect(x, y, w, h);
        g.fillRect(x + 1, y + 1, w - 1, h - 1);
        g.setFont(new Font("Courier", Font.PLAIN, 10));
        g.setColor(Color.orange);

        FontMetrics fm = g.getFontMetrics(g.getFont());
        int textWidth = fm.stringWidth(label);
        int textHeight = fm.getHeight();
        int textAscent = fm.getAscent();

        int textX = x + (w - textWidth) / 2;
        int textY = y + (h - textHeight) / 2 + textAscent;
        g.drawString(label, textX, textY);
    }

    /**
     * Sets both the Node's x and y coordinates to the given values.
     */

    public void move(int x, int y) {
        bounds.x = x;
        bounds.y = y;
    }

    /**
     * Returns true if the given coordinates are within the Node's coordinates and false otherwise.
     */

    public boolean contains(int x, int y) {
		return bounds.contains(x, y);
    }

    /**
     * Sets the Node's label.
     */

    public void setLabel(String label) {
		this.label = label;
    }

    /**
     * Returns the Node's label.
     */

    public String getLabel() {
        return label;
    }

    /**
     * Returns the calculated center of the Node's coordinates.
     */

    public Point center() {
        return new Point(
                bounds.x + bounds.width / 2,
                bounds.y + bounds.height / 2
        );
    }

}