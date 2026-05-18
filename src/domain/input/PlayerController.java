package domain.input;

import domain.entity.Player;
import domain.level.Level;
import domain.math.Direction;
import domain.exception.DhgDomainException;

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
    Direction nextDirection(Player player, Level level) throws DhgDomainException;
    
    /**
     * Processes raw physical keyboard input if applicable to this controller.
     * @param player The player receiving the input.
     * @param direction The direction pressed on the keyboard.
     * @throws DhgDomainException if processing fails.
     */
    void processKeyboardInput(Player player, Direction direction) throws DhgDomainException;
}
