
/**
 * Concrete Handler for the Product Decorator. Creates Boilerplate code accordingly.
 *
 * @author Josue Lopez
 * @author Brendan Holt
 * @version 1.0
 */

public class ProductHandler extends Handler {

    /**
     * Creates the boilerplate code associated with the Decorator.
     */

    @Override
    public void Handle(Component c) {

        if (c instanceof Decorator && c.getClass().equals(ConcreteProductDecorator.class)){
            String extensionCode = "implements Product\n";
            String classCode = tabString + "public " + Decorator.getBaseNode(c).getLabel() + "() {\n" +
                    tabString + "}\n\n";
            Blackboard.getInstance().getClassCode().get(Decorator.getBaseNode(c).getLabel()).getExtensions().add(extensionCode);
            Blackboard.getInstance().getClassCode().get(Decorator.getBaseNode(c).getLabel()).getClassCode().add(classCode);
        } // End of line; Final handler!
    }
}
