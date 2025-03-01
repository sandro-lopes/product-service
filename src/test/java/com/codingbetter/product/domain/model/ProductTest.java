package com.codingbetter.product.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URL;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private ProductId productId;
    private Sku sku;
    private String name;
    private String description;
    private Money price;
    private CategoryId categoryId;
    private Product.ProductBuilder productBuilder;

    @BeforeEach
    void setUp() throws Exception {
        productId = new ProductId();
        sku = createValidSku();
        name = "Test Product";
        description = "Test Description";
        price = new Money(new BigDecimal("99.99"), "USD");
        categoryId = new CategoryId(UUID.randomUUID());
        
        productBuilder = new Product.ProductBuilder()
                .builder(productId, sku, name, description, price, categoryId);
    }

    @Test
    void shouldCreateProductSuccessfully() {
        // Act
        Product product = productBuilder.build();
        
        // Assert
        assertEquals(productId, product.getId());
        assertEquals(sku, product.getSku());
        assertEquals(name, product.getName());
        assertEquals(description, product.getDescription());
        assertEquals(price, product.getPrice());
        assertEquals(categoryId, product.getCategoryId());
        assertEquals(ProductStatus.DRAFT, product.getStatus());
        assertNotNull(product.getCreatedAt());
        assertNull(product.getUpdatedAt());
        assertNull(product.getImages());
        assertNull(product.getSpecifications());
    }
    
    @Test
    void shouldCreateProductWithDefaultIdWhenIdIsNull() {
        // Arrange
        productBuilder = new Product.ProductBuilder()
                .builder(null, sku, name, description, price, categoryId);
        
        // Act
        Product product = productBuilder.build();
        
        // Assert
        assertNotNull(product.getId());
    }
    
    @Test
    void shouldAddImageToProduct() throws Exception {
        // Arrange
        Product product = productBuilder.build();
        URL imageUrl = URI.create("https://example.com/image.jpg").toURL();
        Image image = new Image(imageUrl);
        
        // Act
        product.addImage(image);
        
        // Assert
        assertNotNull(product.getImages());
        assertEquals(1, product.getImages().size());
        assertEquals(image, product.getImages().get(0));
        assertNotNull(product.getUpdatedAt());
    }
    
    @Test
    void shouldAddMultipleImagesToProduct() throws Exception {
        // Arrange
        Product product = productBuilder.build();
        URL imageUrl1 = URI.create("https://example.com/image1.jpg").toURL();
        URL imageUrl2 = URI.create("https://example.com/image2.jpg").toURL();
        Image image1 = new Image(imageUrl1);
        Image image2 = new Image(imageUrl2);
        
        // Act
        product.addImage(image1);
        product.addImage(image2);
        
        // Assert
        assertNotNull(product.getImages());
        assertEquals(2, product.getImages().size());
        assertEquals(image1, product.getImages().get(0));
        assertEquals(image2, product.getImages().get(1));
    }
    
    @Test
    void shouldAddSpecificationToProduct() {
        // Arrange
        Product product = productBuilder.build();
        Specification specification = new Specification("Color", "Blue");
        
        // Act
        product.addSpecification(specification);
        
        // Assert
        assertNotNull(product.getSpecifications());
        assertEquals(1, product.getSpecifications().size());
        assertEquals(specification, product.getSpecifications().get(0));
        assertNotNull(product.getUpdatedAt());
    }
    
    @Test
    void shouldAddMultipleSpecificationsToProduct() {
        // Arrange
        Product product = productBuilder.build();
        Specification specification1 = new Specification("Color", "Blue");
        Specification specification2 = new Specification("Size", "Large");
        
        // Act
        product.addSpecification(specification1);
        product.addSpecification(specification2);
        
        // Assert
        assertNotNull(product.getSpecifications());
        assertEquals(2, product.getSpecifications().size());
        assertEquals(specification1, product.getSpecifications().get(0));
        assertEquals(specification2, product.getSpecifications().get(1));
    }
    
    @Test
    void shouldUpdateProductPrice() {
        // Arrange
        Product product = productBuilder.build();
        Money newPrice = new Money(new BigDecimal("149.99"), "USD");
        
        // Act
        product.updatePrice(newPrice);
        
        // Assert
        assertEquals(newPrice, product.getPrice());
        assertNotNull(product.getUpdatedAt());
    }
    
    @Test
    void shouldActivateProduct() {
        // Arrange
        Product product = productBuilder.build();
        
        // Act
        product.activate();
        
        // Assert
        assertEquals(ProductStatus.ACTIVE, product.getStatus());
        assertNotNull(product.getUpdatedAt());
    }
    
    @Test
    void shouldDeactivateProduct() {
        // Arrange
        Product product = productBuilder.build();
        
        // Act
        product.deactivate();
        
        // Assert
        assertEquals(ProductStatus.INACTIVE, product.getStatus());
        assertNotNull(product.getUpdatedAt());
    }
    
    @Test
    void shouldDiscontinueProduct() {
        // Arrange
        Product product = productBuilder.build();
        
        // Act
        product.discontinue();
        
        // Assert
        assertEquals(ProductStatus.DISCONTINUED, product.getStatus());
        assertNotNull(product.getUpdatedAt());
    }
    
    @Test
    void shouldAddImageToProductBuilder() throws Exception {
        // Arrange
        URL imageUrl = URI.create("https://example.com/image.jpg").toURL();
        Image image = new Image(imageUrl);
        
        // Act
        Product product = productBuilder
                .withImage(image)
                .build();
        
        // Assert
        assertNotNull(product.getImages());
        assertEquals(1, product.getImages().size());
        assertEquals(image, product.getImages().get(0));
    }
    
    @Test
    void shouldAddMultipleImagesToProductBuilder() throws Exception {
        // Arrange
        URL imageUrl1 = URI.create("https://example.com/image1.jpg").toURL();
        URL imageUrl2 = URI.create("https://example.com/image2.jpg").toURL();
        Image image1 = new Image(imageUrl1);
        Image image2 = new Image(imageUrl2);
        
        // Act
        Product product = productBuilder
                .withImage(image1)
                .withImage(image2)
                .build();
        
        // Assert
        assertNotNull(product.getImages());
        assertEquals(2, product.getImages().size());
        assertEquals(image1, product.getImages().get(0));
        assertEquals(image2, product.getImages().get(1));
    }
    
    @Test
    void shouldAddSpecificationToProductBuilder() {
        // Arrange
        Specification specification = new Specification("Color", "Blue");
        
        // Act
        Product product = productBuilder
                .withSpecification(specification)
                .build();
        
        // Assert
        assertNotNull(product.getSpecifications());
        assertEquals(1, product.getSpecifications().size());
        assertEquals(specification, product.getSpecifications().get(0));
    }
    
    @Test
    void shouldAddMultipleSpecificationsToProductBuilder() {
        // Arrange
        Specification specification1 = new Specification("Color", "Blue");
        Specification specification2 = new Specification("Size", "Large");
        
        // Act
        Product product = productBuilder
                .withSpecification(specification1)
                .withSpecification(specification2)
                .build();
        
        // Assert
        assertNotNull(product.getSpecifications());
        assertEquals(2, product.getSpecifications().size());
        assertEquals(specification1, product.getSpecifications().get(0));
        assertEquals(specification2, product.getSpecifications().get(1));
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidProductBuilderScenarios")
    void shouldThrowExceptionWhenBuildingWithInvalidData(
            Product.ProductBuilder builder, String expectedErrorMessage) {
        // Act & Assert
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> builder.build()
        );
        
        assertEquals(expectedErrorMessage, exception.getMessage());
    }
    
    private static Stream<Arguments> provideInvalidProductBuilderScenarios() {
        return Stream.of(
                Arguments.of(
                        new Product.ProductBuilder()
                                .builder(new ProductId(), null, "Name", "Description", 
                                        new Money(new BigDecimal("99.99"), "USD"), 
                                        new CategoryId(UUID.randomUUID())),
                        "SKU is required"
                ),
                Arguments.of(
                        new Product.ProductBuilder()
                                .builder(new ProductId(), createValidSku(), null, "Description", 
                                        new Money(new BigDecimal("99.99"), "USD"), 
                                        new CategoryId(UUID.randomUUID())),
                        "Name is required"
                ),
                Arguments.of(
                        new Product.ProductBuilder()
                                .builder(new ProductId(), createValidSku(), "Name", null, 
                                        new Money(new BigDecimal("99.99"), "USD"), 
                                        new CategoryId(UUID.randomUUID())),
                        "Description is required"
                ),
                Arguments.of(
                        new Product.ProductBuilder()
                                .builder(new ProductId(), createValidSku(), "Name", "Description", 
                                        null, 
                                        new CategoryId(UUID.randomUUID())),
                        "Price is required"
                ),
                Arguments.of(
                        new Product.ProductBuilder()
                                .builder(new ProductId(), createValidSku(), "Name", "Description", 
                                        new Money(new BigDecimal("99.99"), "USD"), 
                                        null),
                        "CategoryId is required"
                )
        );
    }
    
    private static Sku createValidSku() {
        return Sku.builder()
                .withBrand("NIK")
                .withProductType("SHRT")
                .withSize("LG")
                .withColor("BLU")
                .withUniqueId("001")
                .withLocation("351")
                .build();
    }
} 