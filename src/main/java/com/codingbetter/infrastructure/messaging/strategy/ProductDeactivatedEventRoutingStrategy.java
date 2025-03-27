package com.codingbetter.infrastructure.messaging.strategy;

import org.springframework.stereotype.Component;

import com.codingbetter.domain.catalog.product.event.ProductDeactivatedEvent;
import com.codingbetter.domain.shared.event.DomainEvent;
import com.codingbetter.infrastructure.config.ProductRabbitMQConfig;

/**
 * Strategy for determining the routing key for ProductDeactivatedEvent.
 */
@Component
public class ProductDeactivatedEventRoutingStrategy implements EventRoutingStrategy {

    @Override
    public boolean canHandle(DomainEvent event) {
        return event instanceof ProductDeactivatedEvent;
    }

    @Override
    public String getRoutingKey(DomainEvent event) {
        return ProductRabbitMQConfig.PRODUCT_DEACTIVATED_ROUTING_KEY;
    }
} 