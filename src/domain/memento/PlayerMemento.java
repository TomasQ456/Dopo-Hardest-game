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
}
