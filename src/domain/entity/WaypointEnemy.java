package domain.entity;

import domain.math.Vector2;
import domain.level.TileMap;
import java.util.List;
import domain.exception.DhgDomainException;

/**
 * An enemy that patrols along a predefined list of waypoint coordinates.
 */
public class WaypointEnemy extends Enemy {

    private List<Vector2> waypoints;
    private int currentWaypointIndex = 0;

    /**
     * Constructs a WaypointEnemy with specified speed and waypoints.
     * @param speed The movement speed.
     * @param waypoints The list of waypoints to patrol.
     */
    public WaypointEnemy(double speed, List<Vector2> waypoints) {
        super(speed);
        this.waypoints = waypoints;
    }

    @Override
    public void update(double deltaSeconds) throws DhgDomainException {
        if (this.position == null || this.waypoints == null || this.waypoints.isEmpty()) {
            return;
        }

        Vector2 target = this.waypoints.get(this.currentWaypointIndex);
        double distanceX = target.x - this.position.x;
        double distanceY = target.y - this.position.y;
        double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        if (distance < this.speed * deltaSeconds) {
            // Reached waypoint, advance to next
            this.position = target;
            this.currentWaypointIndex = (this.currentWaypointIndex + 1) % this.waypoints.size();
        } else {
            // Move toward target
            double ratio = (this.speed * deltaSeconds) / distance;
            Vector2 newPosition = new Vector2(
                this.position.x + distanceX * ratio,
                this.position.y + distanceY * ratio
            );
            this.position = newPosition;
        }
    }

    @Override
    protected void applyBounds(TileMap tileMap) throws DhgDomainException {}
}
