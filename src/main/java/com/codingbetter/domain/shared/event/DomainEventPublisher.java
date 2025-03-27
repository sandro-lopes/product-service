package com.codingbetter.domain.shared.event;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}
