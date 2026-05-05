package domain.level;

import domain.exception.DhgDomainException;

/** A visual or logical goal tile. */
public class GoalTile extends Tile {
    @Override public boolean isSolid() throws DhgDomainException { return false; }
}
