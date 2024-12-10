public interface Handler {

    //not true Handler, always goes through ENTIRE chain
    public void Handle(Component c);
}
