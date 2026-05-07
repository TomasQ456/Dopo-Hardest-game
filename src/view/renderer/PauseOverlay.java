package view.renderer;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class PauseOverlay {

    private static final Color COLOR_OVERLAY = new Color(0, 0, 0, 150);
    private static final Color COLOR_TEXT    = Color.WHITE;
    private static final int   FONT_SIZE     = 48;

    public PauseOverlay() {
    }

    public void render(Graphics2D g2d, int width, int height) {
        g2d.setColor(COLOR_OVERLAY);
        g2d.fillRect(0, 0, width, height);

        g2d.setColor(COLOR_TEXT);
        g2d.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        String text = "PAUSED";
        FontMetrics fm = g2d.getFontMetrics();
        int x = (width - fm.stringWidth(text)) / 2;
        int y = height / 2;
        g2d.drawString(text, x, y);

        g2d.setFont(new Font("Arial", Font.PLAIN, 16));
        String hint = "Press ESC or Game > Unpause to continue";
        fm = g2d.getFontMetrics();
        g2d.drawString(hint, (width - fm.stringWidth(hint)) / 2, y + 40);
    }
}

