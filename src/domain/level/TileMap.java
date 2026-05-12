package domain.level;

import domain.math.Vector2;
import domain.exception.DhgDomainException;

/**
 * Represents the 2D grid of tiles that make up the static map geometry.
 */
public class TileMap {

    private final int width;
    private final int height;
    private final Tile[][] tiles;
    private static final int TILE_SIZE = 40; // Assume 40x40 pixel tiles

    /**
     * Constructs a TileMap with specified dimensions and tiles.
     * @param width Grid width in tiles.
     * @param height Grid height in tiles.
     * @param tiles The 2D tile grid.
     */
    public TileMap(int width, int height, Tile[][] tiles) {
        this.width = width;
        this.height = height;
        this.tiles = tiles;
    }

    public int getWidth() {
        return this.tiles == null ? this.width : this.tiles.length;
    }

    public int getHeight() {
        return this.tiles == null ? this.height : (this.tiles.length == 0 ? 0 : this.tiles[0].length);
    }

    /**
     * Retrieves the tile at the specified grid coordinates.
     * Used for rendering and collision checking.
     * @param x The grid X coordinate.
     * @param y The grid Y coordinate.
     * @return The Tile at the coordinates, or null/solid if out of bounds.
     */
    public Tile getTile(int x, int y) {
        if (this.tiles == null || x < 0 || x >= this.tiles.length) {
            return null;
        }
        if (y < 0 || y >= this.tiles[x].length) {
            return null;
        }
        return this.tiles[x][y];
    }

    /**
     * Checks if a world position vector corresponds to a walkable tile.
     * Interacts with Actor bounding logic.
     * @param position The physical world position.
     * @return true if the underlying tile is not solid.
     */
    public boolean isWalkable(Vector2 position) throws DhgDomainException {
        int gridX = (int) (position.x / TILE_SIZE);
        int gridY = (int) (position.y / TILE_SIZE);
        Tile tile = getTile(gridX, gridY);
        if (tile == null) {
            return false;
        }
        return !tile.isSolid();
    }
}

