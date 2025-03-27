package com.codingbetter.domain.catalog.product.event;
    
import java.time.LocalDateTime;
import java.util.UUID;

import com.codingbetter.domain.catalog.product.model.Money;
import com.codingbetter.domain.catalog.product.model.ProductId;
import com.codingbetter.domain.shared.event.DomainEvent;


public class ProductPriceChangedEvent implements DomainEvent {
    private final UUID id;
    private final LocalDateTime occurredOn;
    private final ProductId productId;
    private final Money oldPrice;
    private final Money newPrice;

    public ProductPriceChangedEvent(ProductId productId, Money oldPrice, Money newPrice) {
        this.id = UUID.randomUUID();
        this.occurredOn = LocalDateTime.now();
        this.productId = productId;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
    }

    public ProductId getProductId() {
        return productId;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

    public Money getOldPrice() {
        return oldPrice;
    }

    public Money getNewPrice() {
        return newPrice;
    }


    
}
