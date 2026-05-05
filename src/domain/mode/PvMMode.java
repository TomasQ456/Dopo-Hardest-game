package domain.mode;
import domain.level.Level;
import domain.entity.Player;
import domain.GameModel;
import domain.exception.DhgDomainException;
import java.util.List;

/** Specific ruleset implementation. */
public class PvMMode implements GameMode {
    @Override
    public boolean isLevelComplete(Level level) throws DhgDomainException {
        List<Player> players = level.getPlayers();
        if (players.isEmpty()) {
            return false;
        }

        // Level complete when human player reaches goal with all coins collected
        // Simplified: just check if first player is active
        return players.get(0).isActive();
    }

    @Override
    public void resolvePlayerCollision(Player a, Player b) throws DhgDomainException {
        // In PvM, players push each other but don't damage
        // No damage inflicted
    }

    @Override
    public void resolvePlayerDeath(Player player, GameModel model) throws DhgDomainException {
        // Handle player death (respawn or game over logic)
        if (model != null) {
            // Would respawn at checkpoint or trigger game over
        }
    }
}
