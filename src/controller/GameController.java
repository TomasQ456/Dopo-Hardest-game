package controller;

import view.GameWindow;
import view.MainMenuPanel;
import view.LevelSelectPanel;
import view.GamePanel;

import domain.GameModel;
import domain.entity.Entity;
import domain.entity.Player;
import domain.exception.DhgDomainException;
import domain.input.HumanController;
import domain.level.Level;
import domain.level.TestLevelFactory;
import domain.math.Direction;
import domain.mode.SinglePlayerMode;

import java.util.List;

public class GameController {

    private final GameWindow gameWindow;
    private javax.swing.Timer gameLoopTimer;
    private KeyboardInputHandler keyboardHandler;
    private boolean isPaused = false;

    private GameModel gameModel;
    private int selectedLevelIndex = 0;

    private static final int GAME_LOOP_MS    = 16;
    private static final double DELTA_SECONDS = 0.016;

    public GameController(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        try {
            initGameModel();
        } catch (DhgDomainException e) {
            e.printStackTrace();
        }
        wireMainMenu();
        wireLevelSelect();
        wireGameScreen();
    }

    private void initGameModel() throws DhgDomainException {
        gameModel = new GameModel();
        gameModel.addObserver(gameWindow.getGamePanel());
    }

    private void wireMainMenu() {
        gameWindow.getMainMenuPanel().setListener(new MainMenuPanel.MainMenuListener() {
            @Override
            public void onPlay() {
                gameWindow.showLevelSelect();
            }

            @Override
            public void onImport() {
                // stub
            }

            @Override
            public void onExport() {
                // stub
            }

            @Override
            public void onSave() {
                // stub
            }

            @Override
            public void onLoad() {
                // stub
            }
        });
    }

    private void wireLevelSelect() {
        gameWindow.getLevelSelectPanel().setListener(new LevelSelectPanel.LevelSelectListener() {
            @Override
            public void onLevelSelected(int levelIndex) {
                gameWindow.getLevelSelectPanel().highlightLevel(levelIndex);
            }

            @Override
            public void onPlaySelected() {
                try {
                    Level testLevel = TestLevelFactory.build();
                    gameModel.setGameMode(new SinglePlayerMode());
                    // Manually set the level on LevelManager stub
                    // until LevelParser is complete:
                    gameWindow.getGamePanel().setLevel(testLevel);
                    gameWindow.showGame();
                    startGameLoop();
                } catch (DhgDomainException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onGameModeChanged(String modeName) {
                // stub
            }

            @Override
            public void onSkinChanged(int playerIndex, String skinName) {
                // stub
            }
        });
    }

    private void wireGameScreen() {
        KeyboardInputHandler keyboardHandler = new KeyboardInputHandler();
        gameWindow.getGamePanel().addKeyListener(keyboardHandler);
        this.keyboardHandler = keyboardHandler;

        gameWindow.getGamePanel().setListener(new GamePanel.GameScreenListener() {
            @Override
            public void onPauseToggle() {
                isPaused = !isPaused;
                if (isPaused) {
                    gameLoopTimer.stop();
                } else {
                    gameLoopTimer.start();
                    gameWindow.getGamePanel().requestFocusInWindow();
                }
                gameWindow.getGamePanel().setPaused(isPaused);
            }

            @Override
            public void onReset() {
                stopGameLoop();
                gameWindow.getGamePanel().resetGameOver();
                isPaused = false;
                gameWindow.getGamePanel().setPaused(false);
                // TODO: call gameModel.startLevel(selectedLevel) when domain is wired
                startGameLoop();
            }

            @Override
            public void onExitToMenu() {
                stopGameLoop();
                isPaused = false;
                gameWindow.getGamePanel().resetGameOver();
                gameWindow.getGamePanel().setPaused(false);
                gameWindow.showLevelSelectFromGame();
            }
        });
    }

    private void startGameLoop() {
        if (gameLoopTimer != null && gameLoopTimer.isRunning()) return;
        gameLoopTimer = new javax.swing.Timer(GAME_LOOP_MS, e -> {
            try {
                // 1. Feed player 1 direction to domain
                Direction d1 = keyboardHandler.getPlayer1Direction();
                gameModel.processKeyboardInput(d1);

                // 2. Tick the domain model
                gameModel.update(DELTA_SECONDS);

                // 3. Push updated level to panel
                Level updated = gameModel.getCurrentLevel();
                gameWindow.getGamePanel().setLevel(updated);

                // 4. Update HUD data from level
                if (updated != null) {
                    double remaining = updated.getTimeController().getRemainingSeconds();
                    gameWindow.getGamePanel().setRemainingSeconds(remaining);
                }

                gameWindow.getGamePanel().repaint();
            } catch (DhgDomainException ex) {
                ex.printStackTrace();
            }
        });
        gameLoopTimer.start();
    }

    private void stopGameLoop() {
        if (gameLoopTimer != null) gameLoopTimer.stop();
    }
}

