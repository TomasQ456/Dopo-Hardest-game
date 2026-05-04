package domain.input;

import domain.entity.Player;
import domain.level.Level;
import domain.math.Direction;

/** Delegates movement decisions to an underlying AI Strategy. */
public class AIController implements PlayerController {
    private AIStrategy aiStrategy;
    @Override public Direction nextDirection(Player player, Level level) { return Direction.NONE; }
}
