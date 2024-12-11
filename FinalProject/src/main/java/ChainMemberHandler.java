
/**
 * Concrete Handler for the Chain Member Decorator. Generates boilerplate code appropriately.
 *
 * @author Josue Lopez
 * @author Brendan Holt
 * @version 1.0
 */

public class ChainMemberHandler extends Handler {
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

        if (c instanceof Decorator && c.getClass().equals(ConcreteChainMemberDecorator.class)){
            String extensionCode = "extends Handler\n";
            String classCode = "";
            Blackboard.getInstance().getClassCode().get(Decorator.getBaseNode(c).getLabel()).getExtensions().add(extensionCode);
            Blackboard.getInstance().getClassCode().get(Decorator.getBaseNode(c).getLabel()).getClassCode().add(classCode);
        } else {
            successor.Handle(c);
        }
    }
}
