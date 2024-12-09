import java.awt.*;

public class ConcreteObserverDecorator extends Decorator {

    public ConcreteObserverDecorator(Component component) {
        super(component);
    }


    @Override
    public void draw(Graphics g) {
        super.draw(g);

        Node node = getBaseNode();
        this.setX(node.getX() + 100);
        this.setY(node.getY() + 0);

        g.setColor(Color.red);
        g.fillOval(this.getX(),this.getY(),20,20);
        g.setColor(Color.black);
        g.drawString("O",this.getX() + 5,this.getY() + 15);
        //g.fill
        //g.drawString("Observer", x, y);
    }
}
