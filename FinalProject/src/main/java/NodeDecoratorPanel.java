import javax.swing.*;
import java.awt.event.MouseEvent;

public class NodeDecoratorPanel {

    public static void showContextMenu(MouseEvent e, Node rightClickedNode) {
        JPopupMenu popupMenu = new JPopupMenu("Design Patterns");
        JMenuItem observerItem = new JMenuItem("Observer");
        JMenuItem observableItem = new JMenuItem("Observable");
        JMenuItem singletonItem = new JMenuItem("Singleton");
        JMenuItem decorationItem = new JMenuItem("Decoration");
        JMenuItem decoratableItem = new JMenuItem("Decoratable");
        JMenuItem chainMember = new JMenuItem("Chain Member");
        JMenuItem strategyItem = new JMenuItem("Strategy");
        JMenuItem factoryItem = new JMenuItem("Factory");
        JMenuItem productItem = new JMenuItem("Product");

        popupMenu.add(observerItem);
        popupMenu.add(observableItem);
        popupMenu.add(singletonItem);
        popupMenu.add(decorationItem);
        popupMenu.add(decoratableItem);
        popupMenu.add(chainMember);
        popupMenu.add(strategyItem);
        popupMenu.add(factoryItem);
        popupMenu.add(productItem);

        popupMenu.show(e.getComponent(), e.getX(), e.getY());


    }

}
