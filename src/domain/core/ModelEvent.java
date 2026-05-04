package domain.core;

import domain.exception.DhgDomainException;

/**
 * Represents an event triggered by the domain model.
 * Carries the event type and potentially other data to inform observers of what changed.
 */
public class ModelEvent {
    
    private ModelEventType type;

    /**
     * Retrieves the specific type of this event.
     * Used by observers to determine how to handle the model change.
     * @return The type of the model event.
     * @throws DhgDomainException if the type cannot be retrieved.
     */
    public ModelEventType getType() throws DhgDomainException {
        return null;
    }
}
