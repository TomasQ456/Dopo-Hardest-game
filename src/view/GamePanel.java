package view;

import domain.core.GameModelObserver;
import domain.core.ModelEvent;
import domain.exception.DhgDomainException;
import view.renderer.EntityRenderer;
import view.renderer.HudRenderer;
import view.renderer.PauseOverlay;
import view.renderer.TileMapRenderer;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import domain.level.Level;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements GameModelObserver {

    private static final Color COLOR_BACKGROUND    = new Color(173, 216, 230);
    private static final Color COLOR_PAUSE_OVERLAY = new Color(0, 0, 0, 120);
    private static final Color COLOR_HUD_BG        = new Color(0, 0, 0, 180);
    private static final Color COLOR_HUD_TEXT      = Color.WHITE;

    private static final int TILE_SIZE  = 40;
    private static final int HUD_HEIGHT = 40;

    private final TileMapRenderer tileMapRenderer;
    private final EntityRenderer entityRenderer;
    private final HudRenderer hudRenderer;
    private final PauseOverlay pauseOverlay;
    private boolean paused = false;

    private int deaths = 0;
    private int score  = 0;
    private int shieldHits = 0;
    private double remainingSeconds = 0.0;
    private boolean gameOver = false;
    private String gameOverMessage = "";

    private GameScreenListener listener;
    private JMenuItem pauseMenuItem;

    private Level currentLevel;

    public interface GameScreenListener {
        void onPauseToggle();
        void onReset();
        void onExitToMenu();
    }

    public GamePanel() {
        setBackground(COLOR_BACKGROUND);
        setFocusable(true);
        tileMapRenderer = new TileMapRenderer(TILE_SIZE);
        entityRenderer  = new EntityRenderer(TILE_SIZE);
        hudRenderer     = new HudRenderer(HUD_HEIGHT);
        pauseOverlay    = new PauseOverlay();
    }

    public void setLevel(Level level) {
        this.currentLevel = level;
        repaint();
    }

    public void setRemainingSeconds(double s) {
        this.remainingSeconds = s;
    }

    public void setDeaths(int d) { this.deaths = d; }
    public void setScore(int s) { this.score = s; }
    public void setShield(int sh) { this.shieldHits = sh; }

    public JMenuBar buildGameMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");

        pauseMenuItem = new JMenuItem("Pause");
        pauseMenuItem.addActionListener(e -> {
            if (listener != null) listener.onPauseToggle();
        });
        pauseMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));

        JMenuItem resetMenuItem = new JMenuItem("Reset");
        resetMenuItem.addActionListener(e -> {
            if (listener != null) listener.onReset();
        });

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> {
            if (listener != null) listener.onExitToMenu();
        });

        gameMenu.add(pauseMenuItem);
        gameMenu.add(resetMenuItem);
        gameMenu.add(new JSeparator());
        gameMenu.add(exitMenuItem);
        menuBar.add(gameMenu);

        return menuBar;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
        if (pauseMenuItem != null) {
            pauseMenuItem.setText(paused ? "Unpause" : "Pause");
        }
        repaint();
    }

    public void showGameOver(String message) {
        this.gameOver = true;
        this.gameOverMessage = message;
        repaint();
    }

    public void resetGameOver() {
        this.gameOver = false;
        this.gameOverMessage = "";
        repaint();
    }

    public void setListener(GameScreenListener listener) {
        this.listener = listener;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int gameHeight = getHeight() - HUD_HEIGHT;

        try {
            if (currentLevel != null) {
                // 1. Render real tile map
                tileMapRenderer.render(g2d, currentLevel.getTileMap(), getWidth(), gameHeight);

                // 2. Render real entities
                entityRenderer.renderLevel(g2d, currentLevel);
            } else {
                // Fallback: plain background until level is loaded
                tileMapRenderer.render(g2d, getWidth(), gameHeight);
            }

            // 3. Render HUD
            hudRenderer.render(g2d, getWidth(), getHeight(), deaths, score, shieldHits, remainingSeconds);

            // 4. Pause overlay
            if (paused) pauseOverlay.render(g2d, getWidth(), getHeight());

            // 5. Game over overlay
            if (gameOver) renderGameOverOverlay(g2d);

        } catch (DhgDomainException e) {
            // Render error state so the game does not silently break
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("Arial", Font.BOLD, 16));
            g2d.drawString("RENDER ERROR: " + e.getMessage(), 20, 50);
        }
    }

    private void renderGameOverOverlay(Graphics2D g2d) {
        g2d.setColor(COLOR_PAUSE_OVERLAY);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 32));
        FontMetrics fm = g2d.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(gameOverMessage)) / 2;
        int y = getHeight() / 2;
        g2d.drawString(gameOverMessage, x, y);
    }

    @Override
    public void onModelChanged(ModelEvent event) {
        try {
            switch (event.getType()) {
                case PLAYER_DIED:
                    deaths++;
                    repaint();
                    break;
                case COIN_COLLECTED:
                    score += 10;
                    repaint();
                    break;
                case TIME_UP:
                    showGameOver("TIME UP!");
                    break;
                case LEVEL_COMPLETED:
                    showGameOver("LEVEL COMPLETE!");
                    break;
                case CHECKPOINT_REACHED:
                    repaint();
                    break;
                default:
                    // do nothing
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
