package domain.input;
import domain.entity.Player;
import domain.level.Level;
import domain.math.Direction;
import domain.exception.DhgDomainException;

/** Specific AI behavior logic. */
public class ExpertAI implements AIStrategy {
    @Override public Direction nextDirection(Player player, Level level) throws DhgDomainException {
        // ExpertAI always returns a valid Direction (never null).
        // A simple baseline: return UP.
        return Direction.UP;
    }
}
