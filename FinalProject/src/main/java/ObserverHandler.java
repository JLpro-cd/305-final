public class ObserverHandler implements Handler {

    private String boilerPlate = "";
    protected Handler successor;

    public void setSuccessor(Handler handler){
        this.successor = handler;
    }

    //Blackboard.getInstance().getClassCode().get(Decorator.getBaseNode(Blackboard.getInstance().getNodes().get(i)).getLabel()).add("TEST" + i);
    @Override
    public void Handle(Component c) {

        if(c instanceof Decorator && c.getClass().equals(ConcreteObserverDecorator.class)){
            boilerPlate = "implements PropertyChangeListener\n";
            Blackboard.getInstance().getClassCode().get(Decorator.getBaseNode(c).getLabel()).add(boilerPlate);

        }


    }
}
