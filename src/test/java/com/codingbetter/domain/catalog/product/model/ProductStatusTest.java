package com.codingbetter.domain.catalog.product.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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

    @Test
    void shouldGetEnumByName() {
        // Arrange & Act & Assert
        assertEquals(ProductStatus.DRAFT, ProductStatus.valueOf("DRAFT"));
        assertEquals(ProductStatus.ACTIVE, ProductStatus.valueOf("ACTIVE"));
        assertEquals(ProductStatus.INACTIVE, ProductStatus.valueOf("INACTIVE"));
        assertEquals(ProductStatus.DISCONTINUED, ProductStatus.valueOf("DISCONTINUED"));
        assertEquals(ProductStatus.OUT_OF_STOCK, ProductStatus.valueOf("OUT_OF_STOCK"));
    }

    @Test
    void shouldGetEnumByOrdinal() {
        // Arrange & Act & Assert
        assertEquals(ProductStatus.DRAFT, ProductStatus.values()[0]);
        assertEquals(ProductStatus.ACTIVE, ProductStatus.values()[1]);
        assertEquals(ProductStatus.INACTIVE, ProductStatus.values()[2]);
        assertEquals(ProductStatus.DISCONTINUED, ProductStatus.values()[3]);
        assertEquals(ProductStatus.OUT_OF_STOCK, ProductStatus.values()[4]);
    }

    @Test
    void shouldGetEnumName() {
        // Arrange & Act & Assert
        assertEquals("DRAFT", ProductStatus.DRAFT.name());
        assertEquals("ACTIVE", ProductStatus.ACTIVE.name());
        assertEquals("INACTIVE", ProductStatus.INACTIVE.name());
        assertEquals("DISCONTINUED", ProductStatus.DISCONTINUED.name());
        assertEquals("OUT_OF_STOCK", ProductStatus.OUT_OF_STOCK.name());
    }

    @Test
    void shouldGetEnumDeclaringClass() {
        // Arrange & Act & Assert
        assertEquals(ProductStatus.class, ProductStatus.DRAFT.getDeclaringClass());
        assertEquals(ProductStatus.class, ProductStatus.ACTIVE.getDeclaringClass());
        assertEquals(ProductStatus.class, ProductStatus.INACTIVE.getDeclaringClass());
        assertEquals(ProductStatus.class, ProductStatus.DISCONTINUED.getDeclaringClass());
        assertEquals(ProductStatus.class, ProductStatus.OUT_OF_STOCK.getDeclaringClass());
    }

    @Test
    void shouldGetEnumClass() {
        // Arrange & Act & Assert
        assertEquals(ProductStatus.class, ProductStatus.DRAFT.getClass());
        assertEquals(ProductStatus.class, ProductStatus.ACTIVE.getClass());
        assertEquals(ProductStatus.class, ProductStatus.INACTIVE.getClass());
        assertEquals(ProductStatus.class, ProductStatus.DISCONTINUED.getClass());
        assertEquals(ProductStatus.class, ProductStatus.OUT_OF_STOCK.getClass());
    }
} 