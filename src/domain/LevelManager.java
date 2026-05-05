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
    }

    /**
     * Retrieves the currently active level.
     * @return The active Level instance.
     */
    public Level getCurrentLevel() throws DhgDomainException {
        if (this.levels.isEmpty() || this.currentIndex >= this.levels.size()) {
            return null;
        }
        return this.levels.get(this.currentIndex);
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
        return this.levels.get(index);
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
            return this.levels.get(this.currentIndex);
        }
        return null;
    }
}
