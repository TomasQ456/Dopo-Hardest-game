package domain.entity;

import domain.math.Vector2;
import domain.level.TileMap;
import domain.exception.DhgDomainException;

/**
 * An enemy that moves continuously in a straight line, bouncing off walls.
 */
public class LinearEnemy extends Enemy {

    private Vector2 direction;

    /**
     * Constructs a LinearEnemy with specified speed and direction.
     * @param speed The movement speed.
     * @param direction The initial direction to move.
     */
    public LinearEnemy(double speed, Vector2 direction) {
        super(speed);
        this.direction = direction;
    }

    public Vector2 getDirection() throws DhgDomainException {
        return this.direction;
    }

    public void setDirection(Vector2 direction) throws DhgDomainException {
        this.direction = direction;
    }

    @Override
    public void update(double deltaSeconds) throws DhgDomainException {
        if (this.position == null || this.direction == null) {
            return;
        }
        Vector2 movement = this.direction.scale(this.speed * deltaSeconds);
        Vector2 newPosition = this.position.add(movement);
        this.position = newPosition;
    }

    @Override
    protected void applyBounds(TileMap tileMap) throws DhgDomainException {
        // Reverse direction if hitting a boundary (simplified)
        if (this.position.x < 0 || this.position.x > 320) {
            this.direction = new Vector2(-this.direction.x, this.direction.y);
        }
        if (this.position.y < 0 || this.position.y > 240) {
            this.direction = new Vector2(this.direction.x, -this.direction.y);
        }
    }
}
