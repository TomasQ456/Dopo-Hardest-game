package domain.core;

import domain.exception.DhgDomainException;

/**
 * Subject interface for the Observer pattern in the Game Model.
 * Responsible for managing a list of observers and notifying them
 * when significant events occur within the domain layer.
 */
public interface GameModelSubject {

    /**
     * Adds an observer to the list of listeners.
     * Interacts with GameModel implementation to register View or Controller listeners.
     * @param observer The observer to register.
     * @throws DhgDomainException if adding the observer fails.
     */
    void addObserver(GameModelObserver observer) throws DhgDomainException;

    /**
     * Removes an observer from the list of listeners.
     * Interacts with GameModel implementation to unregister listeners.
     * @param observer The observer to unregister.
     * @throws DhgDomainException if removing the observer fails.
     */
    void removeObserver(GameModelObserver observer) throws DhgDomainException;

    /**
     * Notifies all registered observers about a domain event.
     * Interacts with registered GameModelObserver instances by calling their update methods.
     * @param event The event payload containing details about what happened.
     * @throws DhgDomainException if notification fails.
     */
    void notifyObservers(ModelEvent event) throws DhgDomainException;
}
