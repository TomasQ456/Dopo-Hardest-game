package domain.state;
import domain.GameModel;
import domain.core.ModelEvent;
import domain.core.ModelEventType;
import domain.exception.DhgDomainException;

/** Specific phase of the application loop. */
public class GameOverState implements GamePhaseState {

    @Override
    public void onEnter(GameModel model) throws DhgDomainException {
        // Notify observers of game over
        ModelEvent event = new ModelEvent(ModelEventType.PLAYER_DIED);
        model.notifyObservers(event);
    }

    @Override
    public void update(GameModel model, double deltaSeconds) throws DhgDomainException {
        // Do not update game logic
    }

    @Override
    public void onExit(GameModel model) throws DhgDomainException {
        // Cleanup on game over exit
    }
}
