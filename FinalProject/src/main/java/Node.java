import java.awt.*;
import java.util.ArrayList;

/**
 * Node represents a node in the graph
 *
 * @author javiergs
 * @version 1.0
 */
public class Node extends Component {

    private final Rectangle bounds;
    private String label;
    private final static int DEFAULT_SIZE = 100;
    private ArrayList<Decorator> decorators = new ArrayList<Decorator>();

    private final StrategyDraw strategyDraw = new StrategyDrawLineCurved();

    public Node(String label, int x, int y, int w, int h) {
        bounds = new Rectangle(x, y, w, h);
        this.label = label;
    }

    public Node (String label, int x, int y) {
		this(label, x, y, DEFAULT_SIZE, DEFAULT_SIZE);
    }

    public int getX() {
        return bounds.x;
    }

    public int getY() {
        return bounds.y;
    }

    public ArrayList<Decorator> getDecorators() {
        return decorators;
    }

    public void printDecorators() {
        System.out.println("printing decorators: size = " + decorators.size());
        for (Decorator decorator : decorators) {
            System.out.println(decorator);
        }
    }

    public boolean checkIfExists(Decorator decorator) {
        // Iterate through the list of decorators
        for (Decorator existingDecorator : decorators) {
            // Check if the type (class) of the decorator already exists in the list
            if (existingDecorator.getClass().equals(decorator.getClass())) {
                return true;  // If a decorator of the same type exists, return true
            }
        }
        return false;  // If no decorator of the same type is found, return false
    }

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

    public void move(int x, int y) {
        bounds.x = x;
        bounds.y = y;
    }

    public void drawConnect(Node b, Graphics2D g) {
        strategyDraw.drawConnect(this, b, g);
    }

    public boolean contains(int x, int y) {
		return bounds.contains(x, y);
    }

    public void setLabel(String label) {
		this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public Point center() {
        return new Point(
                bounds.x + bounds.width / 2,
                bounds.y + bounds.height / 2
        );
    }

}