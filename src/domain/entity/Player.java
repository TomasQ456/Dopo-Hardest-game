package domain.entity;

import domain.math.Vector2;
import domain.math.Direction;
import domain.skin.SkinBehavior;
import domain.input.PlayerController;
import domain.exception.DhgDomainException;

/**
 * Represents the playable character in the game.
 * Manages player-specific state such as score, deaths, skins, and input control.
 */
public class Player extends Actor {

    private String borderColor;
    private int deaths;
    private int score;
    private Vector2 spawnPosition;
    private SkinBehavior skinBehavior;
    private int shieldHits;
    private PlayerController controller;

    @Override
    public void update(double deltaSeconds) throws DhgDomainException {}

    @Override
    public void onContact(Player player) throws DhgDomainException {}

    @Override
    protected void applyBounds(domain.level.TileMap tileMap) throws DhgDomainException {}

    /**
     * Sets the intended movement direction based on controller input.
     * Interacts with input handling systems to steer the player.
     * @param dir The desired Direction of movement.
     * @throws DhgDomainException if setting the direction fails.
     */
    public void setDesiredDirection(Direction dir) throws DhgDomainException {}

    /**
     * Sets the aesthetic border color for the player.
     * Used primarily to differentiate players in multiplayer modes.
     * @param color Hex string or color name.
     * @throws DhgDomainException if setting the color fails.
     */
    public void setBorderColor(String color) throws DhgDomainException {}

    /**
     * Gets the current border color of the player.
     * @return The color string.
     * @throws DhgDomainException if retrieval fails.
     */
    public String getBorderColor() throws DhgDomainException { return null; }

    /**
     * Increases the player's internal score by the specified amount.
     * Typically called when a BonusCoin or other score-granting entity is collected.
     * @param points The amount of points to add.
     * @throws DhgDomainException if the points cause an invalid state.
     */
    public void addScore(int points) throws DhgDomainException {}

    /**
     * Increments the player's death count.
     * Called by the GameMode or Level when a fatal collision occurs.
     * @throws DhgDomainException if updating the death count fails.
     */
    public void registerDeath() throws DhgDomainException {}

    /**
     * Resets the player's position to the specified spawn point.
     * Called after a death or when moving to a new checkpoint/level.
     * @param spawn The vector representing the spawn location.
     * @throws DhgDomainException if the respawn sequence fails.
     */
    public void respawn(Vector2 spawn) throws DhgDomainException {}

    /**
     * Applies a new SkinBehavior strategy to the player.
     * Modifies player speed, size, and shield characteristics based on the skin.
     * @param behavior The new SkinBehavior to apply.
     * @throws DhgDomainException if applying the skin fails.
     */
    public void applySkin(SkinBehavior behavior) throws DhgDomainException {}

    /**
     * Adds a hit to the player's current shield.
     * Interacts with SkinBehavior to handle the hit effect.
     * @throws DhgDomainException if shield processing fails.
     */
    public void addShieldHit() throws DhgDomainException {}

    /**
     * Attempts to absorb a hit using the player's skin shield.
     * @return true if the hit was safely absorbed, false if it was fatal.
     * @throws DhgDomainException if hit calculation fails.
     */
    public boolean absorbHit() throws DhgDomainException { return false; }

    /**
     * Retrieves the total number of deaths for this player.
     * @return The death count.
     * @throws DhgDomainException if retrieval fails.
     */
    public int getDeaths() throws DhgDomainException { return 0; }

    /**
     * Retrieves the current score of the player.
     * @return The score.
     * @throws DhgDomainException if retrieval fails.
     */
    public int getScore() throws DhgDomainException { return 0; }
}
