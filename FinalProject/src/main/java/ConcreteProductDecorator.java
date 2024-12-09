import java.awt.*;

public class ConcreteProductDecorator extends Decorator {

    int offsetX = 40;
    int offsetY = 100;

    public ConcreteProductDecorator(Component component) {
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
        g.drawString("P",x + 5,y + 15);
    }
}
