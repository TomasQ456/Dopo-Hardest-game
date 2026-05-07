package domain.level;

import domain.entity.Player;
import domain.exception.DhgDomainException;

/**
 * The zone where a player spawns at the beginning of a level.
 */
public class StartZone extends Zone {
    @Override
    public void onContact(Player player) throws DhgDomainException {
        // StartZone does not harm the player
    }
}

