package domain.memento;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/** Data transfer object representing a snapshot of a Level's progress. */
public class LevelMemento implements Serializable {
    private List<PlayerMemento> playerMementos;
    private Set<String> collectedCoinIds;
    private double remainingSeconds;

    public List<PlayerMemento> getPlayerMementos() { return null; }
    public Set<String> getCollectedCoinIds() { return null; }
    public double getRemainingSeconds() { return 0.0; }
}
