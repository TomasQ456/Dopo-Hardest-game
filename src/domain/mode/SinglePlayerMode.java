package domain.mode;
import domain.level.Level;
import domain.level.StartZone;
import domain.level.GoalZone;
import domain.level.Checkpoint;
import domain.entity.Player;
import domain.entity.YellowCoin;
import domain.entity.SkinCoin;
import domain.entity.Coin;
import domain.entity.Entity;
import domain.GameModel;
import domain.core.ModelEvent;
import domain.core.ModelEventType;
import domain.exception.DhgDomainException;
import domain.math.Vector2;
import java.util.List;

/** Specific ruleset implementation. */
public class SinglePlayerMode implements GameMode {
    @Override
    public boolean isLevelComplete(Level level) throws DhgDomainException {
        if (level == null) return false;
        try {
            // All YellowCoins and SkinCoins must be collected
            boolean allCoins = level.getEntities().stream()
                .filter(e -> e instanceof YellowCoin || e instanceof SkinCoin)
                .allMatch(e -> {
                    try { return ((Coin)e).isCollected(); }
                    catch (Exception ex) { return false; }
                });
            if (!allCoins) return false;

            // At least one player must be inside GoalZone
            for (Entity e : level.getEntities()) {
                if (!(e instanceof GoalZone)) continue;
                GoalZone goal = (GoalZone) e;
                for (Player p : level.getPlayers()) {
                    if (goal.contains(p.getPosition())) return true;
                }
            }
            return false;
        } catch (DhgDomainException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void resolvePlayerCollision(Player a, Player b) throws DhgDomainException {
        // SinglePlayer mode has no player-vs-player collisions
    }

    @Override
    public void resolvePlayerDeath(Player player, GameModel model)
        throws DhgDomainException {
        if (player == null || model == null) return;
        try {
            Level level = model.getCurrentLevel();

            // Find respawn point: active checkpoint or start zone
            Checkpoint cp = level.getActiveCheckpoint();
            Vector2 respawn = null;
            if (cp != null) {
                respawn = cp.getRespawnPosition();
            } else {
                // Find StartZone in entities list
                for (Entity e : level.getEntities()) {
                    if (e instanceof StartZone) {
                        respawn = ((StartZone) e).getSpawnPosition();
                        break;
                    }
                }
            }
            if (respawn == null)
                throw new DhgDomainException(DhgDomainException.ERR_NO_SPAWN_FOUND);

            player.registerDeath();
            player.respawn(respawn);
            model.notifyObservers(new ModelEvent(ModelEventType.PLAYER_DIED));

        } catch (DhgDomainException e) {
            e.printStackTrace();
        }
    }
}
