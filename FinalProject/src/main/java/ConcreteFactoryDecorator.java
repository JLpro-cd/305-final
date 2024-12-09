import java.awt.*;

public class ConcreteFactoryDecorator extends Decorator {

    public ConcreteFactoryDecorator(Component component) {
        super(component);
    }
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
