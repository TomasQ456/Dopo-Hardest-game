package domain.entity;

import domain.level.TileMap;
import domain.exception.DhgDomainException;

/**
 * Base abstract class for moving entities like Players and Enemies.
 * Extends Entity to add speed and boundary enforcement logic.
 */
public abstract class Actor extends Entity {

    protected double speed;

    /**
     * Constructs an Actor with specified speed.
     * @param speed The movement speed of the actor.
     */
    public Actor(double speed) {
        super();
        this.speed = speed;
    }

    public double getSpeed() throws DhgDomainException {
        return this.speed;
    }

    public void setSpeed(double speed) throws DhgDomainException {
        this.speed = speed;
    }

    /**
     * Updates the actor's state, primarily handling movement based on speed.
     * @param deltaSeconds The time elapsed since the last frame in seconds.
     * @throws DhgDomainException if the update logic fails.
     */
    @Override
    public abstract void update(double deltaSeconds) throws DhgDomainException;

    /**
     * Constrains the actor within the valid, walkable bounds of the TileMap.
     * Prevents actors from moving through walls or out of bounds.
     * @param tileMap The map containing walkable and solid tiles.
     * @throws DhgDomainException if boundary logic evaluation fails.
     */
    protected abstract void applyBounds(TileMap tileMap) throws DhgDomainException;
}
