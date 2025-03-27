package com.codingbetter.infrastructure.messaging.strategy;

import org.springframework.stereotype.Component;

import com.codingbetter.domain.catalog.product.event.ProductDiscontinuedEvent;
import com.codingbetter.domain.shared.event.DomainEvent;
import com.codingbetter.infrastructure.config.ProductRabbitMQConfig;

/**
 * Strategy for determining the routing key for ProductDiscontinuedEvent.
 */
@Component
public class ProductDiscontinuedEventRoutingStrategy implements EventRoutingStrategy {

    @Override
    public boolean canHandle(DomainEvent event) {
        return event instanceof ProductDiscontinuedEvent;
    }

    @Override
    public String getRoutingKey(DomainEvent event) {
        return ProductRabbitMQConfig.PRODUCT_DISCONTINUED_ROUTING_KEY;
    }
} 