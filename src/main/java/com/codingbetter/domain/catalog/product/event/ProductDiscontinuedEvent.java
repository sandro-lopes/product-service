package com.codingbetter.domain.catalog.product.event;

import java.time.LocalDateTime;
import java.util.UUID;

import com.codingbetter.domain.catalog.product.model.ProductId;
import com.codingbetter.domain.shared.event.DomainEvent;

public class ProductDiscontinuedEvent implements DomainEvent {
    private final UUID id;
    private final LocalDateTime occurredOn;
    private final ProductId productId;

    public ProductDiscontinuedEvent(ProductId productId) {
        this.id = UUID.randomUUID();
        this.occurredOn = LocalDateTime.now();
        this.productId = productId;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

    public ProductId getProductId() {
        return productId;
    }
} 