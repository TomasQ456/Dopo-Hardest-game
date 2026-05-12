package domain;

import domain.level.Level;
import java.util.List;
import java.util.ArrayList;
import domain.exception.DhgDomainException;

/**
 * Manages the sequence and loading of levels throughout a game session.
 */
public class LevelManager {

    private List<Level> levels;
    private int currentIndex;
    private LevelParser levelParser;
    private Level currentLevel;

    /**
     * Constructs a LevelManager with a new parser and empty levels list.
     */
    public LevelManager() {
        this.levelParser = new LevelParser();
        this.levels = new ArrayList<>();
        this.currentIndex = 0;
    }

    /**
     * Initializes the manager with a predefined list of level file paths.
     * @param levelPaths List of paths to load.
     */
    public void loadAll(List<String> levelPaths) throws DhgDomainException {
        if (levelPaths == null || levelPaths.isEmpty()) {
            throw new DhgDomainException("Level paths list must not be null or empty");
        }

        this.levels.clear();
        for (String path : levelPaths) {
            Level level = this.levelParser.parse(path);
            if (level != null) {
                this.levels.add(level);
            }
        }

        this.currentIndex = 0;
        this.currentLevel = this.levels.isEmpty() ? null : this.levels.get(0);
    }

    /**
     * Retrieves the currently active level.
     * @return The active Level instance.
     */
    public Level getCurrentLevel() throws DhgDomainException {
        if (this.currentLevel != null) {
            return this.currentLevel;
        }
        if (this.levels.isEmpty() || this.currentIndex >= this.levels.size()) {
            return null;
        }
        this.currentLevel = this.levels.get(this.currentIndex);
        return this.currentLevel;
    }

    /**
     * Commands the LevelParser to load a specific level index into memory.
     * @param index The target index.
     * @return The newly loaded Level.
     */
    public Level loadLevel(int index) throws DhgDomainException {
        if (index < 0 || index >= this.levels.size()) {
            throw new DhgDomainException("Level index out of bounds: " + index);
        }
        this.currentIndex = index;
        this.currentLevel = this.levels.get(index);
        return this.currentLevel;
    }

    /**
     * Checks if there are more levels remaining after the current one.
     * @return true if there is a next level.
     */
    public boolean hasNextLevel() throws DhgDomainException {
        return (this.currentIndex + 1) < this.levels.size();
    }

    /**
     * Advances to the next level.
     * @return The next level, or null if none exists.
     */
    public Level nextLevel() throws DhgDomainException {
        if (hasNextLevel()) {
            this.currentIndex++;
            this.currentLevel = this.levels.get(this.currentIndex);
            return this.currentLevel;
        }
        return null;
    }

    /**
     * Registers a single active level directly, bypassing the preloaded level list.
     * Useful for test levels and ad-hoc loaded content.
     * @param level The active level to use.
     */
    public void setCurrentLevel(Level level) throws DhgDomainException {
        this.currentLevel = level;
    }
}
