package domain.input;

import domain.entity.Player;
import domain.level.Level;
import domain.math.Direction;

/**
 * Interface mapping physical or AI input to Player movement directions.
 */
public interface PlayerController {
    
    /**
     * Determines the next desired direction for the controlled player.
     * Interacts with the Player class each frame.
     * @param player The player being controlled.
     * @param level The current level context (useful for AI).
     * @return The intended Direction.
     */
    Direction nextDirection(Player player, Level level);
}
