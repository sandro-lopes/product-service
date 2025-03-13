package com.codingbetter.domain.catalog.product.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.codingbetter.domain.catalog.category.model.CategoryId;

@DisplayName("ProductValidations Tests")
class ProductValidationsTest {

    @Test
    @DisplayName("Should validate image successfully")
    void shouldValidateImageSuccessfully() throws MalformedURLException {
        // Arrange
        Image image = new Image(URI.create("http://example.com/image.jpg"));
        List<Image> existingImages = Collections.emptyList();

        // Act & Assert
        assertDoesNotThrow(() -> ProductValidations.validateImage(image, existingImages));
    }

    @Test
    @DisplayName("Should throw exception when image is null")
    void shouldThrowExceptionWhenImageIsNull() {
        // Arrange
        List<Image> existingImages = Collections.emptyList();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ProductValidations.validateImage(null, existingImages)
        );
        assertEquals("Image is required", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when image already exists")
    void shouldThrowExceptionWhenImageAlreadyExists() throws MalformedURLException {
        // Arrange
        Image image = new Image(URI.create("http://example.com/image.jpg"));
        List<Image> existingImages = Arrays.asList(image);

        // Act & Assert
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> ProductValidations.validateImage(image, existingImages)
        );
        assertEquals("Image already exists in the product", exception.getMessage());
    }

    @Test
    @DisplayName("Should validate specification successfully")
    void shouldValidateSpecificationSuccessfully() {
        // Arrange
        Specification specification = new Specification("Color", "Blue");
        List<Specification> existingSpecifications = Collections.emptyList();

        // Act & Assert
        assertDoesNotThrow(() -> ProductValidations.validateSpecification(specification, existingSpecifications));
    }

    @Test
    @DisplayName("Should throw exception when specification is null")
    void shouldThrowExceptionWhenSpecificationIsNull() {
        // Arrange
        List<Specification> existingSpecifications = Collections.emptyList();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ProductValidations.validateSpecification(null, existingSpecifications)
        );
        assertEquals("Specification is required", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when specification already exists")
    void shouldThrowExceptionWhenSpecificationAlreadyExists() {
        // Arrange
        Specification specification = new Specification("Color", "Blue");
        List<Specification> existingSpecifications = Arrays.asList(specification);

        // Act & Assert
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> ProductValidations.validateSpecification(specification, existingSpecifications)
        );
        assertEquals("Specification already exists in the product", exception.getMessage());
    }

    @Test
    @DisplayName("Should validate status successfully")
    void shouldValidateStatusSuccessfully() {
        // Act & Assert
        assertDoesNotThrow(() -> ProductValidations.validateStatus(ProductStatus.ACTIVE, ProductStatus.INACTIVE));
    }

    @Test
    @DisplayName("Should throw exception when status is the same")
    void shouldThrowExceptionWhenStatusIsTheSame() {
        // Act & Assert
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> ProductValidations.validateStatus(ProductStatus.ACTIVE, ProductStatus.ACTIVE)
        );
        assertEquals("Product is already active", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when product is discontinued")
    void shouldThrowExceptionWhenProductIsDiscontinued() {
        // Act & Assert
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> ProductValidations.validateStatus(ProductStatus.DISCONTINUED, ProductStatus.ACTIVE)
        );
        assertEquals("Cannot modify a discontinued product", exception.getMessage());
    }

    @Test
    @DisplayName("Should validate price successfully")
    void shouldValidatePriceSuccessfully() {
        // Arrange
        Money currentPrice = new Money(new BigDecimal("100.0"), Currency.getInstance("USD"));
        Money newPrice = new Money(new BigDecimal("150.0"), Currency.getInstance("USD"));

        // Act & Assert
        assertDoesNotThrow(() -> ProductValidations.validatePrice(currentPrice, newPrice));
    }

