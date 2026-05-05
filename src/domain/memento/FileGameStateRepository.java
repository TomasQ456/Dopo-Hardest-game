package domain.memento;

import domain.exception.DhgDomainException;
import java.io.*;

/** Standard disk I/O implementation of the repository. */
public class FileGameStateRepository implements GameStateRepository {
    private static final String SAVE_DIR = "./saves/";

    @Override
    public void saveGame(CheckpointCaretaker caretaker, int levelIndex) throws DhgDomainException {
        try {
            new File(SAVE_DIR).mkdirs();
            String filename = SAVE_DIR + "level_" + levelIndex + ".sav";
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(caretaker);
            oos.close();
            fos.close();
        } catch (IOException e) {
            throw new DhgDomainException("Failed to save game: " + e.getMessage());
        }
    }

    @Override
    public CheckpointCaretaker loadGame() throws DhgDomainException {
        try {
            String filename = SAVE_DIR + "level_0.sav";
            File saveFile = new File(filename);
            if (!saveFile.exists()) {
                return null;
            }
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            CheckpointCaretaker caretaker = (CheckpointCaretaker) ois.readObject();
            ois.close();
            fis.close();
            return caretaker;
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
