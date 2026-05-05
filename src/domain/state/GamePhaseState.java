package domain.state;

import domain.GameModel;
import domain.exception.DhgDomainException;

/**
 * State Pattern interface defining the high-level flow of the application.
 */
public interface GamePhaseState {

    /**
     * Executed exactly once when transitioning into this state.
     * @param model The GameModel context.
     */
    void onEnter(GameModel model) throws DhgDomainException;

    /**
     * Executed every frame while this state is active.
     * @param model The GameModel context.
     * @param deltaSeconds Raw elapsed time.
     */
    void update(GameModel model, double deltaSeconds) throws DhgDomainException;

    /**
     * Executed exactly once when transitioning out of this state.
     * @param model The GameModel context.
     */
    void onExit(GameModel model) throws DhgDomainException;
}
