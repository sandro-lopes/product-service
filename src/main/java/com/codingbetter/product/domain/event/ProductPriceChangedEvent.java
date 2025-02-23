package com.codingbetter.product.domain.event;

import com.codingbetter.product.domain.model.Money;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductPriceChangedEvent {
    private UUID productId;
    private Money oldPrice;
    private Money newPrice;
}
