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
    private Direction desiredDirection = Direction.NONE;

    /**
     * Constructs a Player with specified initial speed.
     * @param speed The initial speed of the player.
     */
    public Player(double speed) {
        super(speed);
        this.deaths = 0;
        this.score = 0;
        this.shieldHits = 0;
    }

    @Override
    public void update(double deltaSeconds) throws DhgDomainException {
        if (this.position == null || this.desiredDirection == null || this.desiredDirection == Direction.NONE) {
            return;
        }

        double dx = 0.0;
        double dy = 0.0;
        double diagonalScale = 1.0;

        switch (this.desiredDirection) {
            case UP:
                dy = -1.0;
                break;
            case DOWN:
                dy = 1.0;
                break;
            case LEFT:
                dx = -1.0;
                break;
            case RIGHT:
                dx = 1.0;
                break;
            case UP_LEFT:
                dx = -1.0;
                dy = -1.0;
                diagonalScale = 1.0 / Math.sqrt(2.0);
                break;
            case UP_RIGHT:
                dx = 1.0;
                dy = -1.0;
                diagonalScale = 1.0 / Math.sqrt(2.0);
                break;
            case DOWN_LEFT:
                dx = -1.0;
                dy = 1.0;
                diagonalScale = 1.0 / Math.sqrt(2.0);
                break;
            case DOWN_RIGHT:
                dx = 1.0;
                dy = 1.0;
                diagonalScale = 1.0 / Math.sqrt(2.0);
                break;
            default:
                return;
        }

        this.position = new Vector2(
                this.position.x + dx * this.speed * deltaSeconds * diagonalScale,
                this.position.y + dy * this.speed * deltaSeconds * diagonalScale
        );
    }

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
    public void setDesiredDirection(Direction dir) throws DhgDomainException {
        this.desiredDirection = (dir == null) ? Direction.NONE : dir;
    }

    public void setController(PlayerController c) {
        this.controller = c;
    }

    public PlayerController getController() {
        return this.controller;
    }

    /**
     * Sets the aesthetic border color for the player.
     * Used primarily to differentiate players in multiplayer modes.
     * @param color Hex string or color name.
     * @throws DhgDomainException if setting the color fails.
     */
    public void setBorderColor(String color) throws DhgDomainException {
        this.borderColor = color;
    }

    /**
     * Gets the current border color of the player.
     * @return The color string.
     * @throws DhgDomainException if retrieval fails.
     */
    public String getBorderColor() throws DhgDomainException { return this.borderColor; }

    /**
     * Increases the player's internal score by the specified amount.
     * Typically called when a BonusCoin or other score-granting entity is collected.
     * @param points The amount of points to add.
     * @throws DhgDomainException if the points cause an invalid state.
     */
    public void addScore(int points) throws DhgDomainException {
        this.score += points;
    }

    public void setScore(int score) throws DhgDomainException {
        this.score = score;
    }

    /**
     * Increments the player's death count.
     * Called by the GameMode or Level when a fatal collision occurs.
     * @throws DhgDomainException if updating the death count fails.
     */
    public void registerDeath() throws DhgDomainException {
        this.deaths++;
    }

    public void setDeaths(int deaths) throws DhgDomainException {
        this.deaths = deaths;
    }

    /**
     * Resets the player's position to the specified spawn point.
     * Called after a death or when moving to a new checkpoint/level.
     * @param spawn The vector representing the spawn location.
     * @throws DhgDomainException if the respawn sequence fails.
     */
    public void respawn(Vector2 spawn) throws DhgDomainException {
        this.setPosition(spawn);
    }

    /**
     * Applies a new SkinBehavior strategy to the player.
     * Modifies player speed, size, and shield characteristics based on the skin.
     * @param behavior The new SkinBehavior to apply.
     * @throws DhgDomainException if applying the skin fails.
     */
    public void applySkin(SkinBehavior behavior) throws DhgDomainException {
        this.skinBehavior = behavior;
    }

    public SkinBehavior getSkinBehavior() throws DhgDomainException {
        return this.skinBehavior;
    }

    /**
     * Adds a hit to the player's current shield.
     * Interacts with SkinBehavior to handle the hit effect.
     * @throws DhgDomainException if shield processing fails.
     */
    public void addShieldHit() throws DhgDomainException {
        this.shieldHits++;
    }

    /**
     * Attempts to absorb a hit using the player's skin shield.
     * @return true if the hit was safely absorbed, false if it was fatal.
     * @throws DhgDomainException if hit calculation fails.
     */
    public boolean absorbHit() throws DhgDomainException {
        if (this.shieldHits > 0) {
            this.shieldHits--;
            return true;
        }
        return false;
    }

    public void setShieldHits(int shieldHits) throws DhgDomainException {
        this.shieldHits = shieldHits;
    }

    /**
     * Retrieves the total number of deaths for this player.
     * @return The death count.
     * @throws DhgDomainException if retrieval fails.
     */
    public int getDeaths() throws DhgDomainException { return this.deaths; }

    /**
     * Retrieves the current score of the player.
     * @return The score.
     * @throws DhgDomainException if retrieval fails.
     */
    public int getScore() throws DhgDomainException { return this.score; }

    public int getShieldHits() throws DhgDomainException { return this.shieldHits; }
}
