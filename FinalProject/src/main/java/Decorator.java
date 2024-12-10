import java.awt.*;

public abstract class Decorator extends Component {

    protected Component component;
    private int x;
    private int y;
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;

    public Decorator(Component component) {
        this.component = component;
    }

    public Node getBaseNode() { // Version for internal use in concrete decorators
        if (component instanceof Node) {
            return (Node) component;
        } else if (component instanceof Decorator) {
            return ((Decorator) component).getBaseNode();
        }
        throw new IllegalStateException("Base node not found...");
    }

    public static Node getBaseNode(Component comp) { // General version for external uses; handles the casting portion
        if (comp instanceof Node) {
            return (Node) comp;
        } else {
            return getBaseNode(((Decorator) comp).component);
        }
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Point getCenterPoint() {
        return new Point(x + WIDTH / 2, y + HEIGHT / 2);
    }

    public void draw(Graphics g) {
        if (component != null) {
            component.draw(g);
        }
    }
}
