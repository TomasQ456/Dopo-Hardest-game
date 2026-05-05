package domain.entity;

import domain.skin.SkinBehavior;
import domain.exception.DhgDomainException;

/**
 * A special coin that grants the player a new SkinBehavior upon collection.
 */
public class SkinCoin extends Coin {
    private SkinBehavior grantedSkin;

    /**
     * Constructs a SkinCoin with the specified skin to grant.
     * @param grantedSkin The SkinBehavior to apply when collected.
     */
    public SkinCoin(SkinBehavior grantedSkin) {
        this.grantedSkin = grantedSkin;
    }

    @Override
    public void onContact(Player player) throws DhgDomainException {
        if (this.collected) {
            return;
        }
        this.collected = true;
        player.applySkin(this.grantedSkin);
        this.deactivate();
    }
}
