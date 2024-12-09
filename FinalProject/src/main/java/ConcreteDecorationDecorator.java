import java.awt.*;

public class ConcreteDecorationDecorator extends Decorator {

    int offsetX = 100;
    int offsetY = 60;

    public ConcreteDecorationDecorator(Component component) {
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
        g.drawString("D",x + 5,y + 15);
    }
}
