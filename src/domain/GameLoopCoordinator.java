package domain;

/**
 * Manages the fixed-timestep loop, decoupling frame rate from physics logic.
 */
public class GameLoopCoordinator {
    
    private double accumulator;
    private double fixedTimestep;

    /**
     * Adds delta time to the accumulator and steps the model logic exactly
     * when the accumulator surpasses the fixed timestep threshold.
     * @param model The game model to update.
     * @param deltaSeconds Raw elapsed time since last render frame.
     */
    public void tick(GameModel model, double deltaSeconds) {}
}
