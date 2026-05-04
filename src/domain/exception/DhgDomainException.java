package domain.exception;

/**
 * Base custom exception for domain logic errors in The DOPO Hardest Game.
 */
public class DhgDomainException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.
     * @param message The detail message.
     */
    public DhgDomainException(String message) {
        super(message);
    }
}
