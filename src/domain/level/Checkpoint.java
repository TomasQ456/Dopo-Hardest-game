package domain.level;

import domain.entity.Player;
import domain.math.Vector2;
import domain.exception.DhgDomainException;

/**
 * A zone that saves the player's progress and acts as a new respawn point.
 */
public class Checkpoint extends Zone {

    private Vector2 respawnPosition;

    @Override
    public void onContact(Player player) throws DhgDomainException {}
}
