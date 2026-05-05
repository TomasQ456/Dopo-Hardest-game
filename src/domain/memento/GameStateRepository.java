package domain.memento;

import domain.exception.DhgDomainException;

/**
 * Interface defining persistence operations for game saves.
 */
public interface GameStateRepository {

    /**
     * Persists the current Caretaker to storage.
     * @param caretaker The caretaker holding the memento.
     * @param levelIndex The current level index being saved.
     */
    void saveGame(CheckpointCaretaker caretaker, int levelIndex) throws DhgDomainException;

    /**
     * Loads a previously saved Caretaker from storage.
     * @return The populated caretaker.
     */
    CheckpointCaretaker loadGame() throws DhgDomainException;
}
