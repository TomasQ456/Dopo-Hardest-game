package domain;

import domain.level.Level;
import java.util.List;

/**
 * Manages the sequence and loading of levels throughout a game session.
 */
public class LevelManager {

    private List<Level> levels;
    private int currentIndex;
    private LevelParser levelParser;

    /**
     * Initializes the manager with a predefined list of level file paths.
     * @param levelPaths List of paths to load.
     */
    public void loadAll(List<String> levelPaths) {}

    /**
     * Retrieves the currently active level.
     * @return The active Level instance.
     */
    public Level getCurrentLevel() { return null; }

    /**
     * Commands the LevelParser to load a specific level index into memory.
     * @param index The target index.
     * @return The newly loaded Level.
     */
    public Level loadLevel(int index) { return null; }

    /**
     * Checks if there are more levels remaining after the current one.
     * @return true if there is a next level.
     */
    public boolean hasNextLevel() { return false; }
}
