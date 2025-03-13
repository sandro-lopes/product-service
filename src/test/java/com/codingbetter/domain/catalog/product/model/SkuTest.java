package com.codingbetter.domain.catalog.product.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Sku Tests")
class SkuTest {

    private static final String BRAND = "ABC";
    private static final String PRODUCT_TYPE = "TEST";
    private static final String SIZE = "XL";
    private static final String COLOR = "RED";
    private static final String UNIQUE_ID = "123";
    private static final String LOCATION = "456";

    @Nested
    @DisplayName("Creation Tests")
    class CreationTests {
        @Test
        @DisplayName("Should create SKU successfully")
        void shouldCreateSkuSuccessfully() {
            // Act
            Sku sku = Sku.builder()
                .withBrand(BRAND)
                .withProductType(PRODUCT_TYPE)
                .withSize(SIZE)
                .withColor(COLOR)
                .withUniqueId(UNIQUE_ID)
                .withLocation(LOCATION)
                .build();

            // Assert
            assertNotNull(sku);
            assertEquals(BRAND, sku.getBrand());
            assertEquals(PRODUCT_TYPE, sku.getProductType());
            assertEquals(SIZE, sku.getSize());
            assertEquals(COLOR, sku.getColor());
            assertEquals(UNIQUE_ID, sku.getUniqueId());
            assertEquals(LOCATION, sku.getLocation());
        }

        @Test
        @DisplayName("Should throw exception when brand is null")
        void shouldThrowExceptionWhenBrandIsNull() {
            // Act & Assert
            assertThrows(IllegalStateException.class, () -> {
                Sku.builder()
                    .withProductType(PRODUCT_TYPE)
                    .withSize(SIZE)
                    .withColor(COLOR)
                    .withUniqueId(UNIQUE_ID)
                    .withLocation(LOCATION)
                    .build();
            });
        }

        @Test
        @DisplayName("Should throw exception when product type is null")
        void shouldThrowExceptionWhenProductTypeIsNull() {
            // Act & Assert
            assertThrows(IllegalStateException.class, () -> {
                Sku.builder()
                    .withBrand(BRAND)
                    .withSize(SIZE)
                    .withColor(COLOR)
                    .withUniqueId(UNIQUE_ID)
                    .withLocation(LOCATION)
                    .build();
            });
        }

        @Test
        @DisplayName("Should throw exception when size is null")
        void shouldThrowExceptionWhenSizeIsNull() {
            // Act & Assert
            assertThrows(IllegalStateException.class, () -> {
                Sku.builder()
                    .withBrand(BRAND)
                    .withProductType(PRODUCT_TYPE)
                    .withColor(COLOR)
                    .withUniqueId(UNIQUE_ID)
                    .withLocation(LOCATION)
                    .build();
            });
        }

        @Test
        @DisplayName("Should throw exception when color is null")
        void shouldThrowExceptionWhenColorIsNull() {
            // Act & Assert
            assertThrows(IllegalStateException.class, () -> {
                Sku.builder()
                    .withBrand(BRAND)
                    .withProductType(PRODUCT_TYPE)
                    .withSize(SIZE)
                    .withUniqueId(UNIQUE_ID)
                    .withLocation(LOCATION)
                    .build();
            });
        }

        @Test
        @DisplayName("Should throw exception when unique id is null")
        void shouldThrowExceptionWhenUniqueIdIsNull() {
            // Act & Assert
            assertThrows(IllegalStateException.class, () -> {
                Sku.builder()
                    .withBrand(BRAND)
                    .withProductType(PRODUCT_TYPE)
                    .withSize(SIZE)
                    .withColor(COLOR)
                    .withLocation(LOCATION)
                    .build();
            });
        }

