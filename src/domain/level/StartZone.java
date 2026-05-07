package domain.level;

import domain.entity.Player;
import domain.exception.DhgDomainException;
import domain.math.Vector2;
import domain.math.Rect;
import domain.math.HitBox;

/**
 * The zone where a player spawns at the beginning of a level.
 */
public class StartZone extends Zone {

    private Vector2 spawnPosition;

    /**
     * Constructs a StartZone with area and spawn position.
     * @param position The center position of the zone.
     * @param area The rectangular area of this zone.
     * @param spawnPosition The position where players respawn.
     * @throws DhgDomainException if parameters are invalid.
     */
    public StartZone(Vector2 position, Rect area, Vector2 spawnPosition)
        throws DhgDomainException {
        super();
        this.position = position;
        this.hitBox = new HitBox(area);
        this.area = area;
        this.spawnPosition = (spawnPosition != null) ? spawnPosition : position;
    }

    /**
     * Package-private getter for the spawn position.
     * @return The spawn position vector.
     */
    Vector2 getSpawnPosition() {
        return spawnPosition;
    }

    @Override
    public void onContact(Player player) throws DhgDomainException {
        // StartZone contact has no effect after initial spawn
    }
}

