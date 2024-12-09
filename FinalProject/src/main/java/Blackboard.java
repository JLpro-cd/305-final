import java.awt.*;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * Blackboard class is a singleton that stores the nodes of the graph.
 * It also provides a method to save the data to a file and to read the data from a file.
 *
 * @author javiergs
 * @version 1.0
 */
public class Blackboard extends PropertyChangeSupport {

    private static Blackboard instance;
    private final ArrayList<Component> nodes = new ArrayList<>();
    private final ArrayList<ArrayList<Point>> decoratorLines = new ArrayList<>();

    private Strategy strategy = new StrategyAsQueue();

    protected Blackboard() {
        super(new Object());
    }

    public static Blackboard getInstance() {
        if (instance == null)
            instance = new Blackboard();
        return instance;
    }

    public void add(Node node) {
        nodes.add(node);
    }

    public Component get(int index) {
        return nodes.get(index);
    }

    public ArrayList<ArrayList<Point>> getDecoratorLines() {
        return decoratorLines;
    }

    public int size() {
        return nodes.size();
    }

    public void updateNode(Component oldNode, Component newNode) {
        int index = nodes.indexOf(oldNode);
        nodes.set(index, newNode);
    }

    public ArrayList<Component> getNodes() {
        return nodes;
    }

    //public int[] travelingOrder() {return strategy.algorithm(nodes);}

    public void repaint() {
        firePropertyChange("repaint", 0, 1);
    }
}