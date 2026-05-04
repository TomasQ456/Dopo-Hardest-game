package domain.entity;

import domain.exception.DhgDomainException;

/**
 * Base abstract class for unique map hazards or interactive elements.
 */
public abstract class SpecialElement extends Entity {
    @Override
    public void update(double deltaSeconds) throws DhgDomainException {}

    @Override
    public abstract void onContact(Player player) throws DhgDomainException;
}
