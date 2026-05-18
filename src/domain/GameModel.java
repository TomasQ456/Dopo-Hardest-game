package domain;

import domain.core.GameModelSubject;
import domain.core.GameModelObserver;
import domain.core.ModelEvent;
import domain.core.ModelEventType;
import domain.level.Level;
import domain.mode.GameMode;
import domain.state.GamePhaseState;
import domain.state.PlayingState;
import domain.state.GameOverState;
import domain.exception.DhgDomainException;
import java.util.List;
import java.util.ArrayList;

/**
 * The primary Facade for the Model layer.
 * Coordinates the LevelManager, State Machine, Game Mode rules, and Observer events.
 */
public class GameModel implements GameModelSubject {

    private LevelManager levelManager;
    private GameLoopCoordinator loopCoordinator;
    private GameMode gameMode;
    private GamePhaseState currentState;
    private List<GameModelObserver> observers;

    /**
     * Constructs a GameModel with an empty observer list.
     */
    public GameModel() {
        this.observers = new ArrayList<>();
        this.levelManager = new LevelManager();
        this.loopCoordinator = new GameLoopCoordinator(0.016); // 60 FPS default
        this.currentState = new PlayingState();
    }

    /**
     * Central update method called by the LoopCoordinator.
     * Delegates updates to the active Level based on the current PhaseState.
     * @param deltaSeconds Fixed physics delta time.
     */
    public void update(double deltaSeconds) throws DhgDomainException {
        if (this.currentState != null) {
            this.currentState.update(this, deltaSeconds);
        }

        // Check for game conditions
        Level currentLevel = getCurrentLevel();
        if (currentLevel != null && this.gameMode != null) {
            // Check if time is up
            if (currentLevel.getTimeController().isTimeUp()) {
                ModelEvent timeUpEvent = new ModelEvent(ModelEventType.TIME_UP);
                notifyObservers(timeUpEvent);
            }

            // Check if level is complete
            if (this.gameMode.isLevelComplete(currentLevel)) {
                completeLevel();
            }
        }
    }

    /**
     * Instructs the LevelManager to load and initialize a specific level.
     * @param index The index of the level to start.
     */
    public void startLevel(int index) throws DhgDomainException {
        Level level = this.levelManager.loadLevel(index);
        if (level != null) {
            changeState(new PlayingState());
        }
    }

    /**
     * Triggered when win conditions are met. Transitions states or loads next level.
     */
    public void completeLevel() throws DhgDomainException {
        if (this.levelManager.hasNextLevel()) {
            this.levelManager.nextLevel();
            changeState(new PlayingState());

            // Notify observers of level completion
            ModelEvent event = new ModelEvent(ModelEventType.LEVEL_COMPLETED);
            notifyObservers(event);
        } else {
            // Game over - no more levels
            changeState(new GameOverState());
        }
    }

    /**
     * Transitions the main game state (e.g., Playing to Paused).
     * @param state The new state to enter.
     */
    public void changeState(GamePhaseState state) throws DhgDomainException {
        if (this.currentState != null) {
            this.currentState.onExit(this);
        }
        this.currentState = state;
        if (this.currentState != null) {
            this.currentState.onEnter(this);
        }
    }

    /**
     * Retrieves the currently active level instance.
     * @return The current Level.
     */
    public Level getCurrentLevel() throws DhgDomainException {
        return this.levelManager.getCurrentLevel();
    }

    /**
     * Retrieves the active game mode ruleset.
     * @return The GameMode strategy.
     */
    public GameMode getGameMode() {
        return this.gameMode;
    }

    /**
     * Sets the game mode.
     * @param gameMode The GameMode strategy to use.
     */
    public void setGameMode(GameMode gameMode) throws DhgDomainException {
        this.gameMode = gameMode;
    }

    /**
     * Sets the level manager.
     * @param levelManager The LevelManager to use.
     */
    public void setLevelManager(LevelManager levelManager) throws DhgDomainException {
        this.levelManager = levelManager;
    }

    @Override
    public void addObserver(GameModelObserver observer) throws DhgDomainException {
        if (observer == null) {
            throw new DhgDomainException(DhgDomainException.ERR_NULL_OBSERVER);
        }
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(GameModelObserver observer) throws DhgDomainException {
        if (observer == null) {
            throw new DhgDomainException(DhgDomainException.ERR_NULL_OBSERVER);
        }
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers(ModelEvent event) throws DhgDomainException {
        if (event == null) {
            throw new DhgDomainException(DhgDomainException.ERR_NULL_EVENT);
        }
        for (GameModelObserver observer : this.observers) {
            observer.onModelChanged(event);
        }
    }

    /**
     * Processes keyboard input by dispatching it to all player controllers.
     * @param direction The direction pressed.
     */
    public void processKeyboardInput(domain.math.Direction direction) throws DhgDomainException {
        Level level = getCurrentLevel();
        if (level != null) {
            for (domain.entity.Player player : level.getPlayers()) {
                if (player.getController() != null) {
                    player.getController().processKeyboardInput(player, direction);
                }
            }
        }
    }
}
