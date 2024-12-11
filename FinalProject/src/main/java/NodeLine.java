import java.awt.*;
import java.io.Serializable;

/**
 * Represents a UML line connecting two classes (Nodes).
 *
 * @author Josue Lopez
 * @author Brendan Holt
 * @version 1.0
 */

public class NodeLine implements Serializable {
    private String connectionType;
    private Point start;
    private Point end;

    public NodeLine(String connectionType, Point start, Point end) {
        this.connectionType = connectionType;
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the NodeLine's start Point.
     */

    public Point getStart() {
        return start;
    }

    /**
     * Returns the NodeLine's end Point.
     */

    public Point getEnd() {
        return end;
    }

    /**
     * Returns the type of connection the NodeLine represents.
     */

    public String getConnectionType() {
        return connectionType;
    }

    /**
     * Sets the NodeLine's start Point.
     */

    public void setStart(Point start) {
        this.start = start;
    }

    /**
     * Sets the NodeLine's end Point.
     */

    public void setEnd(Point end) {
        this.end = end;

    }

    /**
     * Sets the NodeLine's connection type.
     */

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }



}
