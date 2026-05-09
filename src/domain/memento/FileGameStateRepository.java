package domain.memento;

import domain.exception.DhgDomainException;
import java.io.*;

public class FileGameStateRepository implements GameStateRepository {

    @Override
    public void saveGame(CheckpointCaretaker caretaker, String path) throws DhgDomainException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(caretaker);
        } catch (IOException e) {
            throw new DhgDomainException("Failed to save: " + e.getMessage());
        }
    }

    @Override
    public CheckpointCaretaker loadGame(String path) throws DhgDomainException {
        File f = new File(path);
        if (!f.exists()) {
            throw new DhgDomainException("Save file not found: " + path);
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (CheckpointCaretaker) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DhgDomainException("Failed to load: " + e.getMessage());
        }
    }
}