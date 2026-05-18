package domain.entity;

import domain.exception.DhgDomainException;

/**
 * A collectible that adds bonus points to the player's score.
 */
public class BonusCoin extends Coin {
    private int points;

    /**
     * Constructs a BonusCoin with specified points.
     * @param points The number of points this coin grants.
     */
    public BonusCoin(int points) {
        this.points = points;
    }

    @Override
    public void onContact(Player player) throws DhgDomainException {
        if (this.collected) {
            return;
        }
        this.collected = true;
        player.addScore(this.points);
        this.deactivate();
    }

    @Override
    public void accept(EntityVisitor visitor) throws DhgDomainException {
        visitor.visit(this);
    }
}
