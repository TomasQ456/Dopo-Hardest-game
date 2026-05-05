package domain.entity;

import domain.exception.DhgDomainException;

/**
 * An interactive element that grants health, lives, or restores shields.
 */
public class LifeSource extends SpecialElement {
    @Override
    public void onContact(Player player) throws DhgDomainException {
        player.addShieldHit();
        this.deactivate();
    }
}
