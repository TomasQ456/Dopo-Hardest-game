package domain.memento;

import java.io.Serializable;
import domain.math.Vector2;
import domain.skin.SkinBehavior;

/** Data transfer object representing a snapshot of a Player's state. */
public class PlayerMemento implements Serializable {
    private int deaths;
    private int score;
    private Vector2 position;
    private SkinBehavior skin;
    private int shieldHits;

    /**
     * Constructs a PlayerMemento with all player state fields.
     */
    public PlayerMemento(int deaths, int score, Vector2 position, SkinBehavior skin, int shieldHits) {
        this.deaths = deaths;
        this.score = score;
        this.position = position;
        this.skin = skin;
        this.shieldHits = shieldHits;
    }

    public int getDeaths() { return this.deaths; }
    public int getScore() { return this.score; }
    public Vector2 getPosition() { return this.position; }
    public SkinBehavior getSkin() { return this.skin; }
    public int getShieldHits() { return this.shieldHits; }
}
