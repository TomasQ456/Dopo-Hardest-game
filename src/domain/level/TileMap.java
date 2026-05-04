package domain.level;

import domain.math.Vector2;

/**
 * Represents the 2D grid of tiles that make up the static map geometry.
 */
public class TileMap {

    private int width;
    private int height;
    private Tile[][] tiles;

    /**
     * Retrieves the tile at the specified grid coordinates.
     * Used for rendering and collision checking.
     * @param x The grid X coordinate.
     * @param y The grid Y coordinate.
     * @return The Tile at the coordinates, or null/solid if out of bounds.
     */
    public Tile getTile(int x, int y) { return null; }

    /**
     * Checks if a world position vector corresponds to a walkable tile.
     * Interacts with Actor bounding logic.
     * @param position The physical world position.
     * @return true if the underlying tile is not solid.
     */
    public boolean isWalkable(Vector2 position) { return false; }
}
