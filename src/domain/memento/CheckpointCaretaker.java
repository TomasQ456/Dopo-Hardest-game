package domain.memento;

import java.io.Serializable;
import domain.level.Level;

/**
 * Manages the current saved snapshot (Memento) of the game.
 */
public class CheckpointCaretaker implements Serializable {
    
    private LevelMemento currentMemento;

    /**
     * Creates a new Memento snapshot from the live Level and stores it.
     * @param level The level to snapshot.
     */
    public void save(Level level) {}

    /**
     * Overwrites the live Level state with the data from the stored Memento.
     * @param level The level to restore into.
     */
    public void restore(Level level) {}

    /**
     * Checks if a snapshot exists.
     * @return true if a save state is available.
     */
    public boolean hasMemento() { return false; }
}
