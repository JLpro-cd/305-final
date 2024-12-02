import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DrawAreaListener implements MouseListener, MouseMotionListener {

    private int preX, preY;
    private int selected = -1;

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

    @Override
    public void mouseClicked(MouseEvent e) {
        int selectedNodeNumber = nodeSelected(e);

        if (SwingUtilities.isLeftMouseButton(e)) {
            if (selectedNodeNumber == -1) {
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
            } else {
                Component component = Blackboard.getInstance().get(selectedNodeNumber);
                Node selectedNode;

                if (component instanceof Decorator) {
                    selectedNode = ((Decorator) component).getBaseNode();
                } else {
                    selectedNode = (Node) component;
                }

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
        if (selected == -1) return;

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
        Blackboard.getInstance().repaint();
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
        Blackboard.getInstance().repaint();
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
    }
}
