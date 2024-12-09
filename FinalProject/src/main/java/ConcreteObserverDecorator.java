import java.awt.*;

public class ConcreteObserverDecorator extends Decorator {
    private int offsetX = 100;
    private int offsetY = 0;

    public ConcreteObserverDecorator(Component component) {
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
        g.drawString("O",x + 5,y + 15);
        //g.fill
        //g.drawString("Observer", x, y);
    }
}
