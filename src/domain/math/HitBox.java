package domain.math;

import domain.exception.DhgDomainException;

/**
 * Encapsulates the collision boundaries for an Entity.
 * Wraps a Rect and may include additional collision-related properties in the future.
 */
public class HitBox {
    
    private Rect bounds;

    /**
     * Retrieves the bounding rectangle of this hitbox.
     * Interacts with Level collision detection to get the spatial area.
     * @return The Rect defining the bounds.
     * @throws DhgDomainException if the bounds cannot be retrieved.
     */
    public Rect getBounds() throws DhgDomainException {
        return null;
    }
}
