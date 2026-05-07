package view.renderer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class HudRenderer {

    private static final Color COLOR_HUD_BG   = new Color(20, 20, 20);
    private static final Color COLOR_HUD_TEXT = Color.WHITE;
    private static final Color COLOR_SHIELD   = new Color(100, 180, 255);
    private static final int   FONT_SIZE      = 14;
    private static final int   PADDING        = 8;
    private final int hudHeight;

    public HudRenderer(int hudHeight) {
        this.hudHeight = hudHeight;
    }

    public void render(Graphics2D g2d, int panelWidth, int panelHeight,
                       int deaths, int score, int shieldHits, double remainingSeconds) {

        int hudY = panelHeight - hudHeight;
        g2d.setColor(COLOR_HUD_BG);
        g2d.fillRect(0, hudY, panelWidth, hudHeight);

        g2d.setFont(new Font("Monospaced", Font.BOLD, FONT_SIZE));
        g2d.setColor(COLOR_HUD_TEXT);

        int minutes = (int)(remainingSeconds / 60);
        int seconds = (int)(remainingSeconds % 60);
        String timeStr   = String.format("TIME  %02d:%02d", minutes, seconds);
        String deathStr  = String.format("DEATHS  %d", deaths);
        String scoreStr  = String.format("SCORE  %d", score);
        String shieldStr = String.format("SHIELD  %d", shieldHits);

        int textY = hudY + hudHeight / 2 + FONT_SIZE / 2;

        g2d.drawString(timeStr,   PADDING, textY);
        g2d.drawString(deathStr,  panelWidth / 4, textY);
        g2d.drawString(scoreStr,  panelWidth / 2, textY);
        g2d.drawString(shieldStr, 3 * panelWidth / 4, textY);
    }
}

