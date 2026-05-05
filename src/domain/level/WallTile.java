package domain.level;

import domain.exception.DhgDomainException;

/** Standard solid wall tile preventing movement. */
public class WallTile extends Tile {
    @Override public boolean isSolid() throws DhgDomainException { return true; }
}
