package com.codingbetter.product.domain.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductIdTest {

    @Test
    void shouldCreateProductIdSuccessfully() {
        // Act
        ProductId productId = new ProductId();
        
        // Assert
        assertNotNull(productId);
        assertNotNull(productId.getId());
    }
    
    @Test
    void shouldGenerateRandomUuidWhenCreatingProductId() {
        // Act
        ProductId productId = new ProductId();
        
        // Assert
        assertNotNull(productId.getId());
        assertTrue(productId.getId() instanceof UUID);
    }
    
    @Test
    void shouldCreateTwoProductIdsWithDifferentUuids() {
        // Act
        ProductId productId1 = new ProductId();
        ProductId productId2 = new ProductId();
        
        // Assert
        assertNotEquals(productId1.getId(), productId2.getId());
    }
    
    @Test
    void shouldReturnSameUuidWhenCallingGetIdMultipleTimes() {
        // Arrange
        ProductId productId = new ProductId();
        
        // Act
        UUID firstCall = productId.getId();
        UUID secondCall = productId.getId();
        
        // Assert
        assertSame(firstCall, secondCall);
    }
} 