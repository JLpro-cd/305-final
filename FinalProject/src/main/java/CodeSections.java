import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a class' extension (extends ___, implements ___) and class code (variables, constructors, methods)
 * boilerplate code.
 *
 * @author Josue Lopez
 * @author Brendan Holt
 * @version 1.0
 */

public class CodeSections {
    private ArrayList<String> extensions = new ArrayList<>();
    private ArrayList<String> classCode = new ArrayList<>();

    /**
     * Returns the class' extension (extends ___, implements ___) boilerplate code.
     */

    public List<String> getExtensions() {
        return extensions;
    }

    /**
     * Returns the class' boilerplate code (variables, constructors, methods).
     */

    public List<String> getClassCode() {
        return classCode;
    }


}
