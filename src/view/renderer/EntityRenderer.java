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
import domain.entity.SkinCoin;
import domain.entity.BonusCoin;
import domain.entity.Bomb;
import domain.entity.LifeSource;
import domain.exception.DhgDomainException;
import domain.level.Level;
import domain.level.StartZone;
import domain.level.GoalZone;
import domain.level.Checkpoint;
import domain.math.Vector2;
import domain.skin.SkinBehavior;

public class EntityRenderer {

    private static final Color COLOR_PLAYER1     = new Color(220, 50, 50);
    private static final Color COLOR_PLAYER2     = new Color(50, 50, 220);
    private static final Color COLOR_ENEMY       = new Color(30, 144, 255);
    private static final Color COLOR_COIN_YELL   = new Color(255, 215, 0);
    private static final Color COLOR_COIN_BONUS  = new Color(255, 140, 0);
    private static final Color COLOR_BOMB        = new Color(40, 40, 40);
    private static final Color COLOR_LIFE        = new Color(50, 220, 50);
    private static final Color COLOR_BORDER      = Color.BLACK;
    private static final Color COLOR_START_ZONE  = new Color(100, 255, 100);
    private static final Color COLOR_GOAL_ZONE   = new Color(255, 100, 100);
    private static final Color COLOR_CHECKPOINT  = new Color(100, 100, 255);
    private static final int   ENEMY_SIZE        = 30;
    private static final int   COIN_SIZE         = 16;
    private final int tileSize;

    public EntityRenderer(int tileSize) {
        this.tileSize = tileSize;
    }

    public void render(Graphics2D g2d) {
    }

    public void renderLevel(Graphics2D g2d, Level level) throws DhgDomainException {
        // 1. Render zones first (from entities)
        for (Entity entity : level.getEntities()) {
            if (entity instanceof GoalZone)
                renderZone(g2d, entity, COLOR_GOAL_ZONE);
            else if (entity instanceof Checkpoint)
                renderZone(g2d, entity, COLOR_CHECKPOINT);
            else if (entity instanceof StartZone)
                renderZone(g2d, entity, COLOR_START_ZONE);
        }

        // 2. Render non-player entities
        for (Entity entity : level.getEntities()) {
            try {
                if (!entity.isActive()) continue;
                Vector2 pos = entity.getPosition();
                int x = (int) pos.x;
                int y = (int) pos.y;
                renderNonPlayerEntity(g2d, entity, x, y);
            } catch (DhgDomainException e) { e.printStackTrace(); }
        }

        // 3. Render players (separate list per diagram)
        for (Player player : level.getPlayers()) {
            try {
                if (!player.isActive()) continue;
                Vector2 pos = player.getPosition();
                renderPlayer(g2d, player, (int)pos.x, (int)pos.y);
            } catch (DhgDomainException e) { e.printStackTrace(); }
        }
    }

    private void renderZone(Graphics2D g2d, Entity zone, Color color) throws DhgDomainException {
        Vector2 pos = zone.getPosition();
        if (pos == null) return;
        int x = (int) pos.x;
        int y = (int) pos.y;
        g2d.setColor(color);
        g2d.setAlpha(0.3f);
        g2d.fillRect(x, y, 60, 60);
        g2d.setAlpha(1.0f);
    }

    private void renderPlayer(Graphics2D g2d, Player player, int x, int y)
        throws DhgDomainException {
        Color fill = COLOR_PLAYER1;
        drawSquareEntity(g2d, x, y, ENEMY_SIZE, fill, COLOR_BORDER, "P");
    }

    private void renderNonPlayerEntity(Graphics2D g2d, Entity entity, int x, int y) {
        if (entity instanceof Enemy)
            drawCircleEntity(g2d, x, y, ENEMY_SIZE, COLOR_ENEMY, COLOR_BORDER, "E");
        else if (entity instanceof SkinCoin)
            try {
                Color skinColor = getSkinCoinColor(((SkinCoin)entity).getGrantedSkin());
                drawCircleEntity(g2d, x, y, COIN_SIZE, skinColor, COLOR_BORDER, "");
            } catch (Exception e) { e.printStackTrace(); }
        else if (entity instanceof BonusCoin)
            drawCircleEntity(g2d, x, y, COIN_SIZE, COLOR_COIN_BONUS, COLOR_BORDER, "");
        else if (entity instanceof YellowCoin)
            drawCircleEntity(g2d, x, y, COIN_SIZE, COLOR_COIN_YELL, COLOR_BORDER, "");
        else if (entity instanceof Bomb)
            drawCircleEntity(g2d, x, y, ENEMY_SIZE, COLOR_BOMB, COLOR_BORDER, "B");
        else if (entity instanceof LifeSource)
            drawCircleEntity(g2d, x, y, ENEMY_SIZE, COLOR_LIFE, COLOR_BORDER, "+");
    }

    private Color getSkinCoinColor(SkinBehavior skin) throws DhgDomainException {
        return new Color(200, 100, 200); // Default skin coin color
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
