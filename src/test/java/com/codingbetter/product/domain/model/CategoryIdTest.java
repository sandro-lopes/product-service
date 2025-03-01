package com.codingbetter.product.domain.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CategoryIdTest {

    @Test
    void shouldCreateCategoryIdSuccessfully() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        
        // Act
        CategoryId categoryId = new CategoryId(uuid);
        
        // Assert
        assertEquals(uuid, categoryId.getValue());
    }
    
    @Test
    void shouldThrowExceptionWhenUuidIsNull() {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new CategoryId(null)
        );
        
        assertEquals("Category ID is required", exception.getMessage());
    }
    
    @Test
    void shouldCreateTwoCategoryIdsWithSameUuid() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        
        // Act
        CategoryId categoryId1 = new CategoryId(uuid);
        CategoryId categoryId2 = new CategoryId(uuid);
        
        // Assert
        assertEquals(categoryId1.getValue(), categoryId2.getValue());
    }
    
    @Test
    void shouldCreateTwoCategoryIdsWithDifferentUuids() {
        // Arrange
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        
        // Act
        CategoryId categoryId1 = new CategoryId(uuid1);
        CategoryId categoryId2 = new CategoryId(uuid2);
        
        // Assert
        assertNotEquals(categoryId1.getValue(), categoryId2.getValue());
    }
} 