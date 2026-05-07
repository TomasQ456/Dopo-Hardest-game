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

public class EntityRenderer {

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

    public EntityRenderer(int tileSize) {
        this.tileSize = tileSize;
    }

    public void render(Graphics2D g2d) {
    }

    public void renderLevel(Graphics2D g2d, Level level) throws DhgDomainException {
        List<Entity> entities = level.getEntities();
        for (Entity entity : entities) {
            if (!entity.isActive()) continue;
            Vector2 pos = entity.getPosition();
            int x = (int) pos.x;
            int y = (int) pos.y;

            if (entity instanceof Player) {
                Player p = (Player) entity;
                Color fill = COLOR_PLAYER1;
                drawSquareEntity(g2d, x, y, ENTITY_SIZE, fill, COLOR_BORDER, "P");
            }
            else if (entity instanceof Enemy) {
                drawCircleEntity(g2d, x, y, ENTITY_SIZE, COLOR_ENEMY, COLOR_BORDER, "E");
            }
            else if (entity instanceof BonusCoin) {
                drawCircleEntity(g2d, x, y, COIN_SIZE, COLOR_BONUS_COIN, COLOR_BORDER, "");
            }
            else if (entity instanceof YellowCoin) {
                drawCircleEntity(g2d, x, y, COIN_SIZE, COLOR_COIN, COLOR_BORDER, "");
            }
            else if (entity instanceof Bomb) {
                drawCircleEntity(g2d, x, y, ENTITY_SIZE, COLOR_BOMB, COLOR_BORDER, "B");
            }
            else if (entity instanceof LifeSource) {
                drawCircleEntity(g2d, x, y, ENTITY_SIZE, COLOR_LIFE, COLOR_BORDER, "L");
            }
        }
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
