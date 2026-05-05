package domain.state;
import domain.GameModel;
import domain.exception.DhgDomainException;

/** Specific phase of the application loop. */
public class PausedState implements GamePhaseState {

    @Override
    public void onEnter(GameModel model) throws DhgDomainException {
        // Pause the game
    }

    @Override
    public void update(GameModel model, double deltaSeconds) throws DhgDomainException {
        // Do not advance level time or entity positions
        // Game is paused
    }

    @Override
    public void onExit(GameModel model) throws DhgDomainException {
        // Resume the game
    }
}
