package domain.core;

/**
 * Enumeration of all possible event types that the game model can broadcast.
 */
public enum ModelEventType {
    COIN_COLLECTED,
    PLAYER_DIED,
    CHECKPOINT_REACHED,
    LEVEL_COMPLETED,
    TIME_UP
}
