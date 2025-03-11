package com.codingbetter.infrastructure.messaging.strategy;

import org.springframework.stereotype.Component;

import com.codingbetter.domain.catalog.product.event.ProductActivatedEvent;
import com.codingbetter.domain.shared.event.DomainEvent;
import com.codingbetter.infrastructure.config.ProductRabbitMQConfig;

/**
 * Strategy for determining the routing key for ProductActivatedEvent.
 */
@Component
public class ProductActivatedEventRoutingStrategy implements EventRoutingStrategy {

    @Override
    public boolean canHandle(DomainEvent event) {
        return event instanceof ProductActivatedEvent;
    }

    @Override
    public String getRoutingKey(DomainEvent event) {
        return ProductRabbitMQConfig.PRODUCT_ACTIVATED_ROUTING_KEY;
    }
} 