    @Test
    @DisplayName("Should throw exception when new price is null")
    void shouldThrowExceptionWhenNewPriceIsNull() {
        // Arrange
        Money currentPrice = new Money(new BigDecimal("100.0"), Currency.getInstance("USD"));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ProductValidations.validatePrice(currentPrice, null)
        );
        assertEquals("New price is required", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when currencies are different")
    void shouldThrowExceptionWhenCurrenciesAreDifferent() {
        // Arrange
        Money currentPrice = new Money(new BigDecimal("100.0"), Currency.getInstance("USD"));
        Money newPrice = new Money(new BigDecimal("150.0"), Currency.getInstance("EUR"));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ProductValidations.validatePrice(currentPrice, newPrice)
        );
        assertEquals("New price must have the same currency as the old price", exception.getMessage());
    }

    @Test
    @DisplayName("Should validate product creation successfully")
    void shouldValidateProductCreationSuccessfully() {
        // Arrange
        ProductId id = new ProductId();
        Sku sku = Sku.fromValue("NIKSHRTLGBLU001351");
        String name = "Nike T-Shirt";
        String description = "A comfortable and stylish t-shirt";
        Money price = new Money(new BigDecimal("50.0"), Currency.getInstance("USD"));
        CategoryId categoryId = new CategoryId(UUID.randomUUID());
        LocalDateTime createdAt = LocalDateTime.now();

        // Act & Assert
        assertDoesNotThrow(() -> ProductValidations.validateProductCreation(id, sku, name, description, price, ProductStatus.ACTIVE, categoryId, createdAt));
    }

