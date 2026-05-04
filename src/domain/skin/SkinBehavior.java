package domain.skin;

import domain.entity.Player;
import domain.exception.DhgDomainException;

/**
 * Strategy interface defining varying attributes and abilities for player skins.
 */
public interface SkinBehavior {
    
    /**
     * Retrieves the speed multiplier granted by this skin.
     * Modifies player base speed.
     * @return The multiplier (e.g., 1.0 is normal, 1.5 is faster).
     * @throws DhgDomainException if calculation fails.
     */
    double getSpeedMultiplier() throws DhgDomainException;

    /**
     * Retrieves the visual or physical size multiplier.
     * Modifies player hitbox size.
     * @return The multiplier (e.g., 1.0 is normal, 0.5 is smaller).
     * @throws DhgDomainException if calculation fails.
     */
    double getSizeMultiplier() throws DhgDomainException;

    /**
     * Resolves the effect of a damaging hit on the player.
     * May consume a shield stack or kill the player.
     * @param player The player wearing the skin.
     * @throws DhgDomainException if hit processing fails.
     */
    void onHit(Player player) throws DhgDomainException;
}
