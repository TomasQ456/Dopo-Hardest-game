package domain.math;

import domain.exception.DhgDomainException;

/**
 * Represents a 2D vector for positions and velocities.
 * Used extensively across the domain for spatial calculations.
 */
public class Vector2 {
    
    public double x;
    public double y;

    /**
     * Adds another vector to this vector.
     * Interacts with Actor movement logic to calculate new positions.
     * @param other The vector to add to this one.
     * @return A new Vector2 representing the sum.
     * @throws DhgDomainException if the mathematical calculation fails.
     */
    public Vector2 add(Vector2 other) throws DhgDomainException {
        return null;
    }
}
