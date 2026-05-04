package domain.level;

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
    public boolean isSolid() { return false; }
}
