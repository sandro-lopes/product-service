package com.codingbetter.product.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductStatusTest {

    @Test
    void shouldHaveCorrectEnumValues() {
        // Arrange & Act & Assert
        assertEquals(5, ProductStatus.values().length);
        assertEquals(ProductStatus.DRAFT, ProductStatus.valueOf("DRAFT"));
        assertEquals(ProductStatus.ACTIVE, ProductStatus.valueOf("ACTIVE"));
        assertEquals(ProductStatus.INACTIVE, ProductStatus.valueOf("INACTIVE"));
        assertEquals(ProductStatus.DISCONTINUED, ProductStatus.valueOf("DISCONTINUED"));
        assertEquals(ProductStatus.OUT_OF_STOCK, ProductStatus.valueOf("OUT_OF_STOCK"));
    }
    
    @Test
    void shouldReturnCorrectOrdinalValues() {
        // Arrange & Act & Assert
        assertEquals(0, ProductStatus.DRAFT.ordinal());
        assertEquals(1, ProductStatus.ACTIVE.ordinal());
        assertEquals(2, ProductStatus.INACTIVE.ordinal());
        assertEquals(3, ProductStatus.DISCONTINUED.ordinal());
        assertEquals(4, ProductStatus.OUT_OF_STOCK.ordinal());
    }
    
    @Test
    void shouldReturnCorrectToStringValues() {
        // Arrange & Act & Assert
        assertEquals("DRAFT", ProductStatus.DRAFT.toString());
        assertEquals("ACTIVE", ProductStatus.ACTIVE.toString());
        assertEquals("INACTIVE", ProductStatus.INACTIVE.toString());
        assertEquals("DISCONTINUED", ProductStatus.DISCONTINUED.toString());
        assertEquals("OUT_OF_STOCK", ProductStatus.OUT_OF_STOCK.toString());
    }
    
    @Test
    void shouldCompareEnumValuesCorrectly() {
        // Arrange & Act & Assert
        assertTrue(ProductStatus.DRAFT.compareTo(ProductStatus.ACTIVE) < 0);
        assertTrue(ProductStatus.ACTIVE.compareTo(ProductStatus.INACTIVE) < 0);
        assertTrue(ProductStatus.INACTIVE.compareTo(ProductStatus.DISCONTINUED) < 0);
        assertTrue(ProductStatus.DISCONTINUED.compareTo(ProductStatus.OUT_OF_STOCK) < 0);
        assertEquals(0, ProductStatus.DRAFT.compareTo(ProductStatus.DRAFT));
    }
} 