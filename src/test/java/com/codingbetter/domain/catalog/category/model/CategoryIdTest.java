package com.codingbetter.domain.catalog.category.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

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

    @Test
    void shouldCompareCategoryIdsCorrectly() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        CategoryId categoryId1 = new CategoryId(uuid);
        CategoryId categoryId2 = new CategoryId(uuid);
        CategoryId categoryId3 = new CategoryId(UUID.randomUUID());

        // Assert
        assertTrue(categoryId1.equals(categoryId2));
        assertFalse(categoryId1.equals(categoryId3));
        assertFalse(categoryId1.equals(null));
    }

    @Test
    void shouldHaveConsistentHashCode() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        CategoryId categoryId1 = new CategoryId(uuid);
        CategoryId categoryId2 = new CategoryId(uuid);

        // Assert
        assertEquals(categoryId1.hashCode(), categoryId2.hashCode());
    }

    @Test
    void shouldCompareCategoryIdWithItself() {
        // Arrange
        CategoryId categoryId = new CategoryId(UUID.randomUUID());

        // Assert
        assertEquals(categoryId, categoryId);
    }

    @Test
    void shouldCompareCategoryIdWithDifferentClass() {
        // Arrange
        CategoryId categoryId = new CategoryId(UUID.randomUUID());
        Object other = new Object();

        // Assert
        assertNotEquals(categoryId, other);
    }
} 