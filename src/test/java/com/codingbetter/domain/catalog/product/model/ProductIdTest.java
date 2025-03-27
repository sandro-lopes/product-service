package com.codingbetter.domain.catalog.product.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ProductId Tests")
class ProductIdTest {

    @Test
    @DisplayName("Should create product id successfully")
    void shouldCreateProductIdSuccessfully() {
        // Act
        ProductId productId = new ProductId();

        // Assert
        assertNotNull(productId);
        assertNotNull(productId.getValue());
    }

    @Test
    @DisplayName("Should generate random UUID when creating product id")
    void shouldGenerateRandomUuidWhenCreatingProductId() {
        // Act
        ProductId productId = new ProductId();

        // Assert
        assertNotNull(productId.getUuid());
        assertTrue(productId.getUuid() instanceof UUID);
    }

    @Test
    @DisplayName("Should create two product ids with different UUIDs")
    void shouldCreateTwoProductIdsWithDifferentUuids() {
        // Act
        ProductId productId1 = new ProductId();
        ProductId productId2 = new ProductId();

        // Assert
        assertNotEquals(productId1.getUuid(), productId2.getUuid());
    }

    @Test
    @DisplayName("Should return same UUID when calling getValue multiple times")
    void shouldReturnSameUuidWhenCallingGetValueMultipleTimes() {
        // Arrange
        ProductId productId = new ProductId();
        UUID firstValue = productId.getUuid();

        // Act
        UUID secondValue = productId.getUuid();

        // Assert
        assertEquals(firstValue, secondValue);
    }

    @Test
    @DisplayName("Should create product id with existing UUID")
    void shouldCreateProductIdWithExistingUuid() {
        // Arrange
        UUID uuid = UUID.randomUUID();

        // Act
        ProductId productId = new ProductId(uuid);

        // Assert
        assertEquals(uuid, productId.getUuid());
    }

    @Test
    @DisplayName("Should throw exception when creating product id with null UUID")
    void shouldThrowExceptionWhenCreatingProductIdWithNullUuid() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ProductId(null);
        });
    }

    @Test
    @DisplayName("Should create two product ids with same UUID")
    void shouldCreateTwoProductIdsWithSameUuid() {
        // Arrange
        UUID uuid = UUID.randomUUID();

        // Act
        ProductId productId1 = new ProductId(uuid);
        ProductId productId2 = new ProductId(uuid);

        // Assert
        assertEquals(productId1.getUuid(), productId2.getUuid());
    }

    @Test
    @DisplayName("Should compare product ids correctly")
    void shouldCompareProductIdsCorrectly() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        ProductId productId1 = new ProductId(uuid);
        ProductId productId2 = new ProductId(uuid);
        ProductId productId3 = new ProductId();

        // Assert
        assertTrue(productId1.equals(productId2));
        assertFalse(productId1.equals(productId3));
        assertFalse(productId1.equals(null));
    }

    @Test
    @DisplayName("Should have consistent hash code")
    void shouldHaveConsistentHashCode() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        ProductId productId1 = new ProductId(uuid);
        ProductId productId2 = new ProductId(uuid);

        // Assert
        assertEquals(productId1.hashCode(), productId2.hashCode());
    }

    @Test
    @DisplayName("Should compare product id with itself")
    void shouldCompareProductIdWithItself() {
        // Arrange
        ProductId productId = new ProductId();

        // Assert
        assertEquals(productId, productId);
    }

    @Test
    @DisplayName("Should compare product id with different class")
    void shouldCompareProductIdWithDifferentClass() {
        // Arrange
        ProductId productId = new ProductId();
        Object other = new Object();

        // Assert
        assertNotEquals(productId, other);
    }
} 