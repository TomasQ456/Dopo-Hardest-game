package domain.level;

import domain.exception.DhgDomainException;

/** Standard walkable floor tile. */
public class FloorTile extends Tile {
    @Override public boolean isSolid() throws DhgDomainException { return false; }
    @Override public void accept(TileVisitor visitor) throws DhgDomainException { visitor.visit(this); }
}
