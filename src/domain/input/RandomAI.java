package domain.input;
import domain.entity.Player;
import domain.level.Level;
import domain.math.Direction;
import domain.exception.DhgDomainException;
import java.util.Random;

/** Specific AI behavior logic. */
public class RandomAI implements AIStrategy {
    private Random random = new Random();

    @Override public Direction nextDirection(Player player, Level level) throws DhgDomainException {
        Direction[] cardinalDirections = {
            Direction.NONE,
            Direction.UP,
            Direction.DOWN,
            Direction.LEFT,
            Direction.RIGHT
        };
        return cardinalDirections[random.nextInt(cardinalDirections.length)];
    }
}
