package domain.entity;

import domain.exception.DhgDomainException;

/**
 * An interactive or dynamic wall that acts as a solid physical barrier.
 */
public class SolidWall extends SpecialElement {
    @Override
    public void onContact(Player player) throws DhgDomainException {
        // SolidWall does not modify player state on contact.
        // Collision resolution (position adjustment) is handled by the Level's collision system.
    }

    @Override
    public void accept(EntityVisitor visitor) throws DhgDomainException {
        visitor.visit(this);
    }
}