    @Test
    @DisplayName("Should throw exception when SKU is null")
    void shouldThrowExceptionWhenSkuIsNull() {
        // Arrange
        ProductId id = new ProductId();
        String name = "Nike T-Shirt";
        String description = "A comfortable and stylish t-shirt";
        Money price = new Money(new BigDecimal("50.0"), Currency.getInstance("USD"));
        CategoryId categoryId = new CategoryId(UUID.randomUUID());
        LocalDateTime createdAt = LocalDateTime.now();

        // Act & Assert
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
            () -> ProductValidations.validateProductCreation(id, null, name, description, price, ProductStatus.ACTIVE, categoryId, createdAt)
        );
        assertEquals("SKU is required", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when name is invalid")
    void shouldThrowExceptionWhenNameIsInvalid() {
        // Arrange
        ProductId id = new ProductId();
        Sku sku = Sku.fromValue("NIKSHRTLGBLU001351");
        String description = "A comfortable and stylish t-shirt";
        Money price = new Money(new BigDecimal("50.0"), Currency.getInstance("USD"));
        CategoryId categoryId = new CategoryId(UUID.randomUUID());
        LocalDateTime createdAt = LocalDateTime.now();

        // Act & Assert
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
            () -> ProductValidations.validateProductCreation(id, sku, null, description, price, ProductStatus.ACTIVE, categoryId, createdAt)
        );
        assertEquals("Name must have at least 3 characters", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when description is invalid")
    void shouldThrowExceptionWhenDescriptionIsInvalid() {
        // Arrange
        ProductId id = new ProductId();
        Sku sku = Sku.fromValue("NIKSHRTLGBLU001351");
        String name = "Nike T-Shirt";
        Money price = new Money(new BigDecimal("50.0"), Currency.getInstance("USD"));
        CategoryId categoryId = new CategoryId(UUID.randomUUID());
        LocalDateTime createdAt = LocalDateTime.now();

        // Act & Assert
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
            () -> ProductValidations.validateProductCreation(id, sku, name, null, price, ProductStatus.ACTIVE, categoryId, createdAt)
        );
        assertEquals("Description must have at least 10 characters", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when price is null")
    void shouldThrowExceptionWhenPriceIsNull() {
        // Arrange
        ProductId id = new ProductId();
        Sku sku = Sku.fromValue("NIKSHRTLGBLU001351");
        String name = "Nike T-Shirt";
        String description = "A comfortable and stylish t-shirt";
        CategoryId categoryId = new CategoryId(UUID.randomUUID());
        LocalDateTime createdAt = LocalDateTime.now();

        // Act & Assert
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
            () -> ProductValidations.validateProductCreation(id, sku, name, description, null, ProductStatus.ACTIVE, categoryId, createdAt)
        );
        assertEquals("Price is required", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when categoryId is null")
    void shouldThrowExceptionWhenCategoryIdIsNull() {
        // Arrange
        ProductId id = new ProductId();
        Sku sku = Sku.fromValue("NIKSHRTLGBLU001351");
        String name = "Nike T-Shirt";
        String description = "A comfortable and stylish t-shirt";
        Money price = new Money(new BigDecimal("50.0"), Currency.getInstance("USD"));
        LocalDateTime createdAt = LocalDateTime.now();

        // Act & Assert
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
            () -> ProductValidations.validateProductCreation(id, sku, name, description, price, ProductStatus.ACTIVE, null, createdAt)
        );
        assertEquals("CategoryId is required", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when createdAt is null")
    void shouldThrowExceptionWhenCreatedAtIsNull() {
        // Arrange
        ProductId id = new ProductId();
        Sku sku = Sku.fromValue("NIKSHRTLGBLU001351");
        String name = "Nike T-Shirt";
        String description = "A comfortable and stylish t-shirt";
        Money price = new Money(new BigDecimal("50.0"), Currency.getInstance("USD"));
        CategoryId categoryId = new CategoryId(UUID.randomUUID());

        // Act & Assert
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
            () -> ProductValidations.validateProductCreation(id, sku, name, description, price, ProductStatus.ACTIVE, categoryId, null)
        );
        assertEquals("Created date is required", exception.getMessage());
    }

    @Test
    @DisplayName("Should validate image when existingImages is null")
    void shouldValidateImageWhenExistingImagesIsNull() throws MalformedURLException {
        // Arrange
        Image image = new Image(URI.create("http://example.com/image.jpg"));

        // Act & Assert
        assertDoesNotThrow(() -> ProductValidations.validateImage(image, null));
    }

    @Test
    @DisplayName("Should validate specification when existingSpecifications is null")
    void shouldValidateSpecificationWhenExistingSpecificationsIsNull() {
        // Arrange
        Specification specification = new Specification("Color", "Blue");

        // Act & Assert
        assertDoesNotThrow(() -> ProductValidations.validateSpecification(specification, null));
    }

    @Test
    @DisplayName("Should validate product creation with minimum name length")
    void shouldValidateProductCreationWithMinimumNameLength() {
        // Arrange
        ProductId id = new ProductId(UUID.randomUUID());
        Sku sku = Sku.fromValue("NIKSHRTLGBLU001351");
        String name = "abc";
        String description = "This is a test description";
        Money price = new Money(BigDecimal.TEN, Currency.getInstance("USD"));
        CategoryId categoryId = new CategoryId(UUID.randomUUID());
        LocalDateTime createdAt = LocalDateTime.now();

        // Act & Assert
        assertDoesNotThrow(() -> ProductValidations.validateProductCreation(id, sku, name, description, price, ProductStatus.ACTIVE, categoryId, createdAt));
    }

    @Test
    @DisplayName("Should validate product creation with minimum description length")
    void shouldValidateProductCreationWithMinimumDescriptionLength() {
        // Arrange
        ProductId id = new ProductId(UUID.randomUUID());
        Sku sku = Sku.fromValue("NIKSHRTLGBLU001351");
        String name = "Test Product";
        String description = "1234567890";
        Money price = new Money(BigDecimal.TEN, Currency.getInstance("USD"));
        CategoryId categoryId = new CategoryId(UUID.randomUUID());
        LocalDateTime createdAt = LocalDateTime.now();

        // Act & Assert
        assertDoesNotThrow(() -> ProductValidations.validateProductCreation(id, sku, name, description, price, ProductStatus.ACTIVE, categoryId, createdAt));
    }
} 