        @Test
        @DisplayName("Should throw exception when location is null")
        void shouldThrowExceptionWhenLocationIsNull() {
            // Act & Assert
            assertThrows(IllegalStateException.class, () -> {
                Sku.builder()
                    .withBrand(BRAND)
                    .withProductType(PRODUCT_TYPE)
                    .withSize(SIZE)
                    .withColor(COLOR)
                    .withUniqueId(UNIQUE_ID)
                    .build();
            });
        }
    }

    @Nested
    @DisplayName("Format Validation Tests")
    class FormatValidationTests {
        @Test
        @DisplayName("Should throw exception when brand format is invalid")
        void shouldThrowExceptionWhenBrandFormatIsInvalid() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                Sku.builder()
                    .withBrand("AB") // Should be 3 uppercase letters
                    .withProductType(PRODUCT_TYPE)
                    .withSize(SIZE)
                    .withColor(COLOR)
                    .withUniqueId(UNIQUE_ID)
                    .withLocation(LOCATION)
                    .build();
            });
        }

        @Test
        @DisplayName("Should throw exception when product type format is invalid")
        void shouldThrowExceptionWhenProductTypeFormatIsInvalid() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                Sku.builder()
                    .withBrand(BRAND)
                    .withProductType("TES") // Should be 4 uppercase letters
                    .withSize(SIZE)
                    .withColor(COLOR)
                    .withUniqueId(UNIQUE_ID)
                    .withLocation(LOCATION)
                    .build();
            });
        }

        @Test
        @DisplayName("Should throw exception when size format is invalid")
        void shouldThrowExceptionWhenSizeFormatIsInvalid() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                Sku.builder()
                    .withBrand(BRAND)
                    .withProductType(PRODUCT_TYPE)
                    .withSize("X") // Should be 2 uppercase letters
                    .withColor(COLOR)
                    .withUniqueId(UNIQUE_ID)
                    .withLocation(LOCATION)
                    .build();
            });
        }

        @Test
        @DisplayName("Should throw exception when color format is invalid")
        void shouldThrowExceptionWhenColorFormatIsInvalid() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                Sku.builder()
                    .withBrand(BRAND)
                    .withProductType(PRODUCT_TYPE)
                    .withSize(SIZE)
                    .withColor("RE") // Should be 3 uppercase letters
                    .withUniqueId(UNIQUE_ID)
                    .withLocation(LOCATION)
                    .build();
            });
        }

        @Test
        @DisplayName("Should throw exception when unique id format is invalid")
        void shouldThrowExceptionWhenUniqueIdFormatIsInvalid() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                Sku.builder()
                    .withBrand(BRAND)
                    .withProductType(PRODUCT_TYPE)
                    .withSize(SIZE)
                    .withColor(COLOR)
                    .withUniqueId("12") // Should be 3 digits
                    .withLocation(LOCATION)
                    .build();
            });
        }

        @Test
        @DisplayName("Should throw exception when location format is invalid")
        void shouldThrowExceptionWhenLocationFormatIsInvalid() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                Sku.builder()
                    .withBrand(BRAND)
                    .withProductType(PRODUCT_TYPE)
                    .withSize(SIZE)
                    .withColor(COLOR)
                    .withUniqueId(UNIQUE_ID)
                    .withLocation("45") // Should be 3 digits
                    .build();
            });
        }

        @Test
        @DisplayName("Should throw exception when brand contains non-uppercase letters")
        void shouldThrowExceptionWhenBrandContainsNonUppercaseLetters() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                Sku.builder()
                    .withBrand("AbC") // Contains lowercase letters
                    .withProductType(PRODUCT_TYPE)
                    .withSize(SIZE)
                    .withColor(COLOR)
                    .withUniqueId(UNIQUE_ID)
                    .withLocation(LOCATION)
                    .build();
            });
        }

        @Test
        @DisplayName("Should throw exception when product type contains non-uppercase letters")
        void shouldThrowExceptionWhenProductTypeContainsNonUppercaseLetters() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                Sku.builder()
                    .withBrand(BRAND)
                    .withProductType("TeSt") // Contains lowercase letters
                    .withSize(SIZE)
                    .withColor(COLOR)
                    .withUniqueId(UNIQUE_ID)
                    .withLocation(LOCATION)
                    .build();
            });
        }

        @Test
        @DisplayName("Should throw exception when size contains non-uppercase letters")
        void shouldThrowExceptionWhenSizeContainsNonUppercaseLetters() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                Sku.builder()
                    .withBrand(BRAND)
                    .withProductType(PRODUCT_TYPE)
                    .withSize("Xl") // Contains lowercase letters
                    .withColor(COLOR)
                    .withUniqueId(UNIQUE_ID)
                    .withLocation(LOCATION)
                    .build();
            });
        }

        @Test
        @DisplayName("Should throw exception when color contains non-uppercase letters")
        void shouldThrowExceptionWhenColorContainsNonUppercaseLetters() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                Sku.builder()
                    .withBrand(BRAND)
                    .withProductType(PRODUCT_TYPE)
                    .withSize(SIZE)
                    .withColor("ReD") // Contains lowercase letters
                    .withUniqueId(UNIQUE_ID)
                    .withLocation(LOCATION)
                    .build();
            });
        }

        @Test
        @DisplayName("Should throw exception when unique id contains non-digit characters")
        void shouldThrowExceptionWhenUniqueIdContainsNonDigitCharacters() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                Sku.builder()
                    .withBrand(BRAND)
                    .withProductType(PRODUCT_TYPE)
                    .withSize(SIZE)
                    .withColor(COLOR)
                    .withUniqueId("12A") // Contains non-digit character
                    .withLocation(LOCATION)
                    .build();
            });
        }

        @Test
        @DisplayName("Should throw exception when location contains non-digit characters")
        void shouldThrowExceptionWhenLocationContainsNonDigitCharacters() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                Sku.builder()
                    .withBrand(BRAND)
                    .withProductType(PRODUCT_TYPE)
                    .withSize(SIZE)
                    .withColor(COLOR)
                    .withUniqueId(UNIQUE_ID)
                    .withLocation("45A") // Contains non-digit character
                    .build();
            });
        }
    }

    @Nested
    @DisplayName("Value Conversion Tests")
    class ValueConversionTests {
        @Test
        @DisplayName("Should convert SKU to string value")
        void shouldConvertSkuToStringValue() {
            // Arrange
            Sku sku = Sku.builder()
                .withBrand(BRAND)
                .withProductType(PRODUCT_TYPE)
                .withSize(SIZE)
                .withColor(COLOR)
                .withUniqueId(UNIQUE_ID)
                .withLocation(LOCATION)
                .build();

            // Act
            String value = Sku.toValue(sku);

            // Assert
            assertEquals(BRAND + PRODUCT_TYPE + SIZE + COLOR + UNIQUE_ID + LOCATION, value);
        }

        @Test
        @DisplayName("Should throw exception when converting null SKU to value")
        void shouldThrowExceptionWhenConvertingNullSkuToValue() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                Sku.toValue(null);
            });
        }

        @Test
        @DisplayName("Should create SKU from string value")
        void shouldCreateSkuFromStringValue() {
            // Arrange
            String skuValue = BRAND + PRODUCT_TYPE + SIZE + COLOR + UNIQUE_ID + LOCATION;

            // Act
            Sku sku = Sku.fromValue(skuValue);

            // Assert
            assertNotNull(sku);
            assertEquals(BRAND, sku.getBrand());
            assertEquals(PRODUCT_TYPE, sku.getProductType());
            assertEquals(SIZE, sku.getSize());
            assertEquals(COLOR, sku.getColor());
            assertEquals(UNIQUE_ID, sku.getUniqueId());
            assertEquals(LOCATION, sku.getLocation());
        }

        @Test
        @DisplayName("Should throw exception when creating SKU from null value")
        void shouldThrowExceptionWhenCreatingSkuFromNullValue() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                Sku.fromValue(null);
            });
        }

        @Test
        @DisplayName("Should throw exception when creating SKU from invalid length value")
        void shouldThrowExceptionWhenCreatingSkuFromInvalidLengthValue() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                Sku.fromValue("ABC"); // Too short
            });
        }

        @Test
        @DisplayName("Should throw exception when creating SKU from value with invalid brand format")
        void shouldThrowExceptionWhenCreatingSkuFromValueWithInvalidBrandFormat() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                Sku.fromValue("ABCTESTXLRE123456"); // Brand should be 3 uppercase letters
            });
        }

        @Test
        @DisplayName("Should throw exception when creating SKU from value with invalid product type format")
        void shouldThrowExceptionWhenCreatingSkuFromValueWithInvalidProductTypeFormat() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                Sku.fromValue("ABCTESXLRE123456"); // Product type should be 4 uppercase letters
            });
        }

        @Test
        @DisplayName("Should throw exception when creating SKU from value with invalid size format")
        void shouldThrowExceptionWhenCreatingSkuFromValueWithInvalidSizeFormat() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                Sku.fromValue("ABCTESTXRE123456"); // Size should be 2 uppercase letters
            });
        }

        @Test
        @DisplayName("Should throw exception when creating SKU from value with invalid color format")
        void shouldThrowExceptionWhenCreatingSkuFromValueWithInvalidColorFormat() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                Sku.fromValue("ABCTESTXLRE123456"); // Color should be 3 uppercase letters
            });
        }

        @Test
        @DisplayName("Should throw exception when creating SKU from value with invalid unique id format")
        void shouldThrowExceptionWhenCreatingSkuFromValueWithInvalidUniqueIdFormat() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                Sku.fromValue("ABCTESTXLRE123456"); // Unique ID should be 3 digits
            });
        }

        @Test
        @DisplayName("Should throw exception when creating SKU from value with invalid location format")
        void shouldThrowExceptionWhenCreatingSkuFromValueWithInvalidLocationFormat() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                Sku.fromValue("ABCTESTXLRE12345"); // Location should be 3 digits
            });
        }

        @Test
        @DisplayName("Should throw exception when creating SKU from value with non-uppercase letters")
        void shouldThrowExceptionWhenCreatingSkuFromValueWithNonUppercaseLetters() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                Sku.fromValue("AbCTESTXLRE123456"); // Contains lowercase letters
            });
        }

        @Test
        @DisplayName("Should throw exception when creating SKU from value with non-digit characters")
        void shouldThrowExceptionWhenCreatingSkuFromValueWithNonDigitCharacters() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                Sku.fromValue("ABCTESTXLRE12A456"); // Contains non-digit character
            });
        }

        @Test
        @DisplayName("Should throw exception when creating SKU from value with invalid string length")
        void shouldThrowExceptionWhenCreatingSkuFromValueWithInvalidStringLength() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                Sku.fromValue("ABCTESTXLRE1234567"); // Too long
            });
        }

        @Test
        @DisplayName("Should throw exception when creating SKU from value with invalid substring")
        void shouldThrowExceptionWhenCreatingSkuFromValueWithInvalidSubstring() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                Sku.fromValue("ABCTESTXLRE123456"); // Invalid substring
            });
        }

        @Test
        @DisplayName("Should throw exception when creating SKU from value with string index out of bounds")
        void shouldThrowExceptionWhenCreatingSkuFromValueWithStringIndexOutOfBounds() {
            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                Sku.fromValue("ABCTESTXLRE123456789"); // String long enough to cause StringIndexOutOfBoundsException
            });
        }
    }

    @Nested
    @DisplayName("Equals and HashCode Tests")
    class EqualsAndHashCodeTests {
        @Test
        @DisplayName("Should return true when comparing same SKU")
        void shouldReturnTrueWhenComparingSameSku() {
            // Arrange
            Sku sku = Sku.builder()
                .withBrand(BRAND)
                .withProductType(PRODUCT_TYPE)
                .withSize(SIZE)
                .withColor(COLOR)
                .withUniqueId(UNIQUE_ID)
                .withLocation(LOCATION)
                .build();

            // Act & Assert
            assertTrue(sku.equals(sku));
        }

        @Test
        @DisplayName("Should return false when comparing with null")
        void shouldReturnFalseWhenComparingWithNull() {
            // Arrange
            Sku sku = Sku.builder()
                .withBrand(BRAND)
                .withProductType(PRODUCT_TYPE)
                .withSize(SIZE)
                .withColor(COLOR)
                .withUniqueId(UNIQUE_ID)
                .withLocation(LOCATION)
                .build();

            // Act & Assert
            assertFalse(sku.equals(null));
        }

        @Test
        @DisplayName("Should return false when comparing with different class")
        void shouldReturnFalseWhenComparingWithDifferentClass() {
            // Arrange
            Sku sku = Sku.builder()
                .withBrand(BRAND)
                .withProductType(PRODUCT_TYPE)
                .withSize(SIZE)
                .withColor(COLOR)
                .withUniqueId(UNIQUE_ID)
                .withLocation(LOCATION)
                .build();

            // Act & Assert
            assertFalse(sku.equals(new Object()));
        }

        @Test
        @DisplayName("Should return true when comparing SKUs with same value")
        void shouldReturnTrueWhenComparingSkusWithSameValue() {
            // Arrange
            Sku sku1 = Sku.builder()
                .withBrand(BRAND)
                .withProductType(PRODUCT_TYPE)
                .withSize(SIZE)
                .withColor(COLOR)
                .withUniqueId(UNIQUE_ID)
                .withLocation(LOCATION)
                .build();

            Sku sku2 = Sku.builder()
                .withBrand(BRAND)
                .withProductType(PRODUCT_TYPE)
                .withSize(SIZE)
                .withColor(COLOR)
                .withUniqueId(UNIQUE_ID)
                .withLocation(LOCATION)
                .build();

            // Act & Assert
            assertTrue(sku1.equals(sku2));
        }

        @Test
        @DisplayName("Should return false when comparing SKUs with different values")
        void shouldReturnFalseWhenComparingSkusWithDifferentValues() {
            // Arrange
            Sku sku1 = Sku.builder()
                .withBrand(BRAND)
                .withProductType(PRODUCT_TYPE)
                .withSize(SIZE)
                .withColor(COLOR)
                .withUniqueId(UNIQUE_ID)
                .withLocation(LOCATION)
                .build();

            Sku sku2 = Sku.builder()
                .withBrand("XYZ")
                .withProductType(PRODUCT_TYPE)
                .withSize(SIZE)
                .withColor(COLOR)
                .withUniqueId(UNIQUE_ID)
                .withLocation(LOCATION)
                .build();

            // Act & Assert
            assertFalse(sku1.equals(sku2));
        }

        @Test
        @DisplayName("Should return same hashCode for equal SKUs")
        void shouldReturnSameHashCodeForEqualSkus() {
            // Arrange
            Sku sku1 = Sku.builder()
                .withBrand(BRAND)
                .withProductType(PRODUCT_TYPE)
                .withSize(SIZE)
                .withColor(COLOR)
                .withUniqueId(UNIQUE_ID)
                .withLocation(LOCATION)
                .build();

            Sku sku2 = Sku.builder()
                .withBrand(BRAND)
                .withProductType(PRODUCT_TYPE)
                .withSize(SIZE)
                .withColor(COLOR)
                .withUniqueId(UNIQUE_ID)
                .withLocation(LOCATION)
                .build();

            // Act & Assert
            assertEquals(sku1.hashCode(), sku2.hashCode());
        }
    }
} 