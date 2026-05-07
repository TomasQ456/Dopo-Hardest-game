package domain.exception;

/**
 * Base custom exception for domain logic errors in The DOPO Hardest Game.
 */
public class DhgDomainException extends Exception {
    // --- ModelEvent ---
    public static final String ERR_NULL_TYPE    = "ModelEvent type must not be null";
    public static final String ERR_TYPE_NOT_SET = "ModelEvent type is not set";

    // --- Entity ---
    public static final String ERR_NULL_OTHER          = "other entity must not be null";
    public static final String ERR_NULL_POS            = "pos must not be null";
    public static final String ERR_NULL_POSITION       = "position not initialized";
    public static final String ERR_NULL_HITBOX         = "hitBox not initialized";
    public static final String ERR_NULL_HITBOX_PARAM   = "hitBox must not be null";
    public static final String ERR_NULL_POSITION_PARAM = "position must not be null";

    // --- Coin ---
    public static final String ERR_ALREADY_COLLECTED = "coin is already collected";

    // --- Zone ---
    public static final String ERR_NULL_AREA = "zone area not initialized";

    // --- AIController ---
    public static final String ERR_NULL_STRATEGY       = "AIController has no AIStrategy assigned";
    public static final String ERR_NULL_STRATEGY_PARAM = "aiStrategy must not be null";

    // --- Skins ---
    public static final String ERR_NULL_PLAYER = "player must not be null";

    // --- HitBox ---
    public static final String ERR_BOUNDS_NOT_INITIALIZED = "bounds not initialized";
    public static final String ERR_NULL_BOUNDS_PARAM      = "bounds must not be null";

    // --- Level ---
    public static final String ERR_NULL_TILEMAP  = "TileMap not initialized";
    public static final String ERR_NULL_ENTITY   = "entity must not be null";

    // --- LevelManager ---
    public static final String ERR_NO_NEXT_LEVEL    = "no next level available";
    public static final String ERR_NO_LEVELS_LOADED = "no levels loaded";

    // --- SinglePlayerMode ---
    public static final String ERR_NO_SPAWN_FOUND = "no spawn position found in level";

    // --- Player ---
    public static final String ERR_NULL_SKIN  = "skin behavior must not be null";
    public static final String ERR_NULL_SPAWN = "spawn position must not be null";

    // --- Rect / Vector2 ---
    public static final String ERR_NULL_OTHER_RECT   = "other Rect must not be null";
    public static final String ERR_NULL_OTHER_VECTOR = "other Vector2 must not be null";

    // --- GameModelSubject ---
    public static final String ERR_NULL_OBSERVER = "observer must not be null";
    public static final String ERR_NULL_EVENT    = "event must not be null";

    /**
     * Constructs a new exception with the specified detail message.
     * @param message The detail message.
     */
    public DhgDomainException(String message) {
        super(message);
    }
}
