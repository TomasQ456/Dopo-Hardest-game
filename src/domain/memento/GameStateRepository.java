package domain.memento;

/**
 * Interface defining persistence operations for game saves.
 */
public interface GameStateRepository {

    /**
     * Persists the current Caretaker to storage.
     * @param caretaker The caretaker holding the memento.
     * @param levelIndex The current level index being saved.
     */
    void saveGame(CheckpointCaretaker caretaker, int levelIndex);

    /**
     * Loads a previously saved Caretaker from storage.
     * @return The populated caretaker.
     */
    CheckpointCaretaker loadGame();
}
