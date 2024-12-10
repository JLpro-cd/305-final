
/**
 * Concrete Handler for the Decoration Decorator. Creates Boilerplate code accordingly.
 *
 * @author Josue Lopez
 * @author Brendan Holt
 * @version 1.0
 */

public class DecorationHandler extends Handler {
    private Handler successor;

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

        if (c instanceof Decorator && c.getClass().equals(ConcreteDecorationDecorator.class)){
            String extensionCode = "extends Decorator\n";
            String classCode = tabString + "@Override\n" +
                    tabString + "public void componentMethod() {\n" +
                    tabString + "}\n\n";
            Blackboard.getInstance().getClassCode().get(Decorator.getBaseNode(c).getLabel()).getExtensions().add(extensionCode);
            Blackboard.getInstance().getClassCode().get(Decorator.getBaseNode(c).getLabel()).getClassCode().add(classCode);
        } else {
            successor.Handle(c);
        }
    }
}
