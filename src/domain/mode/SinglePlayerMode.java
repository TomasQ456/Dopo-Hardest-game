package domain.mode;
import domain.level.Level;
import domain.entity.Player;
import domain.entity.YellowCoin;
import domain.entity.Coin;
import domain.entity.Entity;
import domain.level.GoalZone;
import domain.GameModel;
import domain.state.GameOverState;
import domain.exception.DhgDomainException;
import java.util.List;

/** Specific ruleset implementation. */
public class SinglePlayerMode implements GameMode {
    @Override
    public boolean isLevelComplete(Level level) throws DhgDomainException {
        List<Player> players = level.getPlayers();
        if (players.isEmpty()) {
            return false;
        }

        Player player = players.get(0);
        if (!player.isActive()) {
            return false;
        }

        // Check if all coins are collected
        boolean allCoinsCollected = true;
        // This is a simplified check - would need actual coin tracking

        // Check if player is in goal zone (simplified)
        return allCoinsCollected; // Would also check player position in GoalZone
    }

    @Override
    public void resolvePlayerCollision(Player a, Player b) throws DhgDomainException {
        // SinglePlayer mode has no player-vs-player collisions
    }

    @Override
    public void resolvePlayerDeath(Player player, GameModel model) throws DhgDomainException {
        // Try to respawn at checkpoint
        if (model != null) {
            // In a real implementation, would get checkpoint from level
            // For now, just trigger game over
            model.changeState(new GameOverState());
        }
    }
}
