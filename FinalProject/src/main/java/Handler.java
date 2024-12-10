
/**
 * Abstract class from which concrete Handlers inherit from.
 *
 * @author Josue Lopez
 * @author Brendan Holt
 * @version 1.0
 */

public abstract class Handler {
    protected String tabString = "    ";

    public abstract void Handle(Component c);
}
