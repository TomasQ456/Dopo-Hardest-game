package domain.level;

/**
 * Manages the countdown timer for level completion.
 */
public class TimeController {

    private double timeLimitSeconds;
    private double remainingSeconds;

    /**
     * Sets the maximum time limit for the level.
     * Typically called during level initialization.
     * @param seconds The time limit in seconds.
     */
    public void setLimit(double seconds) {}

    /**
     * Decreases the remaining time by the elapsed delta.
     * Called continuously by Level.update().
     * @param deltaSeconds The time passed since the last frame.
     */
    public void tick(double deltaSeconds) {}

    /**
     * Checks if the time limit has expired.
     * Used by GameMode to trigger a game over state.
     * @return true if remaining time is 0 or less.
     */
    public boolean isTimeUp() { return false; }

    /**
     * Retrieves the remaining time.
     * Used by UI observers to display the timer.
     * @return The time remaining in seconds.
     */
    public double getRemainingSeconds() { return 0.0; }
}
