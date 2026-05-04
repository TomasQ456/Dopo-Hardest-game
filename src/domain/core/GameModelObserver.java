package domain.core;

import domain.exception.DhgDomainException;

/**
 * Observer interface for listening to domain events.
 * Implemented by View or Controller components to react to state changes in the Model.
 */
public interface GameModelObserver {

    /**
     * Called when a significant event occurs in the GameModel.
     * Interacts with the GameModelSubject which triggers this callback.
     * @param event The event payload containing details about what happened.
     * @throws DhgDomainException if handling the event fails.
     */
    void onModelChanged(ModelEvent event) throws DhgDomainException;
}
