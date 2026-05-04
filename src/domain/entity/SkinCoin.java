package domain.entity;

import domain.skin.SkinBehavior;
import domain.exception.DhgDomainException;

/**
 * A special coin that grants the player a new SkinBehavior upon collection.
 */
public class SkinCoin extends Coin {
    private SkinBehavior grantedSkin;
    @Override
    public void onContact(Player player) throws DhgDomainException {}
}
