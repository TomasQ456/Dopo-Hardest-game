package domain.mode;
import domain.level.Level;
import domain.entity.Player;
import domain.GameModel;

/** Specific ruleset implementation. */
public class PvPMode implements GameMode {
    @Override public boolean isLevelComplete(Level level) { return false; }
    @Override public void resolvePlayerCollision(Player a, Player b) {}
    @Override public void resolvePlayerDeath(Player player, GameModel model) {}
}
