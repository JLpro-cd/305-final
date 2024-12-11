import java.awt.*;
import java.io.Serializable;

/**
 * Concrete implementation of Decorator, which decorates Node. Represents the Singleton class in the singleton
 * design pattern
 *
 * @author Josue Lopez
 * @author Brendan Holt
 * @version 1.0
 */

public class ConcreteSingletonDecorator extends Decorator implements Serializable {

    public ConcreteSingletonDecorator(){super(null);}

    public ConcreteSingletonDecorator(Component component) {
        super(component);
    }

    /**
     * Draws the Singleton decoration and unwraps while calling draw() on whatever was inside of it.
     */

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        Node node = getBaseNode();
        this.setX(node.getX() + 100);
        this.setY(node.getY() + 40);

        g.setColor(Color.red);
        g.fillOval(this.getX(),this.getY(),20,20);
        g.setColor(Color.black);
        g.drawString("S",this.getX() + 5,this.getY() + 15);
    }
}
