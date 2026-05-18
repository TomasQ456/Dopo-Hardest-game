package domain.input;

import domain.entity.Player;
import domain.level.Level;
import domain.math.Direction;
import domain.exception.DhgDomainException;

/** Reads input from physical devices (keyboard/gamepad). */
public class HumanController implements PlayerController {
    private Direction lastDirection = Direction.NONE;

    @Override
    public void processKeyboardInput(Player player, Direction direction) throws DhgDomainException {
        if (player != null) {
            player.setDesiredDirection(direction);
        }
        this.lastDirection = direction;
    }

    @Override public Direction nextDirection(Player player, Level level) throws DhgDomainException {
        return this.lastDirection;
    }
}
