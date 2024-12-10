import java.awt.*;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Blackboard holds the state of the entire application, including Nodes (which represent classes) and their decorations,
 * all connections, and other necessary information. It is observed by DrawArea.
 *
 * @author Josue Lopez
 * @author Brendan Holt
 * @version 1.0
 */
public class Blackboard extends PropertyChangeSupport {

    private static Blackboard instance;
    private final ArrayList<Component> nodes = new ArrayList<>();
    private final ArrayList<NodeLine> nodeLines = new ArrayList<>();
    private final ArrayList<ArrayList<Point>> decoratorLines = new ArrayList<>(); // First slot in list reserved for line that moves with user's mouse
    private String currentNodeConnectionType = "None";
    private final HashMap<String, ArrayList<String>> classCodeMap = new HashMap<>();

    protected Blackboard() {
        super(new Object());
    }

    public static Blackboard getInstance() {
        if (instance == null) {
            instance = new Blackboard();
        }
        return instance;
    }

    /**
     * Adds a Node to the Blackboard's list of Nodes.
     */

    public void add(Node node) {
        nodes.add(node);
    }

    /**
     *  Returns the Node at the given index in Blackboard's nodes list.
     */

    public Component get(int index) {
        return nodes.get(index);
    }


    /**
     * Returns the list of Decorator lines in Blackboard, initializing it if it doesn't exist yet by calling initializeDecoratorLines().
     */

    public ArrayList<ArrayList<Point>> getDecoratorLines() {
        if (decoratorLines.isEmpty()) {
            initializeDecoratorLines();
        }
        return decoratorLines;
    }

    /**
     * Returns the list of NodeLine objects in Blackboard, initializing it if it doesn't exist yet by calling initializeDecoratorLines().
     */

    public ArrayList<NodeLine> getNodeLines() {
        if (nodeLines.isEmpty()) {
            initializeNodeLines();
        }
        return nodeLines;
    }

    /**
     * Returns the size of the Node list in Blackboard.
     */

    public int size() {
        return nodes.size();
    }

    /**
     * Replaces a Node in the Node list in Blackboard with the given newNode.
     */

    public void updateNode(Component oldNode, Component newNode) {
        int index = nodes.indexOf(oldNode);
        nodes.set(index, newNode);
    }

    /**
     * Returns the list of Nodes in Blackboard.
     */

    public ArrayList<Component> getNodes() {
        return nodes;
    }

    public HashMap<String, ArrayList<String>> getClassCode() {
        return classCodeMap;
    }

    /**
     * Initializes the list of Decorator lines in Blackboard, in which the first index always contains a temporary line
     * that displays mid-drawing, following the mouse.
     */

    public void initializeDecoratorLines() { // Initializes the reserved dragging line
        ArrayList<Point> temp = new ArrayList<>();
        temp.add(new Point(0, 0));
        temp.add(new Point(0, 0));
        decoratorLines.add(temp);
    }

    /**
     * Replaces the first line's starting Point in Blackboard's list of Decorator lines with the given Point.
     * Used to draw a dynamic line that follows the user's mouse until they click on another Decorator.
     */

    public void addDecorationMovingLineStart(Point pt) {
        decoratorLines.getFirst().set(0, pt);
    }

    /**
     * Replaces the first line's ending Point in Blackboard's list of Decorator lines with the given Point.
     * Used to draw a dynamic line that follows the user's mouse until they click on another Decorator.
     */

    public void addDecorationMovingLineEnd(Point pt) {
        decoratorLines.getFirst().set(1, pt);
    }

    /**
     * Clears the first line in Blackboard's list of Decorator lines, which is specifically used for drawing a dynamic
     * line that follows the user's mouse until they click on another Decorator.
     */

    public void clearDecorationMovingLine() {
        decoratorLines.getFirst().set(0, new Point(0, 0));
        decoratorLines.getFirst().set(1, new Point(0, 0));
    }

    /**
     * Clears Blackboard's list of Decorator lines and re-initializes it by calling initializeDecoratorLines().
     */

    public void clearAllDecorationLines() {
        decoratorLines.clear();
        initializeDecoratorLines();
    }

    /**
     * Initializes the list of NodeLines in Blackboard, in which the first index always contains a temporary line
     * that displays mid-drawing, following the mouse.
     */

    public void initializeNodeLines() {
        NodeLine temp = new NodeLine(currentNodeConnectionType, new Point(0, 0), new Point(0, 0));
        nodeLines.add(temp);
    }

    /**
     * Replaces the first line's starting Point in Blackboard's list of NodeLines with the given Point.
     * Used to draw a dynamic line that follows the user's mouse until they click on another Node.
     */

    public void addNodeMovingLineStart(Point pt) {
        nodeLines.getFirst().setStart(pt);
    }

    /**
     * Replaces the first line's ending Point in Blackboard's list of NodeLines with the given Point.
     * Used to draw a dynamic line that follows the user's mouse until they click on another Node.
     */

    public void addNodeMovingLineEnd(Point pt) {
        nodeLines.getFirst().setEnd(pt);
    }

    /**
     * Updates the first line's connection type in Blackboard's list of NodeLines with the current connection type.
     * Used to draw a dynamic string above a line that follows the user's mouse until they click on another Node.
     */

    public void updateNodeMovingLineType() {
        nodeLines.getFirst().setConnectionType(currentNodeConnectionType);
    }

    /**
     * Clears the first line in Blackboard's list of NodeLines, which is specifically used for drawing a dynamic
     * line that follows the user's mouse until they click on another Node.
     */

    public void clearNodeMovingLine() {
        nodeLines.getFirst().setStart(new Point(0, 0));
        nodeLines.getFirst().setEnd(new Point(0, 0));
    }

    /**
     * Clears Blackboard's list of NodeLines and re-initializes it by calling initializeNodeLines().
     */

    public void clearAllNodeLines() {
        nodeLines.clear();
        initializeNodeLines();
    }

    /**
     * Returns the current, user-selected UML connection type as a String.
     */

    public String getCurrentNodeConnectionType() {
        return currentNodeConnectionType;
    }

    /**
     * Sets the current, user-selected UML connection type.
     */

    public void setCurrentNodeConnectionType(String currentNodeConnectionType) {
        this.currentNodeConnectionType = currentNodeConnectionType;
    }

    /**
     * Instructs DrawArea to repaint the canvas. Used when anything is changed.
     */

    public void repaint() {
        firePropertyChange("repaint", 0, 1);
    }
}