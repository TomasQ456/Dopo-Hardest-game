package domain.memento;

import java.io.Serializable;
import domain.level.Level;
import domain.entity.Player;
import domain.entity.Coin;
import domain.exception.DhgDomainException;
import java.util.*;

/**
 * Manages the current saved snapshot (Memento) of the game.
 */
public class CheckpointCaretaker implements Serializable {
    
    private LevelMemento currentMemento;

    /**
     * Creates a new Memento snapshot from the live Level and stores it.
     * @param level The level to snapshot.
     */
    public void save(Level level) throws DhgDomainException {
        List<PlayerMemento> playerMementos = new ArrayList<>();
        List<Player> players = level.getPlayers();

        for (Player player : players) {
            PlayerMemento pm = new PlayerMemento(
                player.getDeaths(),
                player.getScore(),
                player.getPosition(),
                null, // skin would need to be captured separately
                0     // shieldHits would need separate tracking
            );
            playerMementos.add(pm);
        }

        double remainingSeconds = level.getTimeController().getRemainingSeconds();
        Set<String> collectedCoinIds = new HashSet<>();

        this.currentMemento = new LevelMemento(playerMementos, collectedCoinIds, remainingSeconds);
    }

    /**
     * Overwrites the live Level state with the data from the stored Memento.
     * @param level The level to restore into.
     */
    public void restore(Level level) throws DhgDomainException {
        if (this.currentMemento == null) {
            return;
        }

        List<Player> players = level.getPlayers();
        List<PlayerMemento> playerMementos = this.currentMemento.getPlayerMementos();

        for (int i = 0; i < players.size() && i < playerMementos.size(); i++) {
            Player player = players.get(i);
            PlayerMemento pm = playerMementos.get(i);

            player.setPosition(pm.getPosition());
            // Other state restoration would go here
        }

        level.getTimeController().setLimit(this.currentMemento.getRemainingSeconds());
    }

    /**
     * Checks if a snapshot exists.
     * @return true if a save state is available.
     */
    public boolean hasMemento() throws DhgDomainException {
        return this.currentMemento != null;
    }
}
