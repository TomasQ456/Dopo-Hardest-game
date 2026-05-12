package view.renderer;

import java.awt.Color;
import java.awt.Graphics2D;

import domain.exception.DhgDomainException;
import domain.level.Tile;
import domain.level.TileMap;
import domain.level.GoalTile;
import domain.level.StartTile;
import domain.level.WallTile;

public class TileMapRenderer {

    private static final Color COLOR_WALL  = new Color(50, 50, 100);
    private static final Color COLOR_FLOOR = new Color(240, 240, 240);
    private static final Color COLOR_START = new Color(100, 200, 100);
    private static final Color COLOR_GOAL  = new Color(255, 215, 0);
    private static final Color COLOR_GRID  = new Color(180, 180, 180);
    private final int tileSize;

    public TileMapRenderer(int tileSize) {
        this.tileSize = tileSize;
    }

    public void render(Graphics2D g2d, int panelWidth, int panelHeight) {
        g2d.setColor(new Color(200, 200, 200));
        g2d.fillRect(0, 0, panelWidth, panelHeight);
    }

    public void render(Graphics2D g2d, TileMap tileMap, int panelWidth, int panelHeight) throws DhgDomainException {
        int cols = Math.min(panelWidth / tileSize, tileMap.getWidth());
        int rows = Math.min(panelHeight / tileSize, tileMap.getHeight());

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = col * tileSize;
                int y = row * tileSize;
                Tile tile = tileMap.getTile(col, row);
                Color fill = COLOR_FLOOR;
                if (tile instanceof WallTile)       fill = COLOR_WALL;
                else if (tile instanceof StartTile) fill = COLOR_START;
                else if (tile instanceof GoalTile)  fill = COLOR_GOAL;
                g2d.setColor(fill);
                g2d.fillRect(x, y, tileSize, tileSize);
                g2d.setColor(COLOR_GRID);
                g2d.drawRect(x, y, tileSize, tileSize);
            }
        }
    }
}
