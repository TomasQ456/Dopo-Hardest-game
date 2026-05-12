package domain.level;

import domain.entity.Player;
import domain.math.Vector2;
import domain.exception.DhgDomainException;

/**
 * A zone that saves the player's progress and acts as a new respawn point.
 */
public class Checkpoint extends Zone {

    private Vector2 respawnPosition;

    /**
     * Constructs a Checkpoint with the specified respawn position.
     * @param respawnPosition The position where players respawn at this checkpoint.
     */
    public Checkpoint(Vector2 respawnPosition) {
        this.respawnPosition = respawnPosition;
    }

    /**
     * Retrieves the respawn position for this checkpoint.
     * @return The respawn position vector.
     */
    public Vector2 getRespawnPosition() throws DhgDomainException {
        if (this.respawnPosition == null) {
            throw new DhgDomainException(DhgDomainException.ERR_NULL_POSITION);
        }
        return this.respawnPosition;
    }

    public void setRespawnPosition(Vector2 respawnPosition) throws DhgDomainException {
        if (respawnPosition == null) {
            throw new DhgDomainException(DhgDomainException.ERR_NULL_POSITION);
        }
        this.respawnPosition = respawnPosition;
    }

    @Override
    public void onContact(Player player) throws DhgDomainException {
        // Checkpoint activation is handled by Level
    }
}
