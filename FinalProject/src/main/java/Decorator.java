import java.awt.*;

public abstract class Decorator extends Component {
    protected Component component;

    public Decorator(Component component) {
        this.component = component;
    }



    public Node getBaseNode() {
        if (component instanceof Node) {
            return (Node) component;
        } else if (component instanceof Decorator) {
            return ((Decorator) component).getBaseNode();
        }
        throw new IllegalStateException("Base node not found...");
    }

    public void draw(Graphics g) {
        if (component != null) {
            component.draw(g);
        }
    }

}
