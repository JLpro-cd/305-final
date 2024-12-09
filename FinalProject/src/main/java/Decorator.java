import java.awt.*;

public abstract class Decorator extends Component {
    protected Component component;
    private int x;
    private int y;

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

    public void draw(Graphics g) {
        if (component != null) {
            component.draw(g);
        }
    }


}
