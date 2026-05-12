package domain.math;

import domain.exception.DhgDomainException;

import java.io.Serializable;
import java.util.Vector;

/**
 * Represents a 2D vector for positions and velocities.
 * Used extensively across the domain for spatial calculations.
 */
public class Vector2 implements Serializable {
    public double x;
    public double y;

    /**
     * Constructs a Vector2 with default values (0, 0).
     */
    public Vector2() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Constructs a Vector2 with specified x and y coordinates.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Adds another vector to this vector.
     * Interacts with Actor movement logic to calculate new positions.
     * @param other The vector to add to this one.
     * @return A new Vector2 representing the sum.
     * @throws DhgDomainException if the mathematical calculation fails.
     */
    public Vector2 add(Vector2 other) throws DhgDomainException {
        if (other == null) {
            throw new DhgDomainException(DhgDomainException.ERR_NULL_OTHER_VECTOR);
        }
        Vector2 result = new Vector2();
        result.x = this.x + other.x;
        result.y = this.y + other.y;
        return result;
    }

    public Vector2 scale(double factor) {
        Vector2 result = new Vector2();
        result.x = this.x * factor;
        result.y = this.y * factor;
        return result;
    }
}
