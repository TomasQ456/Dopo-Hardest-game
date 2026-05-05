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
        if (this.bounds == null) {
            throw new DhgDomainException(DhgDomainException.ERR_BOUNDS_NOT_INITIALIZED);
        }
        return this.bounds;
    }

    public HitBox(Rect bounds) throws DhgDomainException {
        if (bounds == null) {
            throw new DhgDomainException(DhgDomainException.ERR_NULL_BOUNDS_PARAM);
        }
        this.bounds = bounds;
    }
}
