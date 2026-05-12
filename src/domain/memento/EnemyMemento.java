package domain.memento;

import domain.math.Vector2;

import java.io.Serializable;
import java.util.List;

/** Snapshot of an enemy's runtime state. */
public class EnemyMemento implements Serializable {
    private static final long serialVersionUID = 1L;

    private String enemyType;
    private String enemyId;
    private double x;
    private double y;
    private double speed;
    private double directionX;
    private double directionY;
    private List<Vector2> waypoints;
    private int currentWaypointIndex;

    public EnemyMemento(String enemyType, String enemyId, double x, double y, double speed,
                        double directionX, double directionY, List<Vector2> waypoints, int currentWaypointIndex) {
        this.enemyType = enemyType;
        this.enemyId = enemyId;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.directionX = directionX;
        this.directionY = directionY;
        this.waypoints = waypoints;
        this.currentWaypointIndex = currentWaypointIndex;
    }

    public String getEnemyType() { return this.enemyType; }
    public String getEnemyId() { return this.enemyId; }
    public double getX() { return this.x; }
    public double getY() { return this.y; }
    public double getSpeed() { return this.speed; }
    public double getDirectionX() { return this.directionX; }
    public double getDirectionY() { return this.directionY; }
    public List<Vector2> getWaypoints() { return this.waypoints; }
    public int getCurrentWaypointIndex() { return this.currentWaypointIndex; }
}

