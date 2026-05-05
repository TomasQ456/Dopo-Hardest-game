package domain.mode;
import domain.level.Level;
import domain.entity.Player;
import domain.GameModel;
import domain.exception.DhgDomainException;
import java.util.List;

/** Specific ruleset implementation. */
public class PvPMode implements GameMode {
    @Override
    public boolean isLevelComplete(Level level) throws DhgDomainException {
        List<Player> players = level.getPlayers();
        int activePlayers = 0;
        for (Player player : players) {
            if (player.isActive()) {
                activePlayers++;
            }
        }
        // Level complete when only one player remains active
        return activePlayers <= 1;
    }

    @Override
    public void resolvePlayerCollision(Player a, Player b) throws DhgDomainException {
        // In PvP, collision damages both players
        if (!a.absorbHit()) {
            a.registerDeath();
        }
        if (!b.absorbHit()) {
            b.registerDeath();
        }
    }

    @Override
    public void resolvePlayerDeath(Player player, GameModel model) throws DhgDomainException {
        // Player is already dead, no respawn in PvP
    }
}
