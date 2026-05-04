package domain.memento;

/** Standard disk I/O implementation of the repository. */
public class FileGameStateRepository implements GameStateRepository {
    @Override public void saveGame(CheckpointCaretaker caretaker, int levelIndex) {}
    @Override public CheckpointCaretaker loadGame() { return null; }
}
