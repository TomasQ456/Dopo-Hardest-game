package domain.entity;

import domain.level.TileMap;
import domain.exception.DhgDomainException;

/**
 * An enemy that gains speed over time or under specific conditions.
 */
public class AcceleratedEnemy extends Enemy {

    private double speedMultiplier;

    @Override
    public void update(double deltaSeconds) throws DhgDomainException {}

    @Override
    public void onContact(Player player) throws DhgDomainException {}

    @Override
    protected void applyBounds(TileMap tileMap) throws DhgDomainException {}
}
