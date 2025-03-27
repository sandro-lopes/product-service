package com.codingbetter.infrastructure.messaging.strategy;

import java.util.List;

import org.springframework.stereotype.Component;

import com.codingbetter.domain.shared.event.DomainEvent;

import lombok.extern.slf4j.Slf4j;

/**
 * Factory for obtaining the appropriate routing strategy for an event.
 */
@Component
@Slf4j
public class EventRoutingStrategyFactory {
    
    private final List<EventRoutingStrategy> strategies;
    
    public EventRoutingStrategyFactory(List<EventRoutingStrategy> strategies) {
        this.strategies = strategies;
    }
    
    /**
     * Gets the appropriate routing strategy for the provided event.
     * 
     * @param event The event for which to get the strategy
     * @return The routing strategy for the event, or null if no strategy is found
     */
    public EventRoutingStrategy getStrategy(DomainEvent event) {
        return strategies.stream()
                .filter(strategy -> strategy.canHandle(event))
                .findFirst()
                .orElseGet(() -> {
                    log.warn("No routing strategy found for event: {}", event.getClass().getName());
                    return null;
                });
    }
} 