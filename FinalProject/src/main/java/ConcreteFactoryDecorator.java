import java.awt.*;
import java.io.Serializable;

/**
 * Concrete implementation of Decorator, which decorates Node. Represents the Factory class in the factory
 * design pattern
 *
 * @author Josue Lopez
 * @author Brendan Holt
 * @version 1.0
 */

public class ConcreteFactoryDecorator extends Decorator implements Serializable {

    public ConcreteFactoryDecorator() {super(null);}

    public ConcreteFactoryDecorator(Component component) {
        super(component);
    }

    /**
     * Draws the Factory decoration and unwraps while calling draw() on whatever was inside of it.
     */

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        Node node = getBaseNode();
        this.setX(node.getX() + 60);
        this.setY(node.getY() + 100);

        g.setColor(Color.red);
        g.fillOval(this.getX(),this.getY(),20,20);
        g.setColor(Color.black);
        g.drawString("F",this.getX() + 5,this.getY() + 15);
    }
}
