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
            if (Blackboard.getInstance().get(i).contains(e.getX(), e.getY())) {
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
                Node selectedNode = Blackboard.getInstance().get(selectedNodeNumber);
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
                Node selectedNode = Blackboard.getInstance().get(selectedNodeNumber);
                NodeDecoratorPanel.showContextMenu(e, selectedNode);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        selected = nodeSelected(e);
        if (selected == -1) return;
        Node node = Blackboard.getInstance().get(selected);
        preX = node.getX() - e.getX();
        preY = node.getY() - e.getY();
        node.move(preX + e.getX(), preY + e.getY());
        Blackboard.getInstance().repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (selected == -1) return;
        Node node = Blackboard.getInstance().get(selected);
        node.move(preX + e.getX(), preY + e.getY());
        Blackboard.getInstance().repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (selected == -1) return;
        Node node = Blackboard.getInstance().get(selected);
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
