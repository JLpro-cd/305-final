import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class NodeDecoratorPanel {

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
        Component decoratedNode = null;
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

        if(!(currentNode instanceof Node)){
            n = ((Decorator)currentNode).getBaseNode();
            if (!(n.checkIfExists((Decorator) decoratedNode))) {
                n.getDecorators().add((Decorator)decoratedNode);
            }
            //System.out.println("printing bullshi1:");
            //System.out.println(currentNode);
        } else if (currentNode instanceof Node) {
            n = ((Node)currentNode);
            if (!(n.checkIfExists((Decorator) decoratedNode))) {
                n.getDecorators().add((Decorator)decoratedNode);
            }
            //System.out.println("printing bullshi2:");
            //System.out.println(decoratedNode);
        }

        Blackboard.getInstance().updateNode(currentNode, decoratedNode);
        Blackboard.getInstance().repaint();
    }

}
