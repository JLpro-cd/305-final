import java.awt.*;

public class ConcreteObservableDecorator extends Decorator {
    private int offsetX = 100;
    private int offsetY = 20;


    public ConcreteObservableDecorator(Component component) {
        super(component);
    }

    @Override
    public void draw(Graphics g) {
        // Delegate drawing to the wrapped component
        super.draw(g);

        // Use getBaseNode to access Node-specific data
        Node node = getBaseNode();
        int x = node.getX() + offsetX;
        int y = node.getY() + offsetY;

        g.setColor(Color.red);
        g.fillOval(x,y,20,20);
        g.setColor(Color.black);
        g.drawString("OB",x + 5,y + 15);
    }


}
