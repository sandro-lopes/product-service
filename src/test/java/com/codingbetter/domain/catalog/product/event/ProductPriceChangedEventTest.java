package com.codingbetter.domain.catalog.product.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.codingbetter.domain.catalog.product.model.Money;
import com.codingbetter.domain.catalog.product.model.ProductId;

@DisplayName("ProductPriceChangedEvent Tests")
class ProductPriceChangedEventTest {

    @Test
    @DisplayName("Should create event with product ID and prices")
    void shouldCreateEventWithProductIdAndPrices() {
        // Arrange
        ProductId productId = new ProductId(UUID.randomUUID());
        Money oldPrice = new Money(new BigDecimal("100.00"), Currency.getInstance("BRL"));
        Money newPrice = new Money(new BigDecimal("150.00"), Currency.getInstance("BRL"));

        // Act
        ProductPriceChangedEvent event = new ProductPriceChangedEvent(productId, oldPrice, newPrice);

        // Assert
        assertNotNull(event);
        assertNotNull(event.getId());
        assertNotNull(event.getOccurredOn());
        assertEquals(productId, event.getProductId());
        assertEquals(oldPrice, event.getOldPrice());
        assertEquals(newPrice, event.getNewPrice());
    }

    @Test
    @DisplayName("Should have unique ID for each event")
    void shouldHaveUniqueIdForEachEvent() {
        // Arrange
        ProductId productId = new ProductId(UUID.randomUUID());
        Money oldPrice = new Money(new BigDecimal("100.00"), Currency.getInstance("BRL"));
        Money newPrice = new Money(new BigDecimal("150.00"), Currency.getInstance("BRL"));

        // Act
        ProductPriceChangedEvent event1 = new ProductPriceChangedEvent(productId, oldPrice, newPrice);
        ProductPriceChangedEvent event2 = new ProductPriceChangedEvent(productId, oldPrice, newPrice);

        // Assert
        assertNotEquals(event1.getId(), event2.getId());
    }

    @Test
    @DisplayName("Should have occurredOn timestamp")
    void shouldHaveOccurredOnTimestamp() {
        // Arrange
        ProductId productId = new ProductId(UUID.randomUUID());
        Money oldPrice = new Money(new BigDecimal("100.00"), Currency.getInstance("BRL"));
        Money newPrice = new Money(new BigDecimal("150.00"), Currency.getInstance("BRL"));

        // Act
        ProductPriceChangedEvent event = new ProductPriceChangedEvent(productId, oldPrice, newPrice);
        LocalDateTime now = LocalDateTime.now();

        // Assert
        assertNotNull(event.getOccurredOn());
        assertTrue(event.getOccurredOn().isBefore(now) || event.getOccurredOn().equals(now));
    }

    @Test
    @DisplayName("Should maintain same product ID and prices")
    void shouldMaintainSameProductIdAndPrices() {
        // Arrange
        ProductId productId = new ProductId(UUID.randomUUID());
        Money oldPrice = new Money(new BigDecimal("100.00"), Currency.getInstance("BRL"));
        Money newPrice = new Money(new BigDecimal("150.00"), Currency.getInstance("BRL"));

        // Act
        ProductPriceChangedEvent event = new ProductPriceChangedEvent(productId, oldPrice, newPrice);

        // Assert
        assertEquals(productId, event.getProductId());
        assertEquals(oldPrice, event.getOldPrice());
        assertEquals(newPrice, event.getNewPrice());
    }

    @Test
    @DisplayName("Should handle price decrease")
    void shouldHandlePriceDecrease() {
        // Arrange
        ProductId productId = new ProductId(UUID.randomUUID());
        Money oldPrice = new Money(new BigDecimal("150.00"), Currency.getInstance("BRL"));
        Money newPrice = new Money(new BigDecimal("100.00"), Currency.getInstance("BRL"));

        // Act
        ProductPriceChangedEvent event = new ProductPriceChangedEvent(productId, oldPrice, newPrice);

        // Assert
        assertEquals(oldPrice, event.getOldPrice());
        assertEquals(newPrice, event.getNewPrice());
    }

    @Test
    @DisplayName("Should handle same price")
    void shouldHandleSamePrice() {
        // Arrange
        ProductId productId = new ProductId(UUID.randomUUID());
        Money price = new Money(new BigDecimal("100.00"), Currency.getInstance("BRL"));

        // Act
        ProductPriceChangedEvent event = new ProductPriceChangedEvent(productId, price, price);

        // Assert
        assertEquals(price, event.getOldPrice());
        assertEquals(price, event.getNewPrice());
    }
} 