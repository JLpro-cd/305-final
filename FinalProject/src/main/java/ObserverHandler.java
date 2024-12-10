
/**
 * Concrete Handler for the Observer Decorator. Creates Boilerplate code accordingly.
 *
 * @author Josue Lopez
 * @author Brendan Holt
 * @version 1.0
 */

public class ObserverHandler extends Handler {

    protected Handler successor;

    /**
     * Sets the Handler's successor.
     */

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    /**
     * Creates the boilerplate code associated with the Decorator.
     */

    @Override
    public void Handle(Component c) {

        if (c instanceof Decorator && c.getClass().equals(ConcreteObserverDecorator.class)){
            String extensionCode = "implements PropertyChangeListener\n";
            String classCode = tabString + "@Override\n" +
                    tabString + "public void propertyChange(PropertyChangeEvent e) {\n" +
                    tabString + "}\n\n";
            Blackboard.getInstance().getClassCode().get(Decorator.getBaseNode(c).getLabel()).getExtensions().add(extensionCode);
            Blackboard.getInstance().getClassCode().get(Decorator.getBaseNode(c).getLabel()).getClassCode().add(classCode);
        } else {
            successor.Handle(c);
        }


    }
}
