package domain.level;

import domain.entity.Player;
import domain.exception.DhgDomainException;

/**
 * The zone a player must reach to complete the level.
 */
public class GoalZone extends Zone {
    @Override
    public void onContact(Player player) throws DhgDomainException {
        // GoalZone signals level completion.
        // The actual level completion logic is handled by GameMode.isLevelComplete()
        // which checks if player is in goal zone and all win conditions are met.
    }
}
