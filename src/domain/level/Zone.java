package domain.level;

import domain.entity.Entity;
import domain.entity.Player;
import domain.math.Vector2;
import domain.math.Rect;
import domain.exception.DhgDomainException;

/**
 * Base abstract class for logical map areas like Start, Goal, and Checkpoints.
 * Extends Entity to participate in the collision system without moving.
 */
public abstract class Zone extends Entity {

    protected Rect area;
    protected int playerIndex;

    @Override
    public void update(double deltaSeconds) throws DhgDomainException {}

    @Override
    public abstract void onContact(Player player) throws DhgDomainException;

    /**
     * Checks if a specific position is within this zone's bounds.
     * @param position The vector to check.
     * @return true if the position is inside the zone.
     * @throws DhgDomainException if calculation fails.
     */
    public boolean contains(Vector2 position) throws DhgDomainException { return false; }

    /**
     * Retrieves the player index assigned to this zone (used in multiplayer).
     * @return The player index.
     * @throws DhgDomainException if retrieval fails.
     */
    public int getPlayerIndex() throws DhgDomainException { return 0; }
}
