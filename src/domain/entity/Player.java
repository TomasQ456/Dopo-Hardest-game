package domain.entity;

import domain.math.Vector2;
import domain.math.Direction;
import domain.math.HitBox;
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
    private SkinBehavior originalSkin;
    private int shieldHits;
    private double speedPenalty;
    private PlayerController controller;

    /**
     * Constructs a Player with specified initial speed.
     * @param speed The initial speed of the player.
     */
    public Player(double speed) {
        super(speed);
        this.deaths = 0;
        this.score = 0;
        this.shieldHits = 0;
        this.speedPenalty = 1.0;
    }

    /**
     * Constructs a Player with position, hitbox, and controller.
     * @param position The player's position.
     * @param hitBox The player's hitbox.
     * @param controller The input controller for this player.
     * @throws DhgDomainException if parameters are invalid.
     */
    public Player(Vector2 position, domain.math.HitBox hitBox,
                  PlayerController controller)
        throws DhgDomainException {
        super(0);
        this.position = position;
        this.hitBox = hitBox;
        this.controller = controller;
        this.speedPenalty = 1.0;
        this.deaths = 0;
        this.score = 0;
        this.shieldHits = 0;
    }

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

    /**
     * Increments the player's death count.
     * Called by the GameMode or Level when a fatal collision occurs.
     * Resets skin to original and speed penalty.
     * @throws DhgDomainException if updating the death count fails.
     */
    public void registerDeath() throws DhgDomainException {
        this.deaths++;
        this.speedPenalty = 1.0;
        if (this.originalSkin != null) {
            this.skinBehavior = this.originalSkin;
        }
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
     * The original skin is stored on first application and restored after death.
     * @param behavior The new SkinBehavior to apply.
     * @throws DhgDomainException if applying the skin fails.
     */
    public void applySkin(SkinBehavior behavior) throws DhgDomainException {
        if (behavior == null)
            throw new DhgDomainException(DhgDomainException.ERR_NULL_SKIN);
        // First skin applied is the original
        if (this.originalSkin == null) this.originalSkin = behavior;
        this.skinBehavior = behavior;
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

    /**
     * Retrieves the current skin behavior applied to this player.
     * Used by the view layer for rendering.
     * @return The current SkinBehavior.
     */
    public SkinBehavior getSkinBehavior() {
        return this.skinBehavior;
    }

    /**
     * Package-private method to reduce speed after being hit.
     * Used internally by skins like ClydeSkin.
     */
    void reduceSpeedAfterHit() {
        this.speedPenalty = 0.7;
    }
}
