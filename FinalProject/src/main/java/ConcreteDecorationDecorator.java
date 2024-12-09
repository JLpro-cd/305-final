import java.awt.*;

public class ConcreteDecorationDecorator extends Decorator {


    public ConcreteDecorationDecorator(Component component) {
        super(component);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        Node node = getBaseNode();
        this.setX(node.getX() + 100);
        this.setY(node.getY() + 60);

        g.setColor(Color.red);
        g.fillOval(this.getX(),this.getY(),20,20);
        g.setColor(Color.black);
        g.drawString("D",this.getX() + 5,this.getY() + 15);
    }
}
