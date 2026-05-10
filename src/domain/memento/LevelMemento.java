package domain.memento;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/** Data transfer object representing a snapshot of a Level's progress. */
public class LevelMemento implements Serializable {
    private List<PlayerMemento> playerMementos;
    private Set<String> collectedCoinIds;
    private double remainingSeconds;
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a LevelMemento with all level state fields.
     */
    public LevelMemento(List<PlayerMemento> playerMementos, Set<String> collectedCoinIds, double remainingSeconds) {
        this.playerMementos = playerMementos;
        this.collectedCoinIds = collectedCoinIds;
        this.remainingSeconds = remainingSeconds;
    }

    public List<PlayerMemento> getPlayerMementos() { return this.playerMementos; }
    public Set<String> getCollectedCoinIds() { return this.collectedCoinIds; }
    public double getRemainingSeconds() { return this.remainingSeconds; }
}
