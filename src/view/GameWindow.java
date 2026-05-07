package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class GameWindow extends JFrame {

    public static final String CARD_MAIN_MENU = "MAIN_MENU";
    public static final String CARD_LEVEL_SELECT = "LEVEL_SELECT";
    public static final String CARD_GAME = "GAME";

    private static final int WINDOW_WIDTH = 900;
    private static final int WINDOW_HEIGHT = 600;

    private final CardLayout cardLayout;
    private final JPanel rootPanel;
    private final MainMenuPanel mainMenuPanel;
    private final LevelSelectPanel levelSelectPanel;
    private GamePanel gamePanel;

    public GameWindow() {
        setTitle("The DOPO Hardest Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        rootPanel = new JPanel(cardLayout);

        mainMenuPanel = new MainMenuPanel();
        levelSelectPanel = new LevelSelectPanel();

        rootPanel.add(mainMenuPanel, CARD_MAIN_MENU);
        rootPanel.add(levelSelectPanel, CARD_LEVEL_SELECT);

        gamePanel = new GamePanel();
        rootPanel.add(gamePanel, CARD_GAME);

        setContentPane(rootPanel);
        setJMenuBar(mainMenuPanel.buildMenuBar());

        showMainMenu();
    }

    public void showMainMenu() {
        cardLayout.show(rootPanel, CARD_MAIN_MENU);
    }

    public void showLevelSelect() {
        cardLayout.show(rootPanel, CARD_LEVEL_SELECT);
    }

    public void showGame() {
        setJMenuBar(gamePanel.buildGameMenuBar());
        cardLayout.show(rootPanel, CARD_GAME);
        gamePanel.requestFocusInWindow();
    }

    public void showLevelSelectFromGame() {
        setJMenuBar(mainMenuPanel.buildMenuBar());
        cardLayout.show(rootPanel, CARD_LEVEL_SELECT);
    }

    public MainMenuPanel getMainMenuPanel() {
        return mainMenuPanel;
    }

    public LevelSelectPanel getLevelSelectPanel() {
        return levelSelectPanel;
    }

    public GamePanel getGamePanel() { return gamePanel; }
}
