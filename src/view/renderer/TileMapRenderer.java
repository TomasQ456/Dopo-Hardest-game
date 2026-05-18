package view.renderer;

import java.awt.Color;
import java.awt.Graphics2D;

import domain.exception.DhgDomainException;
import domain.level.Tile;
import domain.level.TileMap;
import domain.level.GoalTile;
import domain.level.StartTile;
import domain.level.WallTile;

import domain.level.TileVisitor;
import domain.level.FloorTile;

public class TileMapRenderer implements TileVisitor {

    private static final Color COLOR_WALL  = new Color(50, 50, 100);
    private static final Color COLOR_FLOOR = new Color(240, 240, 240);
    private static final Color COLOR_START = new Color(100, 200, 100);
    private static final Color COLOR_GOAL  = new Color(255, 215, 0);
    private static final Color COLOR_GRID  = new Color(180, 180, 180);
    private final int tileSize;
    private Color currentFill;

    public TileMapRenderer(int tileSize) {
        this.tileSize = tileSize;
    }

    public void render(Graphics2D g2d, int panelWidth, int panelHeight) {
        g2d.setColor(new Color(200, 200, 200));
        g2d.fillRect(0, 0, panelWidth, panelHeight);
    }

    public void render(Graphics2D g2d, TileMap tileMap, int panelWidth, int panelHeight) throws DhgDomainException {
        int cols = panelWidth / tileSize;
        int rows = panelHeight / tileSize;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = col * tileSize;
                int y = row * tileSize;
                Tile tile = tileMap.getTile(col, row);
                
                this.currentFill = COLOR_FLOOR; // Default
                if (tile != null) {
                    tile.accept(this);
                }
                
                g2d.setColor(this.currentFill);
                g2d.fillRect(x, y, tileSize, tileSize);
                g2d.setColor(COLOR_GRID);
                g2d.drawRect(x, y, tileSize, tileSize);
            }
        }
    }

    @Override public void visit(WallTile tile) { this.currentFill = COLOR_WALL; }
    @Override public void visit(StartTile tile) { this.currentFill = COLOR_START; }
    @Override public void visit(GoalTile tile) { this.currentFill = COLOR_GOAL; }
    @Override public void visit(FloorTile tile) { this.currentFill = COLOR_FLOOR; }
}
