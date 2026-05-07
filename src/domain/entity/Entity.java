package domain.entity;

import domain.math.Vector2;
import domain.math.HitBox;
import domain.core.Updatable;
import domain.exception.DhgDomainException;

/**
 * Base abstract class for all physical objects on the map.
 * Defines position, bounding boxes, active state, and fundamental interactions.
 */
public abstract class Entity implements Updatable {

    protected Vector2 position;
    protected HitBox hitBox;
    protected boolean active;

    /**
     * Constructs an Entity with default active state (true).
     */
    public Entity() {
        this.active = true;
    }

    /**
     * Updates the entity's state for the current frame.
     * Overridden by concrete entities (e.g., Actors moving, Animations).
     * @param deltaSeconds The time elapsed since the last frame in seconds.
     * @throws DhgDomainException if the update logic encounters an invalid domain state.
     */
    public abstract void update(double deltaSeconds) throws DhgDomainException;

    /**
     * Checks if this entity's hitbox intersects with another entity's hitbox.
     * Called by Level during the collision checking phase.
     * @param other The other entity to check for intersection.
     * @return true if the entities intersect.
     * @throws DhgDomainException if calculation fails.
     */
    public boolean intersects(Entity other) throws DhgDomainException {
        if (other == null) {
            throw new DhgDomainException(DhgDomainException.ERR_NULL_OTHER);
        }
        if (this.hitBox == null || other.hitBox == null) {
            return false;
        }
        return this.hitBox.getBounds().intersects(other.hitBox.getBounds());
    }

    /**
     * Resolves contact logic when a Player collides with this entity.
     * Utilizes double-dispatch for specific collision behavior (e.g., Coin collects, Bomb damages).
     * @param player The player instance that collided with this entity.
     * @throws DhgDomainException if collision resolution fails.
     */
    public abstract void onContact(Player player) throws DhgDomainException;

    /**
     * Retrieves the current position of the entity.
     * Used by rendering and physics systems.
     * @return The entity's position vector.
     * @throws DhgDomainException if retrieval fails.
     */
    public Vector2 getPosition() throws DhgDomainException {
        if (this.position == null) {
            throw new DhgDomainException(DhgDomainException.ERR_NULL_POSITION);
        }
        return this.position;
    }

    /**
     * Sets the position of the entity.
     * Used when spawning, teleporting, or updating movement.
     * @param pos The new position vector.
     * @throws DhgDomainException if setting the position fails or is invalid.
     */
    public void setPosition(Vector2 pos) throws DhgDomainException {
        if (pos == null) {
            throw new DhgDomainException(DhgDomainException.ERR_NULL_POS);
        }
        this.position = pos;
    }

    public void setHitBox(HitBox hitBox) {
        this.hitBox = hitBox;
    }

    /**
     * Retrieves the hitbox of the entity.
     * Used by the Level's collision system.
     * @return The HitBox instance.
     * @throws DhgDomainException if retrieval fails.
     */
    public HitBox getHitBox() throws DhgDomainException {
        if (this.hitBox == null) {
            throw new DhgDomainException(DhgDomainException.ERR_NULL_HITBOX);
        }
        return this.hitBox;
    }

    /**
     * Checks if the entity is currently active in the game world.
     * Inactive entities are ignored during updates and collisions.
     * @return true if active.
     * @throws DhgDomainException if retrieval fails.
     */
    public boolean isActive() throws DhgDomainException {
        return this.active;
    }

    /**
     * Marks the entity as inactive, effectively removing it from interactions.
     * Used when coins are collected or enemies are destroyed.
     * @throws DhgDomainException if deactivation fails.
     */
    public void deactivate() throws DhgDomainException {
        this.active = false;
    }
}
