public class ObservableHandler extends Handler {

    private Handler successor;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    @Override
    public void Handle(Component c) {

        if (c instanceof Decorator && c.getClass().equals(ConcreteObservableDecorator.class)){
            String extensionCode = "extends PropertyChangeSupport\n";
            String classCode = tabString + "public " + Decorator.getBaseNode(c).getLabel() + "() {\n" +
                    tabString + tabString + "super(new Object());\n" +
                    tabString + "}\n\n";
            Blackboard.getInstance().getClassCode().get(Decorator.getBaseNode(c).getLabel()).getExtensions().add(extensionCode);
            Blackboard.getInstance().getClassCode().get(Decorator.getBaseNode(c).getLabel()).getClassCode().add(classCode);
        } else {
            successor.Handle(c);
        }
    }
}
