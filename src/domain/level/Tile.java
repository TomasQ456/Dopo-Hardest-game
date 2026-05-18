package domain.level;

import domain.exception.DhgDomainException;

/**
 * Base abstract class representing a grid cell in the TileMap.
 */
public abstract class Tile {
    
    private int x;
    private int y;

    /**
     * Determines if this tile acts as a physical barrier.
     * Interacts with Actor bounding logic to prevent walking through walls.
     * @return true if the tile is solid (unwalkable), false otherwise.
     */
    public boolean isSolid() throws DhgDomainException { return false; }

    /**
     * Accepts a visitor for polymorphic operations.
     * @param visitor The visitor to accept.
     * @throws DhgDomainException if visit fails.
     */
    public abstract void accept(TileVisitor visitor) throws DhgDomainException;
}
