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
     * Constructs a Rect with specified bounds.
     * @param x The x-coordinate of the rectangle.
     * @param y The y-coordinate of the rectangle.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     */
    public Rect(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Checks if this rectangle intersects with another rectangle.
     * Used by the collision system to determine if two entities are touching.
     * @param other The other rectangle to check against.
     * @return true if the rectangles overlap, false otherwise.
     * @throws DhgDomainException if intersection calculation fails.
     */
    public boolean intersects(Rect other) throws DhgDomainException {
        if (other == null) {
            throw new DhgDomainException(DhgDomainException.ERR_NULL_OTHER_RECT);
        }
        boolean noOverlap = this.x + this.width <= other.x
                || other.x + other.width <= this.x
                || this.y + this.height <= other.y
                || other.y + other.height <= this.y;
        return !noOverlap;
    }
}
