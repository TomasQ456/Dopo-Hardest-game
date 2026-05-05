package domain.entity;

import domain.level.TileMap;
import domain.exception.DhgDomainException;

/**
 * An enemy that gains speed over time or under specific conditions.
 */
public class AcceleratedEnemy extends Enemy {

    private double speedMultiplier = 1.0;
    private double elapsedSeconds = 0.0;

    /**
     * Constructs an AcceleratedEnemy with specified speed.
     * @param speed The initial movement speed.
     */
    public AcceleratedEnemy(double speed) {
        super(speed);
    }

    @Override
    public void update(double deltaSeconds) throws DhgDomainException {
        this.elapsedSeconds += deltaSeconds;
        // Gradually increase speed multiplier
        this.speedMultiplier = 1.0 + (this.elapsedSeconds / 10.0);
    }

    @Override
    protected void applyBounds(TileMap tileMap) throws DhgDomainException {}

    /**
     * Resets the acceleration.
     */
    public void reset() throws DhgDomainException {
        this.speedMultiplier = 1.0;
        this.elapsedSeconds = 0.0;
    }

    /**
     * Gets the effective speed with acceleration applied.
     */
    public double getEffectiveSpeed() {
        return this.speed * this.speedMultiplier;
    }
}
