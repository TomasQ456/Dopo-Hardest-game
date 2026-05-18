package view.renderer;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.util.List;

import domain.entity.Entity;
import domain.entity.Player;
import domain.entity.Enemy;
import domain.entity.YellowCoin;
import domain.entity.BonusCoin;
import domain.entity.Bomb;
import domain.entity.LifeSource;
import domain.exception.DhgDomainException;
import domain.level.Level;
import domain.math.Vector2;

import domain.entity.EntityVisitor;
import domain.entity.SkinCoin;
import domain.entity.SolidWall;
import domain.entity.SpecialElement;

public class EntityRenderer implements EntityVisitor {

    private static final Color COLOR_PLAYER1    = new Color(220, 50, 50);
    private static final Color COLOR_PLAYER2    = new Color(50, 50, 220);
    private static final Color COLOR_ENEMY      = new Color(30, 144, 255);
    private static final Color COLOR_COIN       = new Color(255, 215, 0);
    private static final Color COLOR_BONUS_COIN = new Color(255, 140, 0);
    private static final Color COLOR_BOMB       = new Color(40, 40, 40);
    private static final Color COLOR_LIFE       = new Color(50, 220, 50);
    private static final Color COLOR_BORDER     = Color.BLACK;
    private static final int   ENTITY_SIZE      = 30;
    private static final int   COIN_SIZE        = 16;
    private final int tileSize;

    private Graphics2D currentG2d;
    private int currentX;
    private int currentY;

    public EntityRenderer(int tileSize) {
        this.tileSize = tileSize;
    }

    public void render(Graphics2D g2d) {
    }

    public void renderLevel(Graphics2D g2d, Level level) throws DhgDomainException {
        this.currentG2d = g2d;
        List<Entity> entities = level.getEntities();
        for (Entity entity : entities) {
            if (!entity.isActive()) continue;
            Vector2 pos = entity.getPosition();
            this.currentX = (int) pos.x;
            this.currentY = (int) pos.y;
            
            // Polymorphic dispatch! Zero instanceof used.
            entity.accept(this);
        }
    }

    @Override
    public void visit(Player player) {
        drawSquareEntity(currentG2d, currentX, currentY, ENTITY_SIZE, COLOR_PLAYER1, COLOR_BORDER, "P");
    }

    @Override
    public void visit(Enemy enemy) {
        drawCircleEntity(currentG2d, currentX, currentY, ENTITY_SIZE, COLOR_ENEMY, COLOR_BORDER, "E");
    }

    @Override
    public void visit(YellowCoin coin) {
        drawCircleEntity(currentG2d, currentX, currentY, COIN_SIZE, COLOR_COIN, COLOR_BORDER, "");
    }

    @Override
    public void visit(BonusCoin coin) {
        drawCircleEntity(currentG2d, currentX, currentY, COIN_SIZE, COLOR_BONUS_COIN, COLOR_BORDER, "");
    }

    @Override
    public void visit(Bomb bomb) {
        drawCircleEntity(currentG2d, currentX, currentY, ENTITY_SIZE, COLOR_BOMB, COLOR_BORDER, "B");
    }

    @Override
    public void visit(LifeSource source) {
        drawCircleEntity(currentG2d, currentX, currentY, ENTITY_SIZE, COLOR_LIFE, COLOR_BORDER, "L");
    }

    @Override
    public void visit(SkinCoin coin) {
        // Special drawing if needed, otherwise fallback or empty
        drawCircleEntity(currentG2d, currentX, currentY, COIN_SIZE, Color.MAGENTA, COLOR_BORDER, "S");
    }

    @Override
    public void visit(SolidWall wall) {
        // Walls are usually rendered by TileMapRenderer, but if an entity wall is here:
    }

    @Override
    public void visit(SpecialElement element) {
        // Generic special element
    }

    @Override
    public void visit(domain.level.Zone zone) {
        // Zones are typically invisible logically-triggering regions
    }

    private void drawSquareEntity(Graphics2D g2d, int x, int y, int size, Color fill, Color border, String label) {
        g2d.setColor(fill);
        g2d.fillRect(x, y, size, size);
        g2d.setColor(border);
        g2d.drawRect(x, y, size, size);
        if (!label.isEmpty()) {
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 11));
            g2d.drawString(label, x + size / 2 - 4, y - 3);
        }
    }

    private void drawCircleEntity(Graphics2D g2d, int x, int y, int size, Color fill, Color border, String label) {
        g2d.setColor(fill);
        g2d.fillOval(x, y, size, size);
        g2d.setColor(border);
        g2d.drawOval(x, y, size, size);

        if (label != null) {
            FontMetrics fm = g2d.getFontMetrics();
            int strX = x + (size - fm.stringWidth(label)) / 2;
            int strY = y - 5;
            g2d.drawString(label, strX, strY);
        }
    }
}
