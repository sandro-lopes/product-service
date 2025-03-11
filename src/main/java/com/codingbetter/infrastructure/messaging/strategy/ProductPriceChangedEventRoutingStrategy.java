package com.codingbetter.infrastructure.messaging.strategy;

import org.springframework.stereotype.Component;

import com.codingbetter.domain.catalog.product.event.ProductPriceChangedEvent;
import com.codingbetter.domain.shared.event.DomainEvent;
import com.codingbetter.infrastructure.config.ProductRabbitMQConfig;

/**
 * Strategy for determining the routing key for ProductPriceChangedEvent.
 */
@Component
public class ProductPriceChangedEventRoutingStrategy implements EventRoutingStrategy {

    @Override
    public boolean canHandle(DomainEvent event) {
        return event instanceof ProductPriceChangedEvent;
    }

    @Override
    public String getRoutingKey(DomainEvent event) {
        return ProductRabbitMQConfig.PRODUCT_PRICE_CHANGED_ROUTING_KEY;
    }
} 