import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * Central source of logic for user interaction through clicking on the GUI. Modifies Blackboard according to user
 * activity and calls repaint() to refresh the DrawArea.
 *
 * @author Josue Lopez
 * @author Brendan Holt
 * @version 1.0
 */

public class DrawAreaListener implements MouseListener, MouseMotionListener {

    private int preX, preY;
    private int selected = -1;
    private Decorator startDecoration;
    boolean isDrawingDecorationLine = false;
    private Point currentMouseLocation = null;
    private Node startNode = null;

    /**
     * Returns the index of the selected Node within Blackboard's Node list if the user's click was on it, or -1 if no
     * Node was clicked on.
     */

    private int nodeSelected(MouseEvent e) {
        int nodeSelected = -1;
        for (int i = 0; i < Blackboard.getInstance().size(); i++) {
            Component component = Blackboard.getInstance().get(i);
            Node node = Decorator.getBaseNode(component);

            if (node.contains(e.getX(), e.getY())) {
                nodeSelected = i;
            }
        }
        return nodeSelected;
    }

    /**
     * Returns an array with the format [int node index, int decorator index] if the user clicked on a Decorator, or
     * [-1, -1] if no Decorator was clicked on.
     */

    private int[] decorationSelected(MouseEvent e) {
        int[] decorationSelected = {-1, -1};

        for (int i = 0; i < Blackboard.getInstance().size(); i++) {
            Component component = Blackboard.getInstance().get(i);
            Node node = Decorator.getBaseNode(component);

            for (int j = 0; j < node.getDecoratorHolder().getDecorators().length; j++) {
                Decorator decorator = node.getDecoratorHolder().getDecorators()[j];

                if (decorator == null) { continue; }

                int dx = decorator.getX();
                int dy = decorator.getY();
                int width = 25;
                int height = 25;

                if (e.getX() >= dx && e.getX() <= dx + width &&
                        e.getY() >= dy && e.getY() <= dy + height) {
                    decorationSelected[0] = i;
                    decorationSelected[1] = j;
                    return decorationSelected;
                }
            }
        }
        return decorationSelected;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int selectedNodeNumber = nodeSelected(e);
        int[] decorationSelected = decorationSelected(e);

        if (SwingUtilities.isLeftMouseButton(e)) { // Left mouse clicked.
            if (selectedNodeNumber == -1) { // Click was not on a node.
                if (!(decorationSelected[0] == -1 && decorationSelected[1] == -1)) { // Click was on a decoration.
                    int nodeIndex = decorationSelected[0];
                    int decorationIndex = decorationSelected[1];
                    Component component = Blackboard.getInstance().get(nodeIndex);
                    Node n = Decorator.getBaseNode(component);
                    Decorator clickedDeco = n.getDecoratorHolder().getDecorators()[decorationIndex];

                    if (!isDrawingDecorationLine) { // First click on a decoration; Start drawing the line.
                        startDecoration = clickedDeco;
                        isDrawingDecorationLine = true;
                        currentMouseLocation = e.getPoint();
                        Blackboard.getInstance().addDecorationMovingLineStart(currentMouseLocation);
                    } else { // Second click on a decoration; Finish drawing line.
                        if (clickedDeco != null && startDecoration != clickedDeco) {
                            ArrayList<Point> newLine = new ArrayList<>();
                            newLine.add(startDecoration.getCenterPoint());
                            newLine.add(clickedDeco.getCenterPoint());
                            Blackboard.getInstance().getDecoratorLines().add(newLine);
                            isDrawingDecorationLine = false;
                            startDecoration = null;
                            currentMouseLocation = null;
                            Blackboard.getInstance().clearDecorationMovingLine();
                            Blackboard.getInstance().repaint();
                        } else { // Error; User clicked same decoration twice.
                            isDrawingDecorationLine = false;
                            startDecoration = null;
                            currentMouseLocation = null;
                            Blackboard.getInstance().clearDecorationMovingLine();
                            Blackboard.getInstance().repaint();
                        }
                    }
                } else { // Click was not on a decoration OR node; Create new node.
                    String name = "unnamed" + Blackboard.getInstance().size();
                    Node newNode = new Node(name, e.getX(), e.getY(), 100, 100);

                    String result = (String) JOptionPane.showInputDialog(
                            e.getComponent(),
                            "Type the name of the new class",
                            "Class Name",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            name
                    );
                    if (result != null && !result.isEmpty()) {
                        Blackboard.getInstance().add(newNode);
                        newNode.setLabel(result);
                    }

                    Blackboard.getInstance().repaint();
                }
            } else { // Click was on a node.
                Component component = Blackboard.getInstance().get(selectedNodeNumber);
                Node selectedNode = Decorator.getBaseNode(component);

                if (Blackboard.getInstance().getCurrentNodeConnectionType().equals("None")) { // Node clicked but connector not selected; not drawing line, instead prompt to change node (class) name.
                    String result = (String) JOptionPane.showInputDialog(
                            e.getComponent(),
                            "What would you like to change " + selectedNode.getLabel() + "'s name to?",
                            "Class Name Change",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            selectedNode.getLabel()
                    );
                    if (result != null && !result.isEmpty()) {
                        selectedNode.setLabel(result);
                    }
                    Blackboard.getInstance().repaint();
                } else { // Node clicked and connector selected; Drawing line.
                    if (startNode == null) { // First click on a node; Create line that moves with mouse.
                        Blackboard.getInstance().initializeNodeLines();
                        startNode = selectedNode;
                        currentMouseLocation = e.getPoint();
                        Blackboard.getInstance().addNodeMovingLineStart(currentMouseLocation);
                        Blackboard.getInstance().updateNodeMovingLineType();
                    } else { // Second click on a node; Finalize line.
                        if (selectedNode != startNode) {
                            NodeLine temp = new NodeLine(Blackboard.getInstance().getCurrentNodeConnectionType(), startNode.center(), selectedNode.center());
                            Blackboard.getInstance().getNodeLines().add(temp);
                            Blackboard.getInstance().clearNodeMovingLine();
                            Blackboard.getInstance().repaint();
                            Blackboard.getInstance().setCurrentNodeConnectionType("None");
                            startNode = null;
                            currentMouseLocation = null;
                        } else { // Error; User clicked on the same node twice.
                            startNode = null;
                            currentMouseLocation = null;
                            Blackboard.getInstance().setCurrentNodeConnectionType("None");
                            Blackboard.getInstance().clearNodeMovingLine();
                            Blackboard.getInstance().repaint();
                        }
                    }


                }
            }
        } else if (SwingUtilities.isRightMouseButton(e)) { // Right mouse click.
            if (selectedNodeNumber != -1) { // Right-clicked on a node; Open context menu showing decoration options.
                Component component = Blackboard.getInstance().get(selectedNodeNumber);
                NodeDecoratorPanel.showContextMenu(e, component);
            }
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
        selected = nodeSelected(e);
        int[] decorationSelected = decorationSelected(e);

        if (decorationSelected[0] == -1 && decorationSelected[1] == -1) { // Mouse press was not on a decorator;
            if (selected == -1) return; // Mouse press was on a node; Continue.
            Component component = Blackboard.getInstance().get(selected);
            Node node = Decorator.getBaseNode(component);

            preX = node.getX() - e.getX();
            preY = node.getY() - e.getY();
            node.move(preX + e.getX(), preY + e.getY());
        }
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        if (selected == -1) return; // Mouse press was on a node; Continue.

        if (startNode == null) { // Not in the middle of creating a line for nodes; Continue.

            Component component = Blackboard.getInstance().get(selected);
            Node node = Decorator.getBaseNode(component);

            node.move(preX + e.getX(), preY + e.getY());

            if (!(Blackboard.getInstance().getNodeLines().size() == 1)) {
                Blackboard.getInstance().clearAllNodeLines();
            }

            if (!(Blackboard.getInstance().getDecoratorLines().size() == 1)) {
                Blackboard.getInstance().clearAllDecorationLines();
            }

            Blackboard.getInstance().repaint();
        }

    }


    @Override
    public void mouseReleased(MouseEvent e) {
        if (selected == -1) return; // Mouse press was on a node; Continue.

        Component component = Blackboard.getInstance().get(selected);
        Node node = Decorator.getBaseNode(component);

        node.move(preX + e.getX(), preY + e.getY());
        selected = nodeSelected(e);
    }


    @Override
    public void mouseEntered(MouseEvent e) {
    }


    @Override
    public void mouseExited(MouseEvent e) {
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        if (!(startNode == null)) { // Mouse was moved while in the process of creating a line for nodes; Update dynamically.
            currentMouseLocation = e.getPoint();
            Blackboard.getInstance().addNodeMovingLineEnd(currentMouseLocation);
            Blackboard.getInstance().repaint();
        }

        if (isDrawingDecorationLine) { // Mouse was moved while in the process of creating a line for decorations; Update dynamically.
            currentMouseLocation = e.getPoint();
            Blackboard.getInstance().addDecorationMovingLineEnd(currentMouseLocation);
            Blackboard.getInstance().repaint();
        }
    }
}
