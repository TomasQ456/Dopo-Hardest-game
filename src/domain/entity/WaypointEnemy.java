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

    @Override
    public void update(double deltaSeconds) throws DhgDomainException {}

    @Override
    public void onContact(Player player) throws DhgDomainException {}

    @Override
    protected void applyBounds(TileMap tileMap) throws DhgDomainException {}
}
