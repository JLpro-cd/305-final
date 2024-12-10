public class ObserverHandler extends Handler {

    protected Handler successor;

    public void setSuccessor(Handler handler){
        this.successor = handler;
    }

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
