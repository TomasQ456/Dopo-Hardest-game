package domain.state;
import domain.GameModel;

/** Specific phase of the application loop. */
public class GameOverState implements GamePhaseState {
    @Override public void onEnter(GameModel model) {}
    @Override public void update(GameModel model, double deltaSeconds) {}
    @Override public void onExit(GameModel model) {}
}
