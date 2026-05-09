package domain.memento;

import domain.exception.DhgDomainException;

/**
 * Interface defining persistence operations for game saves.
 */
public interface GameStateRepository {
    void saveGame(CheckpointCaretaker caretaker, String path) throws DhgDomainException;
    CheckpointCaretaker loadGame(String path) throws DhgDomainException;
}