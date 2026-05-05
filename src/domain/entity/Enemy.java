package domain.entity;

import domain.exception.DhgDomainException;

/**
 * Base abstract class for all antagonistic entities in the game.
 * Handles common enemy behaviors and fatal collision interactions with the player.
 */
public abstract class Enemy extends Actor {

    public Enemy(double speed) {
        super(speed);
    }

    @Override
    public abstract void update(double deltaSeconds) throws DhgDomainException;

    /**
     * Resolves collision with the player, typically resulting in player death or shield damage.
     * Called by Level's collision system.
     * @param player The player that collided with this enemy.
     * @throws DhgDomainException if collision logic fails.
     */
    @Override
    public void onContact(Player player) throws DhgDomainException {
        if (!player.absorbHit()) {
            player.registerDeath();
        }
    }
}
