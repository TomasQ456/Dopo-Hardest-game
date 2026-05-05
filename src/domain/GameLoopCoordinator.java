package domain;

import domain.exception.DhgDomainException;

/**
 * Manages the fixed-timestep loop, decoupling frame rate from physics logic.
 */
public class GameLoopCoordinator {
    
    private double accumulator;
    private double fixedTimestep;

    /**
     * Constructs a GameLoopCoordinator with the specified fixed timestep.
     * @param fixedTimestep The physics timestep in seconds (e.g., 0.016 for 60 FPS).
     */
    public GameLoopCoordinator(double fixedTimestep) {
        this.fixedTimestep = fixedTimestep;
        this.accumulator = 0.0;
    }

    /**
     * Adds delta time to the accumulator and steps the model logic exactly
     * when the accumulator surpasses the fixed timestep threshold.
     * @param model The game model to update.
     * @param deltaSeconds Raw elapsed time since last render frame.
     */
    public void tick(GameModel model, double deltaSeconds) throws DhgDomainException {
        if (model == null) {
            throw new DhgDomainException("GameModel must not be null");
        }

        this.accumulator += deltaSeconds;

        while (this.accumulator >= this.fixedTimestep) {
            model.update(this.fixedTimestep);
            this.accumulator -= this.fixedTimestep;
        }
    }
}
