package com.codingbetter.infrastructure.messaging.strategy;

import com.codingbetter.domain.shared.event.DomainEvent;

/**
 * Interface for event routing strategies.
 */
public interface EventRoutingStrategy {
    
    /**
     * Checks if this strategy can handle the provided event.
     * 
     * @param event The event to be checked
     * @return true if this strategy can handle the event, false otherwise
     */
    boolean canHandle(DomainEvent event);
    
    /**
     * Determines the routing key for the provided event.
     * 
     * @param event The event for which to determine the routing key
     * @return The routing key for the event
     */
    String getRoutingKey(DomainEvent event);
} 