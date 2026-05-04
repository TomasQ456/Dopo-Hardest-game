package domain.state;

import domain.GameModel;

/**
 * State Pattern interface defining the high-level flow of the application.
 */
public interface GamePhaseState {

    /**
     * Executed exactly once when transitioning into this state.
     * @param model The GameModel context.
     */
    void onEnter(GameModel model);

    /**
     * Executed every frame while this state is active.
     * @param model The GameModel context.
     * @param deltaSeconds Raw elapsed time.
     */
    void update(GameModel model, double deltaSeconds);

    /**
     * Executed exactly once when transitioning out of this state.
     * @param model The GameModel context.
     */
    void onExit(GameModel model);
}
