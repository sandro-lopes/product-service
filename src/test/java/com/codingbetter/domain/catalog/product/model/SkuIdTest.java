package com.codingbetter.domain.catalog.product.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SkuIdTest {

    private static final String VALID_SKU = "NIKSHRTLGBLU001351";
    private static final String INVALID_SKU = "INVALID-SKU";

    @Test
    void shouldCreateSkuIdSuccessfully() {
        // Act
        SkuId skuId = new SkuId(VALID_SKU);
        
        // Assert
        assertEquals(VALID_SKU, skuId.getValue());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidSkuValues")
    void shouldThrowExceptionWhenSkuIsInvalid(String sku, String expectedErrorMessage) {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new SkuId(sku)
        );
        
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void shouldCreateTwoSkuIdsWithSameValue() {
        // Arrange
        SkuId skuId1 = new SkuId(VALID_SKU);
        SkuId skuId2 = new SkuId(VALID_SKU);
        
        // Assert
        assertEquals(skuId1.getValue(), skuId2.getValue());
    }

    @Test
    void shouldCreateTwoSkuIdsWithDifferentValues() {
        // Arrange
        SkuId skuId1 = new SkuId(VALID_SKU);
        SkuId skuId2 = new SkuId("NIKSHRTXLBLU002352");
        
        // Assert
        assertNotEquals(skuId1.getValue(), skuId2.getValue());
    }

    @Test
    void shouldCompareSkuIdsCorrectly() {
        // Arrange
        SkuId skuId1 = new SkuId(VALID_SKU);
        SkuId skuId2 = new SkuId(VALID_SKU);
        SkuId skuId3 = new SkuId("NIKSHRTXLBLU002352");
        
        // Assert
        assertEquals(skuId1, skuId2);
        assertNotEquals(skuId1, skuId3);
    }

    @Test
    void shouldHaveConsistentHashCode() {
        // Arrange
        SkuId skuId1 = new SkuId(VALID_SKU);
        SkuId skuId2 = new SkuId(VALID_SKU);
        
        // Assert
        assertEquals(skuId1.hashCode(), skuId2.hashCode());
    }

    @Test
    void shouldCompareSkuIdWithNull() {
        // Arrange
        SkuId skuId = new SkuId(VALID_SKU);
        
        // Assert
        assertNotEquals(skuId, null);
    }

    @Test
    void shouldCompareSkuIdWithDifferentClass() {
        // Arrange
        SkuId skuId = new SkuId(VALID_SKU);
        Object other = new Object();
        
        // Assert
        assertNotEquals(skuId, other);
    }

    @Test
    void shouldCompareSkuIdWithItself() {
        // Arrange
        SkuId skuId = new SkuId(VALID_SKU);
        
        // Assert
        assertEquals(skuId, skuId);
    }

    private static Stream<Arguments> provideInvalidSkuValues() {
        return Stream.of(
                Arguments.of(null, "SKU is required"),
                Arguments.of("", "SKU must match pattern: [A-Z]{3}[A-Z]{4}[A-Z]{2}[A-Z]{3}\\d{3}\\d{3}"),
                Arguments.of(INVALID_SKU, "SKU must match pattern: [A-Z]{3}[A-Z]{4}[A-Z]{2}[A-Z]{3}\\d{3}\\d{3}"),
                Arguments.of("NIKSHRTLGBLU001", "SKU must match pattern: [A-Z]{3}[A-Z]{4}[A-Z]{2}[A-Z]{3}\\d{3}\\d{3}"),
                Arguments.of("NIKSHRTLGBLU001351EXTRA", "SKU must match pattern: [A-Z]{3}[A-Z]{4}[A-Z]{2}[A-Z]{3}\\d{3}\\d{3}"),
                Arguments.of("123SHRTLGBLU001351", "SKU must match pattern: [A-Z]{3}[A-Z]{4}[A-Z]{2}[A-Z]{3}\\d{3}\\d{3}"),
                Arguments.of("NIK1234LGBLU001351", "SKU must match pattern: [A-Z]{3}[A-Z]{4}[A-Z]{2}[A-Z]{3}\\d{3}\\d{3}"),
                Arguments.of("NIKSHRT12BLU001351", "SKU must match pattern: [A-Z]{3}[A-Z]{4}[A-Z]{2}[A-Z]{3}\\d{3}\\d{3}"),
                Arguments.of("NIKSHRTLG123001351", "SKU must match pattern: [A-Z]{3}[A-Z]{4}[A-Z]{2}[A-Z]{3}\\d{3}\\d{3}"),
                Arguments.of("NIKSHRTLGBLUABC351", "SKU must match pattern: [A-Z]{3}[A-Z]{4}[A-Z]{2}[A-Z]{3}\\d{3}\\d{3}"),
                Arguments.of("NIKSHRTLGBLU001ABC", "SKU must match pattern: [A-Z]{3}[A-Z]{4}[A-Z]{2}[A-Z]{3}\\d{3}\\d{3}")
        );
    }
} 