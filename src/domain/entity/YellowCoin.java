package domain.entity;

import domain.exception.DhgDomainException;

/**
 * A standard coin required to progress or complete a level.
 */
public class YellowCoin extends Coin {
    @Override
    public void onContact(Player player) throws DhgDomainException {}
}
