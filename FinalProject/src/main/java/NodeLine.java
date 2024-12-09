import java.awt.*;

public class NodeLine {
    private String connectionType;
    private Point start;
    private Point end;

    public NodeLine(String connectionType, Point start, Point end) {
        this.connectionType = connectionType;
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public void setEnd(Point end) {
        this.end = end;

    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }



}
