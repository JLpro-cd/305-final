import java.awt.*;

public class ConcreteStrategyDecorator extends Decorator {

    public ConcreteStrategyDecorator(Component component) {
        super(component);
    }

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
