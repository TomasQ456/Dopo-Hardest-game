package domain.level;

/** Standard solid wall tile preventing movement. */
public class WallTile extends Tile {
    @Override public boolean isSolid() { return true; }
}
