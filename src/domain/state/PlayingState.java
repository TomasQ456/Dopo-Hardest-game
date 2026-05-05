package domain.state;
import domain.GameModel;
import domain.exception.DhgDomainException;
import domain.level.Level;

/** Specific phase of the application loop. */
public class PlayingState implements GamePhaseState {

    @Override
    public void onEnter(GameModel model) throws DhgDomainException {
        // Start level update loop
    }

    @Override
    public void update(GameModel model, double deltaSeconds) throws DhgDomainException {
        Level level = model.getCurrentLevel();
        if (level != null) {
            level.update(deltaSeconds);
        }
    }

    @Override
    public void onExit(GameModel model) throws DhgDomainException {
        // Suspend update delegation
    }
}
