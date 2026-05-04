package domain.input;

import domain.entity.Player;
import domain.level.Level;
import domain.math.Direction;

/** Reads input from physical devices (keyboard/gamepad). */
public class HumanController implements PlayerController {
    @Override public Direction nextDirection(Player player, Level level) { return Direction.NONE; }
}
