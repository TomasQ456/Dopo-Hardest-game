package domain.entity;

import domain.exception.DhgDomainException;

/**
 * Base abstract class for collectibles in the game.
 * Tracks collected state to prevent multiple collections.
 */
public abstract class Coin extends Entity {

    protected boolean collected;

    @Override
    public void update(double deltaSeconds) throws DhgDomainException {}

    /**
     * Resolves contact with a player, triggering collection logic.
     * @param player The player collecting the coin.
     * @throws DhgDomainException if collection fails.
     */
    @Override
    public abstract void onContact(Player player) throws DhgDomainException;

    /**
     * Checks if this coin has already been collected.
     * @return true if collected.
     * @throws DhgDomainException if state check fails.
     */
    public boolean isCollected() throws DhgDomainException { return false; }

    /**
     * Resets the coin to an uncollected state, typically upon level reset.
     * @throws DhgDomainException if reset fails.
     */
    public void reset() throws DhgDomainException {}
}
