public class SingletonHandler extends Handler {
    private Handler successor;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    @Override

    public void Handle(Component c) {
        if (c instanceof Decorator && c.getClass().equals(ConcreteSingletonDecorator.class)){
            //String extensionCode = "";
            String classCode = tabString + "private static Singleton instance;\n" +
                    "\n" +
                    tabString + "private Singleton() {}\n" +
                    "\n" +
                    tabString + "public static Singleton getInstance() {\n" +
                    tabString + tabString + "if (instance == null) {\n" +
                    tabString + tabString + tabString + "instance = new Singleton();\n" +
                    tabString + tabString + "}\n" +
                    tabString + "return instance;\n" +
                    tabString + "}\n\n";
            //Blackboard.getInstance().getClassCode().get(Decorator.getBaseNode(c).getLabel()).getExtensions().add(extensionCode);
            Blackboard.getInstance().getClassCode().get(Decorator.getBaseNode(c).getLabel()).getClassCode().add(classCode);
        } else {
            successor.Handle(c);
        }
    }
}
