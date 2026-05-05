package domain.input;

import domain.entity.Player;
import domain.level.Level;
import domain.math.Direction;
import domain.exception.DhgDomainException;

/** Reads input from physical devices (keyboard/gamepad). */
public class HumanController implements PlayerController {
    private Direction lastDirection = Direction.NONE;

    /**
     * Registers the currently pressed direction from input hardware.
     * @param direction The direction being pressed.
     */
    public void registerInput(Direction direction) {
        this.lastDirection = direction;
    }

    @Override public Direction nextDirection(Player player, Level level) throws DhgDomainException {
        return this.lastDirection;
    }
}
