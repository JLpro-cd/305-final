import java.awt.*;

/**
 * Concrete implementation of Decorator, which decorates Node. Represents the Decoratable class in the decoration
 * design pattern
 *
 * @author Josue Lopez
 * @author Brendan Holt
 * @version 1.0
 */

public class ConcreteDecoratableDecorator extends Decorator {

    public ConcreteDecoratableDecorator(Component component) {
        super(component);
    }

    /**
     * Draws the Decoratable decoration and unwraps while calling draw() on whatever was inside of it.
     */

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        Node node = getBaseNode();
        this.setX(node.getX() + 100);
        this.setY(node.getY() + 80);

        g.setColor(Color.red);
        g.fillOval(this.getX(),this.getY(),20,20);
        g.setColor(Color.black);
        g.drawString("DB",this.getX() + 5,this.getY() + 15);
    }
}
