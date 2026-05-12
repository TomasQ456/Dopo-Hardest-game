package domain.memento;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/** Data transfer object representing a snapshot of a Level's progress. */
public class LevelMemento implements Serializable {
    private static final long serialVersionUID = 1L;

    private String levelId;
    private List<PlayerMemento> playerMementos;
    private Set<String> collectedCoinIds;
    private double remainingSeconds;
    private List<EnemyMemento> enemyStates;
    private List<CheckpointMementoData> checkpointStates;
    private List<SpecialElementMemento> specialElementStates;
    private List<CoinMemento> coinStates;

    /**
     * Constructs a LevelMemento with all level state fields.
     */
    public LevelMemento(List<PlayerMemento> playerMementos, Set<String> collectedCoinIds, double remainingSeconds) {
        this(null, playerMementos, collectedCoinIds, remainingSeconds, null, null, null, null);
    }

    public LevelMemento(String levelId, List<PlayerMemento> playerMementos, Set<String> collectedCoinIds,
                        double remainingSeconds, List<EnemyMemento> enemyStates,
                        List<CheckpointMementoData> checkpointStates,
                        List<SpecialElementMemento> specialElementStates,
                        List<CoinMemento> coinStates) {
        this.levelId = levelId;
        this.playerMementos = playerMementos;
        this.collectedCoinIds = collectedCoinIds;
        this.remainingSeconds = remainingSeconds;
        this.enemyStates = enemyStates;
        this.checkpointStates = checkpointStates;
        this.specialElementStates = specialElementStates;
        this.coinStates = coinStates;
    }

    public String getLevelId() { return this.levelId; }
    public List<PlayerMemento> getPlayerMementos() { return this.playerMementos; }
    public Set<String> getCollectedCoinIds() { return this.collectedCoinIds; }
    public double getRemainingSeconds() { return this.remainingSeconds; }
    public List<EnemyMemento> getEnemyStates() { return this.enemyStates; }
    public List<CheckpointMementoData> getCheckpointStates() { return this.checkpointStates; }
    public List<SpecialElementMemento> getSpecialElementStates() { return this.specialElementStates; }
    public List<CoinMemento> getCoinStates() { return this.coinStates; }
}
