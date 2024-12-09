import java.awt.*;

public class ConcreteObservableDecorator extends Decorator {

    public ConcreteObservableDecorator(Component component) {
        super(component);
    }

    @Override
    public void draw(Graphics g) {
        // Delegate drawing to the wrapped component
        super.draw(g);

        // Use getBaseNode to access Node-specific data
        Node node = getBaseNode();
        this.setX(node.getX() + 100);
        this.setY(node.getY() + 20);

        g.setColor(Color.red);
        g.fillOval(this.getX(),this.getY(),20,20);
        g.setColor(Color.black);
        g.drawString("OB",this.getX() + 5,this.getY() + 15);
    }


}
