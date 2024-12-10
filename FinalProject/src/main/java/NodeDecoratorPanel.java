import javax.swing.*;
import java.awt.event.MouseEvent;

/**
 * Context menu which appears upon right-clicking a Node.
 *
 * @author Josue Lopez
 * @author Brendan Holt
 * @version 1.0
 */

public class NodeDecoratorPanel {

    /**
     * Creates and shows the context menu that appears upon right-clicking a Node. Listens for clicks and wraps the
     * Node object accordingly.
     */

    public static void showContextMenu(MouseEvent e, Component rightClickedNode) {
        JPopupMenu popupMenu = new JPopupMenu("Design Patterns");

        JMenuItem observerItem = new JMenuItem("Observer");
        JMenuItem observableItem = new JMenuItem("Observable");
        JMenuItem singletonItem = new JMenuItem("Singleton");
        JMenuItem decorationItem = new JMenuItem("Decoration");
        JMenuItem decoratableItem = new JMenuItem("Decoratable");
        JMenuItem chainMemberItem = new JMenuItem("Chain Member");
        JMenuItem strategyItem = new JMenuItem("Strategy");
        JMenuItem factoryItem = new JMenuItem("Factory");
        JMenuItem productItem = new JMenuItem("Product");

        observerItem.addActionListener(e1 -> applyDecoration(rightClickedNode, "Observer"));
        observableItem.addActionListener(e1 -> applyDecoration(rightClickedNode, "Observable"));
        singletonItem.addActionListener(e1 -> applyDecoration(rightClickedNode, "Singleton"));
        decorationItem.addActionListener(e1 -> applyDecoration(rightClickedNode, "Decoration"));
        decoratableItem.addActionListener(e1 -> applyDecoration(rightClickedNode, "Decoratable"));
        chainMemberItem.addActionListener(e1 -> applyDecoration(rightClickedNode, "Chain Member"));
        strategyItem.addActionListener(e1 -> applyDecoration(rightClickedNode, "Strategy"));
        factoryItem.addActionListener(e1 -> applyDecoration(rightClickedNode, "Factory"));
        productItem.addActionListener(e1 -> applyDecoration(rightClickedNode, "Product"));

        popupMenu.add(observerItem);
        popupMenu.add(observableItem);
        popupMenu.add(singletonItem);
        popupMenu.add(decorationItem);
        popupMenu.add(decoratableItem);
        popupMenu.add(chainMemberItem);
        popupMenu.add(strategyItem);
        popupMenu.add(factoryItem);
        popupMenu.add(productItem);

        popupMenu.show(e.getComponent(), e.getX(), e.getY());
    }


    private static void applyDecoration(Component rightClickedNode, String decoration) {
        Decorator decoratedNode = null;
        Node n;

        int index = Blackboard.getInstance().getNodes().indexOf(rightClickedNode);

        if (index == -1) {
            System.out.println("failed to find node in list");
            return;
        }

        Component currentNode = Blackboard.getInstance().getNodes().get(index);

        if (decoration.equals("Observer")) {
            decoratedNode = new ConcreteObserverDecorator(currentNode);
        } else if (decoration.equals("Observable")) {
            decoratedNode = new ConcreteObservableDecorator(currentNode);
        } else if (decoration.equals("Singleton")) {
            decoratedNode = new ConcreteSingletonDecorator(currentNode);
        } else if (decoration.equals("Decoration")) {
            decoratedNode = new ConcreteDecorationDecorator(currentNode);
        } else if (decoration.equals("Decoratable")) {
            decoratedNode = new ConcreteDecoratableDecorator(currentNode);
        } else if (decoration.equals("Chain Member")) {
            decoratedNode = new ConcreteChainMemberDecorator(currentNode);
        } else if (decoration.equals("Strategy")) {
            decoratedNode = new ConcreteStrategyDecorator(currentNode);
        } else if (decoration.equals("Factory")) {
            decoratedNode = new ConcreteFactoryDecorator(currentNode);
        } else if (decoration.equals("Product")) {
            decoratedNode = new ConcreteProductDecorator(currentNode);
        }

        n = Decorator.getBaseNode(currentNode);

        if (!(n.checkIfExists(decoratedNode))) {
            n.getDecoratorHolder().addDecorator(decoratedNode);
        }

        Blackboard.getInstance().updateNode(currentNode, decoratedNode);
        Blackboard.getInstance().repaint();
    }

}
