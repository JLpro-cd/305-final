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
    private final ArrayList<NodeLine> nodeLines = new ArrayList<>();
    private final ArrayList<ArrayList<Point>> decoratorLines = new ArrayList<>(); // First slot in list reserved for line that moves with user's mouse
    private String currentNodeConnectionType = "None";

    protected Blackboard() {
        super(new Object());
    }

    public static Blackboard getInstance() {
        if (instance == null) {
            instance = new Blackboard();
        }
        return instance;
    }

    public void add(Node node) {
        nodes.add(node);
    }

    public Component get(int index) {
        return nodes.get(index);
    }

    public ArrayList<ArrayList<Point>> getDecoratorLines() {
        if (decoratorLines.isEmpty()) {
            initializeDecoratorLines();
        }
        return decoratorLines;
    }

    public ArrayList<NodeLine> getNodeLines() {
        return nodeLines;
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

    public void initializeDecoratorLines() { // Initializes the reserved dragging line
        ArrayList<Point> temp = new ArrayList<>();
        temp.add(new Point(0, 0));
        temp.add(new Point(0, 0));
        decoratorLines.add(temp);
    }

    public void addDecorationMovingLineStart(Point pt) {
        decoratorLines.getFirst().set(0, pt);
    }

    public void addDecorationMovingLineEnd(Point pt) {
        decoratorLines.getFirst().set(1, pt);
    }

    public void clearDecorationMovingLine() {
        decoratorLines.getFirst().set(0, new Point(0, 0));
        decoratorLines.getFirst().set(1, new Point(0, 0));
    }

    public void clearAllDecorationLines() {
        decoratorLines.clear();
        initializeDecoratorLines();
    }

    public void initializeNodeLines() {
        NodeLine temp = new NodeLine(currentNodeConnectionType, new Point(0, 0), new Point(0, 0));
        nodeLines.add(temp);
    }

    public void addNodeMovingLineStart(Point pt) {
        nodeLines.getFirst().setStart(pt);
    }

    public void addNodeMovingLineEnd(Point pt) {
        nodeLines.getFirst().setEnd(pt);
    }

    public void clearNodeMovingLine() {
        nodeLines.getFirst().setStart(new Point(0, 0));
        nodeLines.getFirst().setEnd(new Point(0, 0));
    }

    public void clearAllNodeLines() {
        nodeLines.clear();
        initializeNodeLines();
    }

    public String getCurrentNodeConnectionType() {
        return currentNodeConnectionType;
    }

    public void setCurrentNodeConnectionType(String currentNodeConnectionType) {
        this.currentNodeConnectionType = currentNodeConnectionType;
    }

    public void repaint() {
        firePropertyChange("repaint", 0, 1);
    }
}