import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class DrawAreaListener implements MouseListener, MouseMotionListener {

    private int preX, preY;
    private Point startPoint = null;
    private Point endPoint = null;
    private int selected = -1;
    private Decorator startDecoration;
    boolean isDrawingDecorationLine = false;
    private Point currentMouseLocation = null;
    private Node startNode = null;


    private int nodeSelected(MouseEvent e) {
        int nodeSelected = -1;
        for (int i = 0; i < Blackboard.getInstance().size(); i++) {
            Component component = Blackboard.getInstance().get(i);
            Node node;

            if (component instanceof Decorator) {
                node = ((Decorator) component).getBaseNode();
            } else {
                node = (Node) component;
            }

            if (node.contains(e.getX(), e.getY())) {
                nodeSelected = i;
            }
        }

        return nodeSelected;
    }

    /*
    * Iterate through decorations in a NOde
    * Loop through Nodes,
    *   Check for instance since it can be either node or decoration
    *   if decoration, getBaseNode go back to step 1
    * Loop through Decorations,
    *   Determine if that decoration's coordinates were referenced
    * Else
    * return sentinel value
    * */

    private int[] decorationSelected(MouseEvent e) { // looks for decorations, if found returns [node index, decoration index]
        int[] decorationSelected = {-1, -1};

        for (int i = 0; i < Blackboard.getInstance().size(); i++) {
            Component component = Blackboard.getInstance().get(i);
            Node node;

            if (component instanceof Decorator) {
                node = ((Decorator) component).getBaseNode();
            } else {
                node = (Node) component;
            }


            for (int j = 0; j < node.getDecorators().size(); j++) {
                Decorator decorator = node.getDecorators().get(j);


                // Assuming all decorators have a width and height of 20x20
                int dx = decorator.getX();
                int dy = decorator.getY();
                int width = 25;
                int height = 25;


                // Check if the click is within the 20x20 bounds of the decorator
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

        if (SwingUtilities.isLeftMouseButton(e)) {
            if (selectedNodeNumber == -1) {
                if (!(decorationSelected[0] == -1 && decorationSelected[1] == -1)) {
                    int nodeIndex = decorationSelected[0];
                    int decorationIndex = decorationSelected[1];
                    Component component = Blackboard.getInstance().get(decorationSelected[0]);
                    Node n;

                    if (component instanceof Decorator) {
                        n = ((Decorator) component).getBaseNode();
                    } else {
                        n = (Node) component;
                    }

                    Decorator clickedDeco = n.getDecorators().get(decorationIndex);

                    if (!isDrawingDecorationLine) {
                        startDecoration = clickedDeco;
                        isDrawingDecorationLine = true;
                        currentMouseLocation = e.getPoint();
                        Blackboard.getInstance().addDecorationMovingLineStart(currentMouseLocation);
                        System.out.println("Started drawing decoration line");
                    } else {
                        Decorator endDecoration = clickedDeco;
                        if (endDecoration != null && startDecoration != endDecoration) {
                            ArrayList<Point> newLine = new ArrayList<>();
                            newLine.add(startDecoration.getCenterPoint());
                            newLine.add(endDecoration.getCenterPoint());
                            Blackboard.getInstance().getDecoratorLines().add(newLine);

                            isDrawingDecorationLine = false;
                            startDecoration = null;
                            currentMouseLocation = null;
                            System.out.println("finalizing drawing decoration line");
                            Blackboard.getInstance().clearDecorationMovingLine();
                            Blackboard.getInstance().repaint();
                        } else {
                            isDrawingDecorationLine = false;
                            startDecoration = null;
                            currentMouseLocation = null;
                            Blackboard.getInstance().clearDecorationMovingLine();
                            Blackboard.getInstance().repaint();
                            System.out.println("Clicked on the same decoration");
                        }
                    }
                } else {
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
            } else {
                Component component = Blackboard.getInstance().get(selectedNodeNumber);
                Node selectedNode;

                if (component instanceof Decorator) {
                    selectedNode = ((Decorator) component).getBaseNode();
                } else {
                    selectedNode = (Node) component;
                }

                if (Blackboard.getInstance().getCurrentNodeConnectionType().equals("None")) { // Node clicked but connector not selected; not drawing line
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
                } else {
                    if (startNode == null) { // First click of process; Create line that moves with mouse
                        Blackboard.getInstance().initializeNodeLines();
                        startNode = selectedNode;
                        currentMouseLocation = e.getPoint();
                        Blackboard.getInstance().addNodeMovingLineStart(currentMouseLocation);
                        System.out.println("Started drawing node line");
                    } else { // Second click of process; Finalize line
                        if (selectedNode != null && selectedNode != startNode) {
                            NodeLine temp = new NodeLine(Blackboard.getInstance().getCurrentNodeConnectionType(), startNode.center(), selectedNode.center());
                            Blackboard.getInstance().getNodeLines().add(temp);
                            Blackboard.getInstance().clearNodeMovingLine();
                            Blackboard.getInstance().repaint();
                            Blackboard.getInstance().setCurrentNodeConnectionType("None");
                            startNode = null;
                            currentMouseLocation = null;
                            System.out.println("Finalized drawing node line");
                        } else {
                            startNode = null;
                            currentMouseLocation = null;
                            Blackboard.getInstance().setCurrentNodeConnectionType("None");
                            Blackboard.getInstance().clearNodeMovingLine();
                            Blackboard.getInstance().repaint();
                            System.out.println("Clicked on the same Node");
                        }
                    }


                }
            }
        } else if (SwingUtilities.isRightMouseButton(e)) {
            if (selectedNodeNumber != -1) {
                Component component = Blackboard.getInstance().get(selectedNodeNumber);
                NodeDecoratorPanel.showContextMenu(e, component);
            }
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
        selected = nodeSelected(e);

        int[] decorationSelected = decorationSelected(e);

        if (!(decorationSelected[0] == -1 && decorationSelected[1] == -1)) {

            Component component = Blackboard.getInstance().get(decorationSelected[0]);
            Node n;

            if (component instanceof Decorator) {
                n = ((Decorator) component).getBaseNode();
            } else {
                n = (Node) component;
            }

            startPoint = e.getPoint();

        }else {

            if(selected == -1){return;}

            Component component = Blackboard.getInstance().get(selected);
            Node node;

            if (component instanceof Decorator) {
                node = ((Decorator) component).getBaseNode();
            } else {
                node = (Node) component;
            }

            preX = node.getX() - e.getX();
            preY = node.getY() - e.getY();
            node.move(preX + e.getX(), preY + e.getY());
            //Blackboard.getInstance().repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if (selected == -1) return;

        Component component = Blackboard.getInstance().get(selected);
        Node node;

        if (component instanceof Decorator) {
            node = ((Decorator) component).getBaseNode();
        } else {
            node = (Node) component;
        }


        node.move(preX + e.getX(), preY + e.getY());
        endPoint = e.getPoint();

        if (!(Blackboard.getInstance().getNodeLines().size() == 1)) {
            Blackboard.getInstance().clearAllNodeLines();
        }

        if (!(Blackboard.getInstance().getDecoratorLines().size() == 1)) {
            Blackboard.getInstance().clearAllDecorationLines();
        }

        Blackboard.getInstance().repaint();

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (selected == -1) return;

        Component component = Blackboard.getInstance().get(selected);
        Node node;

        if (component instanceof Decorator) {
            node = ((Decorator) component).getBaseNode();
        } else {
            node = (Node) component;
        }

        node.move(preX + e.getX(), preY + e.getY());
        //Blackboard.getInstance().repaint();
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
        if (!(startNode == null)) {
            currentMouseLocation = e.getPoint();
            Blackboard.getInstance().addNodeMovingLineEnd(currentMouseLocation);
            Blackboard.getInstance().repaint();
        }

        if (isDrawingDecorationLine) {
            currentMouseLocation = e.getPoint();
            Blackboard.getInstance().addDecorationMovingLineEnd(currentMouseLocation);
            Blackboard.getInstance().repaint();
        }
    }
}
