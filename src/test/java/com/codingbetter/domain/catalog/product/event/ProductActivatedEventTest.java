package com.codingbetter.domain.catalog.product.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.codingbetter.domain.catalog.product.model.ProductId;

@DisplayName("ProductActivatedEvent Tests")
class ProductActivatedEventTest {

    @Test
    @DisplayName("Should create event with product ID")
    void shouldCreateEventWithProductId() {
        // Arrange
        ProductId productId = new ProductId(UUID.randomUUID());

        // Act
        ProductActivatedEvent event = new ProductActivatedEvent(productId);

        // Assert
        assertNotNull(event);
        assertNotNull(event.getId());
        assertNotNull(event.getOccurredOn());
        assertEquals(productId, event.getProductId());
    }

    @Test
    @DisplayName("Should have unique ID for each event")
    void shouldHaveUniqueIdForEachEvent() {
        // Arrange
        ProductId productId = new ProductId(UUID.randomUUID());

        // Act
        ProductActivatedEvent event1 = new ProductActivatedEvent(productId);
        ProductActivatedEvent event2 = new ProductActivatedEvent(productId);

        // Assert
        assertNotEquals(event1.getId(), event2.getId());
    }

    @Test
    @DisplayName("Should have occurredOn timestamp")
    void shouldHaveOccurredOnTimestamp() {
        // Arrange
        ProductId productId = new ProductId(UUID.randomUUID());

        // Act
        ProductActivatedEvent event = new ProductActivatedEvent(productId);
        LocalDateTime now = LocalDateTime.now();

        // Assert
        assertNotNull(event.getOccurredOn());
        assertTrue(event.getOccurredOn().isBefore(now) || event.getOccurredOn().equals(now));
    }

    @Test
    @DisplayName("Should maintain same product ID")
    void shouldMaintainSameProductId() {
        // Arrange
        ProductId productId = new ProductId(UUID.randomUUID());

        // Act
        ProductActivatedEvent event = new ProductActivatedEvent(productId);

        // Assert
        assertEquals(productId, event.getProductId());
    }
} 