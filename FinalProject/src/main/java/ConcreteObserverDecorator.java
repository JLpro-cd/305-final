import java.awt.*;
import java.io.Serializable;

/**
 * Concrete implementation of Decorator, which decorates Node. Represents the Observer class in the observer
 * design pattern
 *
 * @author Josue Lopez
 * @author Brendan Holt
 * @version 1.0
 */

public class ConcreteObserverDecorator extends Decorator implements Serializable {

    public ConcreteObserverDecorator() {super(null);}

    public ConcreteObserverDecorator(Component component) {
        super(component);
    }

    /**
     * Draws the Observer decoration and unwraps while calling draw() on whatever was inside of it.
     */

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        Node node = getBaseNode();
        this.setX(node.getX() + 100);
        this.setY(node.getY());

        g.setColor(Color.red);
        g.fillOval(this.getX(),this.getY(),20,20);
        g.setColor(Color.black);
        g.drawString("O",this.getX() + 5,this.getY() + 15);
    }
}
