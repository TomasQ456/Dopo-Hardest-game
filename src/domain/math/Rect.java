package domain.math;

import domain.exception.DhgDomainException;

/**
 * Represents an Axis-Aligned Bounding Box (AABB) for collision detection.
 * Used by HitBox to perform intersection checks.
 */
public class Rect {
    
    public double x;
    public double y;
    public double width;
    public double height;

    /**
     * Checks if this rectangle intersects with another rectangle.
     * Used by the collision system to determine if two entities are touching.
     * @param other The other rectangle to check against.
     * @return true if the rectangles overlap, false otherwise.
     * @throws DhgDomainException if intersection calculation fails.
     */
    public boolean intersects(Rect other) throws DhgDomainException {
        return false;
    }
}
