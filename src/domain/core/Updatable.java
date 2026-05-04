package domain.core;

import domain.exception.DhgDomainException;

/**
 * Interface for objects that update every frame.
 * Ensures the entity can process time-based logic.
 */
public interface Updatable {
    /**
     * Updates the object's state based on elapsed time.
     * Called by the GameLoopCoordinator or Level update loop.
     * @param delta Time elapsed since last frame in seconds.
     * @throws DhgDomainException if update logic fails.
     */
    void update(double delta) throws DhgDomainException;
}
