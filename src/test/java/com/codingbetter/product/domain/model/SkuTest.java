package com.codingbetter.product.domain.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SkuTest {

    private static final String BRAND = "NIK";
    private static final String PRODUCT_TYPE = "SHRT";
    private static final String SIZE = "LG";
    private static final String COLOR_BLUE = "BLU";
    private static final String COLOR_RED = "RED";
    private static final String UNIQUE_ID = "001";
    private static final String LOCATION = "351";
    private static final String COMPLETE_SKU_BLUE = "NIKSHRTLGBLU001351";
    private static final String COMPLETE_SKU_RED = "NIKSHRTLGRED001351";

    @Test
    void shouldCreateSkuSuccessfullyUsingBuilder() {
        // Arrange & Act
        Sku sku = Sku.builder()
                .withBrand(BRAND)
                .withProductType(PRODUCT_TYPE)
                .withSize(SIZE)
                .withColor(COLOR_BLUE)
                .withUniqueId(UNIQUE_ID)
                .withLocation(LOCATION)
                .build();
        
        // Assert
        assertEquals(COMPLETE_SKU_BLUE, sku.getValue());
        assertEquals(BRAND, sku.getBrand());
        assertEquals(PRODUCT_TYPE, sku.getProductType());
        assertEquals(SIZE, sku.getSize());
        assertEquals(COLOR_BLUE, sku.getColor());
        assertEquals(UNIQUE_ID, sku.getUniqueId());
        assertEquals(LOCATION, sku.getLocation());
    }
    
    @Test
    void shouldCreateSkuFromValueSuccessfully() {
        // Arrange & Act
        Sku sku = Sku.fromValue(COMPLETE_SKU_BLUE);
        
        // Assert
        assertEquals(COMPLETE_SKU_BLUE, sku.getValue());
        assertEquals(BRAND, sku.getBrand());
        assertEquals(PRODUCT_TYPE, sku.getProductType());
        assertEquals(SIZE, sku.getSize());
        assertEquals(COLOR_BLUE, sku.getColor());
        assertEquals(UNIQUE_ID, sku.getUniqueId());
        assertEquals(LOCATION, sku.getLocation());
    }

    
    @Test
    void shouldConvertSkuToValueSuccessfully() {
        // Arrange
        Sku sku = Sku.builder()
                .withBrand(BRAND)
                .withProductType(PRODUCT_TYPE)
                .withSize(SIZE)
                .withColor(COLOR_BLUE)
                .withUniqueId(UNIQUE_ID)
                .withLocation(LOCATION)
                .build();
        
        // Act
        String skuValue = Sku.toValue(sku);
        
        // Assert
        assertEquals(COMPLETE_SKU_BLUE, skuValue);
    }
    
    @Test
    void shouldThrowExceptionWhenConvertingNullSkuToValue() {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Sku.toValue(null)
        );
        
        assertEquals("Sku cannot be null", exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionWhenSkuValueIsNull() {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Sku.fromValue(null)
        );
        
        assertTrue(exception.getMessage().contains("Invalid SKU"));
    }
    
    @Test
    void shouldThrowExceptionWhenSkuValueDoesNotMatchPattern() {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Sku.fromValue("INVALID-SKU")
        );
        
        assertTrue(exception.getMessage().contains("Invalid SKU"));
    }
    
    @Test
    void shouldThrowExceptionWhenSkuValueIsTooShort() {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Sku.fromValue("NIKSHRTLGBLU001")
        );
        
        assertTrue(exception.getMessage().contains("Invalid SKU"));
    }
    
    @Test
    void shouldThrowExceptionWhenSkuValueIsTooLong() {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Sku.fromValue("NIKSHRTLGBLU001351EXTRA")
        );
        
        assertTrue(exception.getMessage().contains("Invalid SKU"));
    }
    
    @Test
    void shouldThrowExceptionWhenSkuValueHasInvalidBrand() {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Sku.fromValue("123SHRTLGBLU001351")
        );
        
        assertTrue(exception.getMessage().contains("Invalid SKU"));
    }
    
    @Test
    void shouldThrowExceptionWhenSkuValueHasInvalidProductType() {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Sku.fromValue("NIK1234LGBLU001351")
        );
        
        assertTrue(exception.getMessage().contains("Invalid SKU"));
    }
    
    @Test
    void shouldThrowExceptionWhenSkuValueHasInvalidSize() {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Sku.fromValue("NIKSHRT12BLU001351")
        );
        
        assertTrue(exception.getMessage().contains("Invalid SKU"));
    }
    
    @Test
    void shouldThrowExceptionWhenSkuValueHasInvalidColor() {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Sku.fromValue("NIKSHRTLG123001351")
        );
        
        assertTrue(exception.getMessage().contains("Invalid SKU"));
    }
    
    @Test
    void shouldThrowExceptionWhenSkuValueHasInvalidUniqueId() {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Sku.fromValue("NIKSHRTLGBLUABC351")
        );
        
        assertTrue(exception.getMessage().contains("Invalid SKU"));
    }
    
    @Test
    void shouldThrowExceptionWhenSkuValueHasInvalidLocation() {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Sku.fromValue("NIKSHRTLGBLU001ABC")
        );
        
        assertTrue(exception.getMessage().contains("Invalid SKU"));
    }
    
    @Test
    void shouldCreateSkuFromValueWithDifferentBrand() {
        // Arrange
        String skuValue = "ADISHRTLGBLU001351";
        
        // Act
        Sku sku = Sku.fromValue(skuValue);
        
        // Assert
        assertEquals("ADI", sku.getBrand());
        assertEquals(PRODUCT_TYPE, sku.getProductType());
        assertEquals(SIZE, sku.getSize());
        assertEquals(COLOR_BLUE, sku.getColor());
        assertEquals(UNIQUE_ID, sku.getUniqueId());
        assertEquals(LOCATION, sku.getLocation());
    }
    
    @Test
    void shouldCreateSkuFromValueWithDifferentProductType() {
        // Arrange
        String skuValue = "NIKPANTLGBLU001351";
        
        // Act
        Sku sku = Sku.fromValue(skuValue);
        
        // Assert
        assertEquals(BRAND, sku.getBrand());
        assertEquals("PANT", sku.getProductType());
        assertEquals(SIZE, sku.getSize());
        assertEquals(COLOR_BLUE, sku.getColor());
        assertEquals(UNIQUE_ID, sku.getUniqueId());
        assertEquals(LOCATION, sku.getLocation());
    }
    
    @Test
    void shouldCreateSkuFromValueWithDifferentSize() {
        // Arrange
        String skuValue = "NIKSHRTXLBLU001351";
        
        // Act
        Sku sku = Sku.fromValue(skuValue);
        
        // Assert
        assertEquals(BRAND, sku.getBrand());
        assertEquals(PRODUCT_TYPE, sku.getProductType());
        assertEquals("XL", sku.getSize());
        assertEquals(COLOR_BLUE, sku.getColor());
        assertEquals(UNIQUE_ID, sku.getUniqueId());
        assertEquals(LOCATION, sku.getLocation());
    }
    
    @Test
    void shouldCreateSkuFromValueWithDifferentColor() {
        // Arrange
        String skuValue = "NIKSHRTLGRED001351";
        
        // Act
        Sku sku = Sku.fromValue(skuValue);
        
        // Assert
        assertEquals(BRAND, sku.getBrand());
        assertEquals(PRODUCT_TYPE, sku.getProductType());
        assertEquals(SIZE, sku.getSize());
        assertEquals(COLOR_RED, sku.getColor());
        assertEquals(UNIQUE_ID, sku.getUniqueId());
        assertEquals(LOCATION, sku.getLocation());
    }
    
    @Test
    void shouldCreateSkuFromValueWithDifferentUniqueId() {
        // Arrange
        String skuValue = "NIKSHRTLGBLU002351";
        
        // Act
        Sku sku = Sku.fromValue(skuValue);
        
        // Assert
        assertEquals(BRAND, sku.getBrand());
        assertEquals(PRODUCT_TYPE, sku.getProductType());
        assertEquals(SIZE, sku.getSize());
        assertEquals(COLOR_BLUE, sku.getColor());
        assertEquals("002", sku.getUniqueId());
        assertEquals(LOCATION, sku.getLocation());
    }
    
    @Test
    void shouldCreateSkuFromValueWithDifferentLocation() {
        // Arrange
        String skuValue = "NIKSHRTLGBLU001352";
        
        // Act
        Sku sku = Sku.fromValue(skuValue);
        
        // Assert
        assertEquals(BRAND, sku.getBrand());
        assertEquals(PRODUCT_TYPE, sku.getProductType());
        assertEquals(SIZE, sku.getSize());
        assertEquals(COLOR_BLUE, sku.getColor());
        assertEquals(UNIQUE_ID, sku.getUniqueId());
        assertEquals("352", sku.getLocation());
    }
    
    @Test
    void shouldCreateSkuBuilderSuccessfully() {
        // Arrange & Act
        Sku.SkuBuilder builder = Sku.builder();
        
        // Assert
        assertNotNull(builder);
        assertTrue(builder instanceof Sku.SkuBuilder);
    }
    
    @Test
    void shouldExtractCorrectComponentsFromSkuValue() {
        // Arrange
        String customSku = "ABCDEFGXYZZZ123456";
        
        // Act
        Sku sku = Sku.fromValue(customSku);
        
        // Assert
        assertEquals("ABC", sku.getBrand());
        assertEquals("DEFG", sku.getProductType());
        assertEquals("XY", sku.getSize());
        assertEquals("ZZZ", sku.getColor());
        assertEquals("123", sku.getUniqueId());
        assertEquals("456", sku.getLocation());
        assertEquals(customSku, sku.getValue());
    }
    
    @Test
    void shouldThrowExceptionWhenBuildingWithoutRequiredFields() {
        // Arrange, Act & Assert
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> Sku.builder().build()
        );
        
        assertEquals("Brand is required", exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionWhenBuildingWithBrandButNoProductType() {
        // Arrange, Act & Assert
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> Sku.builder()
                        .withBrand(BRAND)
                        .build()
        );
        
        assertEquals("Product type is required", exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionWhenBuildingWithBrandAndProductTypeButNoSize() {
        // Arrange, Act & Assert
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> Sku.builder()
                        .withBrand(BRAND)
                        .withProductType(PRODUCT_TYPE)
                        .build()
        );
        
        assertEquals("Size is required", exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionWhenBuildingWithBrandProductTypeSizeButNoColor() {
        // Arrange, Act & Assert
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> Sku.builder()
                        .withBrand(BRAND)
                        .withProductType(PRODUCT_TYPE)
                        .withSize(SIZE)
                        .build()
        );
        
        assertEquals("Color is required", exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionWhenBuildingWithBrandProductTypeSizeColorButNoUniqueId() {
        // Arrange, Act & Assert
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> Sku.builder()
                        .withBrand(BRAND)
                        .withProductType(PRODUCT_TYPE)
                        .withSize(SIZE)
                        .withColor(COLOR_BLUE)
                        .build()
        );
        
        assertEquals("Unique ID is required", exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionWhenBuildingWithBrandProductTypeSizeColorUniqueIdButNoLocation() {
        // Arrange, Act & Assert
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> Sku.builder()
                        .withBrand(BRAND)
                        .withProductType(PRODUCT_TYPE)
                        .withSize(SIZE)
                        .withColor(COLOR_BLUE)
                        .withUniqueId(UNIQUE_ID)
                        .build()
        );
        
        assertEquals("Location is required", exception.getMessage());
    }
    
    @Test
    void shouldCreateSkuWithDifferentBuilderOrder() {
        // Arrange & Act
        Sku sku = Sku.builder()
                .withLocation(LOCATION)
                .withUniqueId(UNIQUE_ID)
                .withColor(COLOR_BLUE)
                .withSize(SIZE)
                .withProductType(PRODUCT_TYPE)
                .withBrand(BRAND)
                .build();
        
        // Assert
        assertEquals(COMPLETE_SKU_BLUE, sku.getValue());
    }
    
    @Test
    void shouldCreateTwoIdenticalSkusWithSameValues() {
        // Arrange
        Sku sku1 = Sku.builder()
                .withBrand(BRAND)
                .withProductType(PRODUCT_TYPE)
                .withSize(SIZE)
                .withColor(COLOR_BLUE)
                .withUniqueId(UNIQUE_ID)
                .withLocation(LOCATION)
                .build();
        
        Sku sku2 = Sku.builder()
                .withBrand(BRAND)
                .withProductType(PRODUCT_TYPE)
                .withSize(SIZE)
                .withColor(COLOR_BLUE)
                .withUniqueId(UNIQUE_ID)
                .withLocation(LOCATION)
                .build();
        
        // Assert
        assertEquals(sku1.getValue(), sku2.getValue());
        assertEquals(sku1.getBrand(), sku2.getBrand());
        assertEquals(sku1.getProductType(), sku2.getProductType());
        assertEquals(sku1.getSize(), sku2.getSize());
        assertEquals(sku1.getColor(), sku2.getColor());
        assertEquals(sku1.getUniqueId(), sku2.getUniqueId());
        assertEquals(sku1.getLocation(), sku2.getLocation());
    }
    
    @Test
    void shouldCreateSkuWithDifferentBrand() {
        // Arrange
        String differentBrand = "ADI";
        
        // Act
        Sku sku = Sku.builder()
                .withBrand(differentBrand)
                .withProductType(PRODUCT_TYPE)
                .withSize(SIZE)
                .withColor(COLOR_BLUE)
                .withUniqueId(UNIQUE_ID)
                .withLocation(LOCATION)
                .build();
        
        // Assert
        assertEquals(differentBrand, sku.getBrand());
        assertNotEquals(BRAND, sku.getBrand());
    }
    
    @Test
    void shouldCreateSkuWithDifferentProductType() {
        // Arrange
        String differentProductType = "PANT";
        
        // Act
        Sku sku = Sku.builder()
                .withBrand(BRAND)
                .withProductType(differentProductType)
                .withSize(SIZE)
                .withColor(COLOR_BLUE)
                .withUniqueId(UNIQUE_ID)
                .withLocation(LOCATION)
                .build();
        
        // Assert
        assertEquals(differentProductType, sku.getProductType());
        assertNotEquals(PRODUCT_TYPE, sku.getProductType());
    }
    
    @Test
    void shouldCreateSkuWithDifferentSize() {
        // Arrange
        String differentSize = "SM";
        
        // Act
        Sku sku = Sku.builder()
                .withBrand(BRAND)
                .withProductType(PRODUCT_TYPE)
                .withSize(differentSize)
                .withColor(COLOR_BLUE)
                .withUniqueId(UNIQUE_ID)
                .withLocation(LOCATION)
                .build();
        
        // Assert
        assertEquals(differentSize, sku.getSize());
        assertNotEquals(SIZE, sku.getSize());
    }
    
    @Test
    void shouldCreateSkuWithDifferentColor() {
        // Arrange
        String differentColor = "RED";
        
        // Act
        Sku sku = Sku.builder()
                .withBrand(BRAND)
                .withProductType(PRODUCT_TYPE)
                .withSize(SIZE)
                .withColor(differentColor)
                .withUniqueId(UNIQUE_ID)
                .withLocation(LOCATION)
                .build();
        
        // Assert
        assertEquals(differentColor, sku.getColor());
        assertNotEquals(COLOR_BLUE, sku.getColor());
    }
    
    @Test
    void shouldCreateSkuWithDifferentUniqueId() {
        // Arrange
        String differentUniqueId = "002";
        
        // Act
        Sku sku = Sku.builder()
                .withBrand(BRAND)
                .withProductType(PRODUCT_TYPE)
                .withSize(SIZE)
                .withColor(COLOR_BLUE)
                .withUniqueId(differentUniqueId)
                .withLocation(LOCATION)
                .build();
        
        // Assert
        assertEquals(differentUniqueId, sku.getUniqueId());
        assertNotEquals(UNIQUE_ID, sku.getUniqueId());
    }
    
    @Test
    void shouldCreateSkuWithDifferentLocation() {
        // Arrange
        String differentLocation = "352";
        
        // Act
        Sku sku = Sku.builder()
                .withBrand(BRAND)
                .withProductType(PRODUCT_TYPE)
                .withSize(SIZE)
                .withColor(COLOR_BLUE)
                .withUniqueId(UNIQUE_ID)
                .withLocation(differentLocation)
                .build();
        
        // Assert
        assertEquals(differentLocation, sku.getLocation());
        assertNotEquals(LOCATION, sku.getLocation());
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidBrandValues")
    void shouldThrowExceptionWhenBrandIsInvalid(String brand, String expectedErrorMessage) {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Sku.builder().withBrand(brand)
        );
        
        assertEquals(expectedErrorMessage, exception.getMessage());
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidProductTypeValues")
    void shouldThrowExceptionWhenProductTypeIsInvalid(String productType, String expectedErrorMessage) {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Sku.builder().withProductType(productType)
        );
        
        assertEquals(expectedErrorMessage, exception.getMessage());
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidSizeValues")
    void shouldThrowExceptionWhenSizeIsInvalid(String size, String expectedErrorMessage) {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Sku.builder().withSize(size)
        );
        
        assertEquals(expectedErrorMessage, exception.getMessage());
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidColorValues")
    void shouldThrowExceptionWhenColorIsInvalid(String color, String expectedErrorMessage) {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Sku.builder().withColor(color)
        );
        
        assertEquals(expectedErrorMessage, exception.getMessage());
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidUniqueIdValues")
    void shouldThrowExceptionWhenUniqueIdIsInvalid(String uniqueId, String expectedErrorMessage) {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Sku.builder().withUniqueId(uniqueId)
        );
        
        assertEquals(expectedErrorMessage, exception.getMessage());
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidLocationValues")
    void shouldThrowExceptionWhenLocationIsInvalid(String location, String expectedErrorMessage) {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Sku.builder().withLocation(location)
        );
        
        assertEquals(expectedErrorMessage, exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionWhenColorHasLowercaseLetters() {
        // Arrange
        String invalidColor = "Blu"; // Contains lowercase letters
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Sku.builder()
                        .withBrand(BRAND)
                        .withProductType(PRODUCT_TYPE)
                        .withSize(SIZE)
                        .withColor(invalidColor)
                        .withUniqueId(UNIQUE_ID)
                        .withLocation(LOCATION)
                        .build()
        );
        
        assertEquals("Color must be 3 uppercase letters", exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionWhenColorHasNonLetterCharacters() {
        // Arrange
        String invalidColor = "BL1"; // Contains a number
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Sku.builder()
                        .withBrand(BRAND)
                        .withProductType(PRODUCT_TYPE)
                        .withSize(SIZE)
                        .withColor(invalidColor)
                        .withUniqueId(UNIQUE_ID)
                        .withLocation(LOCATION)
                        .build()
        );
        
        assertEquals("Color must be 3 uppercase letters", exception.getMessage());
    }
    
    private static Stream<Arguments> provideInvalidBrandValues() {
        return Stream.of(
                Arguments.of(null, "Brand must be 3 uppercase letters"),
                Arguments.of("NI", "Brand must be 3 uppercase letters"),
                Arguments.of("NIK1", "Brand must be 3 uppercase letters"),
                Arguments.of("nik", "Brand must be 3 uppercase letters")
        );
    }
    
    private static Stream<Arguments> provideInvalidProductTypeValues() {
        return Stream.of(
                Arguments.of(null, "Product type must be 4 uppercase letters"),
                Arguments.of("SHR", "Product type must be 4 uppercase letters"),
                Arguments.of("SHIRT", "Product type must be 4 uppercase letters"),
                Arguments.of("shrt", "Product type must be 4 uppercase letters")
        );
    }
    
    private static Stream<Arguments> provideInvalidSizeValues() {
        return Stream.of(
                Arguments.of(null, "Size must be 2 uppercase letters"),
                Arguments.of("L", "Size must be 2 uppercase letters"),
                Arguments.of("LGG", "Size must be 2 uppercase letters"),
                Arguments.of("lg", "Size must be 2 uppercase letters")
        );
    }
    
    private static Stream<Arguments> provideInvalidColorValues() {
        return Stream.of(
                Arguments.of(null, "Color must be 3 uppercase letters"),
                Arguments.of("BL", "Color must be 3 uppercase letters"),
                Arguments.of("BLUEE", "Color must be 3 uppercase letters"),
                Arguments.of("blue", "Color must be 3 uppercase letters")
        );
    }
    
    private static Stream<Arguments> provideInvalidUniqueIdValues() {
        return Stream.of(
                Arguments.of(null, "Unique ID must be 3 digits"),
                Arguments.of("1", "Unique ID must be 3 digits"),
                Arguments.of("0001", "Unique ID must be 3 digits"),
                Arguments.of("ABC", "Unique ID must be 3 digits")
        );
    }
    
    private static Stream<Arguments> provideInvalidLocationValues() {
        return Stream.of(
                Arguments.of(null, "Location must be 3 digits"),
                Arguments.of("1", "Location must be 3 digits"),
                Arguments.of("3511", "Location must be 3 digits"),
                Arguments.of("ABC", "Location must be 3 digits")
        );
    }
} 