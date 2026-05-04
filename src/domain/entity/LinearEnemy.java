package domain.entity;

import domain.math.Vector2;
import domain.level.TileMap;
import domain.exception.DhgDomainException;

/**
 * An enemy that moves continuously in a straight line, bouncing off walls.
 */
public class LinearEnemy extends Enemy {

    private Vector2 direction;

    @Override
    public void update(double deltaSeconds) throws DhgDomainException {}

    @Override
    public void onContact(Player player) throws DhgDomainException {}

    @Override
    protected void applyBounds(TileMap tileMap) throws DhgDomainException {}
}
