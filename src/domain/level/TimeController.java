package domain.level;

import domain.exception.DhgDomainException;

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
    public void setLimit(double seconds) throws DhgDomainException {
        this.timeLimitSeconds = seconds;
        this.remainingSeconds = seconds;
    }

    /**
     * Decreases the remaining time by the elapsed delta.
     * Called continuously by Level.update().
     * @param deltaSeconds The time passed since the last frame.
     */
    public void tick(double deltaSeconds) throws DhgDomainException {
        this.remainingSeconds -= deltaSeconds;
        if (this.remainingSeconds < 0.0) {
            this.remainingSeconds = 0.0;
        }
    }

    /**
     * Checks if the time limit has expired.
     * Used by GameMode to trigger a game over state.
     * @return true if remaining time is 0 or less.
     */
    public boolean isTimeUp() throws DhgDomainException {
        return this.remainingSeconds <= 0.0;
    }

    /**
     * Retrieves the remaining time.
     * Used by UI observers to display the timer.
     * @return The time remaining in seconds.
     */
    public double getRemainingSeconds() throws DhgDomainException {
        return this.remainingSeconds;
    }
}
