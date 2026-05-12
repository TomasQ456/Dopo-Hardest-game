package domain.memento;

import java.io.Serializable;

/** Snapshot of a checkpoint's runtime state. */
public class CheckpointMementoData implements Serializable {
    private static final long serialVersionUID = 1L;

    private String checkpointId;
    private boolean active;
    private double x;
    private double y;

    public CheckpointMementoData(String checkpointId, boolean active, double x, double y) {
        this.checkpointId = checkpointId;
        this.active = active;
        this.x = x;
        this.y = y;
    }

    public String getCheckpointId() { return this.checkpointId; }
    public boolean isActive() { return this.active; }
    public double getX() { return this.x; }
    public double getY() { return this.y; }
}

