package domain.input;
import domain.entity.Player;
import domain.level.Level;
import domain.math.Direction;

/** Specific AI behavior logic. */
public class RandomAI implements AIStrategy {
    @Override public Direction nextDirection(Player player, Level level) { return Direction.NONE; }
}
