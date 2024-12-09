import java.awt.*;

public class ConcreteStrategyDecorator extends Decorator {

    int offsetX = 80;
    int offsetY = 100;

    public ConcreteStrategyDecorator(Component component) {
        super(component);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        Node node = getBaseNode();
        int x = node.getX() + offsetX;
        int y = node.getY() + offsetY;

        g.setColor(Color.red);
        g.fillOval(x,y,20,20);
        g.setColor(Color.black);
        g.drawString("ST",x + 5,y + 15);
    }
}
