package domain.level;

public interface TileVisitor {
    void visit(WallTile tile);
    void visit(StartTile tile);
    void visit(GoalTile tile);
    void visit(FloorTile tile);
}
