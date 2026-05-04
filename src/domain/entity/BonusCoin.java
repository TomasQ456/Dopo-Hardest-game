package domain.entity;

import domain.exception.DhgDomainException;

/**
 * A collectible that adds bonus points to the player's score.
 */
public class BonusCoin extends Coin {
    private int points;
    @Override
    public void onContact(Player player) throws DhgDomainException {}
}
