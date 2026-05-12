package domain.memento;

import java.io.Serializable;

/** Snapshot of a special element's runtime state. */
public class SpecialElementMemento implements Serializable {
    private static final long serialVersionUID = 1L;

    private String elementType;
    private String elementId;
    private boolean active;
    private double x;
    private double y;

    public SpecialElementMemento(String elementType, String elementId, boolean active, double x, double y) {
        this.elementType = elementType;
        this.elementId = elementId;
        this.active = active;
        this.x = x;
        this.y = y;
    }

    public String getElementType() { return this.elementType; }
    public String getElementId() { return this.elementId; }
    public boolean isActive() { return this.active; }
    public double getX() { return this.x; }
    public double getY() { return this.y; }
}

