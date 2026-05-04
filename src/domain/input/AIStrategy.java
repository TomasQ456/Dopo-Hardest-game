package domain.input;

import domain.entity.Player;
import domain.level.Level;
import domain.math.Direction;

/** Strategy interface defining different computer-controlled intelligence levels. */
public interface AIStrategy {
    
    /**
     * Calculates the best next direction based on the current state.
     * @param player The AI-controlled player.
     * @param level The current level state.
     * @return The calculated Direction.
     */
    Direction nextDirection(Player player, Level level);
}
