package domain.level;

import domain.exception.DhgDomainException;

/** A visual or logical starting tile. */
public class StartTile extends Tile {
    @Override public boolean isSolid() throws DhgDomainException { return false; }
    @Override public void accept(TileVisitor visitor) throws DhgDomainException { visitor.visit(this); }
}
