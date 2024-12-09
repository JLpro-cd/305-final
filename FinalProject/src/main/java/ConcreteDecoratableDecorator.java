import java.awt.*;

public class ConcreteDecoratableDecorator extends Decorator {

    int offsetX = 100;
    int offsetY = 80;

    public ConcreteDecoratableDecorator(Component component) {
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
        g.drawString("DB",x + 5,y + 15);
    }
}
