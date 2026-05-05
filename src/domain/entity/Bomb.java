package domain.entity;

import domain.exception.DhgDomainException;

/**
 * A hazard element that causes immediate player death upon contact.
 */
public class Bomb extends SpecialElement {
    @Override
    public void onContact(Player player) throws DhgDomainException {
        player.registerDeath();
    }
}
