package domain.mode;

import domain.level.Level;
import domain.entity.Player;
import domain.GameModel;
import domain.exception.DhgDomainException;

/**
 * Strategy interface defining overarching rules for different ways to play the game.
 */
public interface GameMode {

    /**
     * Checks if the win conditions for the current level have been met.
     * @param level The level to evaluate.
     * @return true if the level is successfully complete.
     */
    boolean isLevelComplete(Level level) throws DhgDomainException;

    /**
     * Resolves physical collision between two players.
     * Handled differently depending on PvP (damage) vs PvM/SinglePlayer (push/ignore).
     * @param a The first player.
     * @param b The second player.
     */
    void resolvePlayerCollision(Player a, Player b) throws DhgDomainException;

    /**
     * Handles the logic when a player loses a life.
     * Decides whether to respawn the player or trigger Game Over.
     * @param player The player that died.
     * @param model The GameModel facade to trigger state changes.
     */
    void resolvePlayerDeath(Player player, GameModel model) throws DhgDomainException;
}
