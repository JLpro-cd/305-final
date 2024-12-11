import java.awt.*;
import java.io.Serializable;

/**
 * Concrete implementation of Decorator, which decorates Node. Represents the Strategy class in the strategy
 * design pattern
 *
 * @author Josue Lopez
 * @author Brendan Holt
 * @version 1.0
 */

public class ConcreteStrategyDecorator extends Decorator implements Serializable {

    public ConcreteStrategyDecorator(){super(null);}

    public ConcreteStrategyDecorator(Component component) {
        super(component);
    }

    /**
     * Draws the Decoratable decoration and unwraps while calling draw() on whatever was inside of it.
     */

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        Node node = getBaseNode();
        this.setX(node.getX() + 80);
        this.setY(node.getY() + 100);

        g.setColor(Color.red);
        g.fillOval(this.getX(),this.getY(),20,20);
        g.setColor(Color.black);
        g.drawString("ST",this.getX() + 5,this.getY() + 15);
    }
}
