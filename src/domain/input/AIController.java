package domain.input;

import domain.entity.Player;
import domain.level.Level;
import domain.math.Direction;
import domain.exception.DhgDomainException;

/** Delegates movement decisions to an underlying AI Strategy. */
public class AIController implements PlayerController {
    private AIStrategy aiStrategy;

    /**
     * Sets the AI strategy to use for decision making.
     * @param strategy The AIStrategy to delegate to.
     * @throws DhgDomainException if strategy is null.
     */
    public void setStrategy(AIStrategy strategy) throws DhgDomainException {
        if (strategy == null) {
            throw new DhgDomainException(DhgDomainException.ERR_NULL_STRATEGY_PARAM);
        }
        this.aiStrategy = strategy;
    }

    @Override public Direction nextDirection(Player player, Level level) throws DhgDomainException {
        if (this.aiStrategy == null) {
            throw new DhgDomainException(DhgDomainException.ERR_NULL_STRATEGY);
        }
        return this.aiStrategy.nextDirection(player, level);
    }

    @Override
    public void processKeyboardInput(Player player, Direction direction) throws DhgDomainException {
        // AI controllers ignore physical keyboard inputs
    }
}
