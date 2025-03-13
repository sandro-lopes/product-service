package com.codingbetter.domain.catalog.product.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.codingbetter.domain.catalog.category.model.CategoryId;
import com.codingbetter.domain.catalog.product.event.ProductActivatedEvent;
import com.codingbetter.domain.catalog.product.event.ProductDeactivatedEvent;
import com.codingbetter.domain.catalog.product.event.ProductDiscontinuedEvent;
import com.codingbetter.domain.catalog.product.event.ProductPriceChangedEvent;

@DisplayName("Product Tests")
class ProductTest {

    private static final String PRODUCT_NAME = "Test Product";
    private static final String PRODUCT_DESCRIPTION = "Test Description";
    private static final String IMAGE_URL = "https://example.com/image.jpg";
    private static final String SPECIFICATION_NAME = "Color";
    private static final String SPECIFICATION_VALUE = "Blue";

    private ProductId productId;
    private CategoryId categoryId;
    private Sku sku;
    private Money price;
    private Specification specification;
    private Image image;
    private Set<Specification> specifications;
    private Set<Image> images;
    private Set<Sku> skus;

    @BeforeEach
    void setUp() throws Exception {
        productId = new ProductId();
        categoryId = new CategoryId(UUID.randomUUID());
        sku = Sku.builder()
            .withBrand("ABC")
            .withProductType("TEST")
            .withSize("XL")
            .withColor("RED")
            .withUniqueId("123")
            .withLocation("456")
            .build();
        price = new Money(new BigDecimal("100.00"), Currency.getInstance("BRL"));
        specification = new Specification(SPECIFICATION_NAME, SPECIFICATION_VALUE);
        image = new Image(URI.create(IMAGE_URL));

        specifications = new HashSet<>();
        specifications.add(specification);

        images = new HashSet<>();
        images.add(image);

        skus = new HashSet<>();
        skus.add(sku);
    }

    @Nested
    @DisplayName("Creation Tests")
    class CreationTests {
        @Test
        @DisplayName("Should create product successfully")
        void shouldCreateProductSuccessfully() {
            // Act
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            // Assert
            assertNotNull(product);
            assertEquals(productId, product.getId());
            assertEquals(categoryId, product.getCategoryId());
            assertEquals(PRODUCT_NAME, product.getName());
            assertEquals(PRODUCT_DESCRIPTION, product.getDescription());
            assertEquals(price, product.getPrice());
            assertEquals(ProductStatus.DRAFT, product.getStatus());
            assertNotNull(product.getCreatedAt());
            assertNull(product.getUpdatedAt());
        }

        @Test
        @DisplayName("Should create product successfully with a generated id")
        void shouldCreateProductWithGeneratedIdWhenProductIdIsNull() {
            // Act
            Product product = new Product.ProductBuilder()
                .builder(null, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            // Assert
            assertNotNull(product);
            assertNotNull(product.getId());
            assertEquals(categoryId, product.getCategoryId());
            assertEquals(PRODUCT_NAME, product.getName());
            assertEquals(PRODUCT_DESCRIPTION, product.getDescription());
            assertEquals(price, product.getPrice());
            assertEquals(ProductStatus.DRAFT, product.getStatus());
        }

        @Test
        @DisplayName("Should throw exception when category id is null")
        void shouldThrowExceptionWhenCategoryIdIsNull() {
            // Act & Assert
            assertThrows(IllegalStateException.class, () -> {
                new Product.ProductBuilder()
                    .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, null)
                    .build();
            });
        }

        @Test
        @DisplayName("Should throw exception when name is null")
        void shouldThrowExceptionWhenNameIsNull() {
            // Act & Assert
            assertThrows(IllegalStateException.class, () -> {
                new Product.ProductBuilder()
                    .builder(productId, sku, null, PRODUCT_DESCRIPTION, price, categoryId)
                    .build();
            });
        }

        @Test
        @DisplayName("Should throw exception when description is null")
        void shouldThrowExceptionWhenDescriptionIsNull() {
            // Act & Assert
            assertThrows(IllegalStateException.class, () -> {
                new Product.ProductBuilder()
                    .builder(productId, sku, PRODUCT_NAME, null, price, categoryId)
                    .build();
            });
        }

        @Test
        @DisplayName("Should throw exception when price is null")
        void shouldThrowExceptionWhenPriceIsNull() {
            // Act & Assert
            assertThrows(IllegalStateException.class, () -> {
                new Product.ProductBuilder()
                    .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, null, categoryId)
                    .build();
            });
        }

        @Test
        @DisplayName("Should create product with valid SKU format")
        void shouldCreateProductWithValidSkuFormat() {
            // Arrange
            Sku validSku = Sku.builder()
                .withBrand("ABC")
                .withProductType("TEST")
                .withSize("XL")
                .withColor("RED")
                .withUniqueId("123")
                .withLocation("456")
                .build();

            // Act
            Product product = new Product.ProductBuilder()
                .builder(productId, validSku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            // Assert
            assertNotNull(product);
            assertEquals(validSku, product.getSku());
        }

        @Test
        @DisplayName("Should throw exception when SKU is null")
        void shouldThrowExceptionWhenSkuIsNull() {
            // Act & Assert
            assertThrows(IllegalStateException.class, () -> {
                new Product.ProductBuilder()
                    .builder(productId, null, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                    .build();
            });
        }

        @Test
        @DisplayName("Should throw exception when name is empty")
        void shouldThrowExceptionWhenNameIsEmpty() {
            // Act & Assert
            assertThrows(IllegalStateException.class, () -> {
                new Product.ProductBuilder()
                    .builder(productId, sku, "", PRODUCT_DESCRIPTION, price, categoryId)
                    .build();
            });
        }

        @Test
        @DisplayName("Should throw exception when name is too short")
        void shouldThrowExceptionWhenNameIsTooShort() {
            // Act & Assert
            assertThrows(IllegalStateException.class, () -> {
                new Product.ProductBuilder()
                    .builder(productId, sku, "AB", PRODUCT_DESCRIPTION, price, categoryId)
                    .build();
            });
        }

        @Test
        @DisplayName("Should throw exception when description is empty")
        void shouldThrowExceptionWhenDescriptionIsEmpty() {
            // Act & Assert
            assertThrows(IllegalStateException.class, () -> {
                new Product.ProductBuilder()
                    .builder(productId, sku, PRODUCT_NAME, "", price, categoryId)
                    .build();
            });
        }

        @Test
        @DisplayName("Should throw exception when description is too short")
        void shouldThrowExceptionWhenDescriptionIsTooShort() {
            // Act & Assert
            assertThrows(IllegalStateException.class, () -> {
                new Product.ProductBuilder()
                    .builder(productId, sku, PRODUCT_NAME, "Too short", price, categoryId)
                    .build();
            });
        }

        @Test
        @DisplayName("Should throw exception when name contains only whitespace")
        void shouldThrowExceptionWhenNameContainsOnlyWhitespace() {
            // Act & Assert
            assertThrows(IllegalStateException.class, () -> {
                new Product.ProductBuilder()
                    .builder(productId, sku, "   ", PRODUCT_DESCRIPTION, price, categoryId)
                    .build();
            });
        }

        @Test
        @DisplayName("Should throw exception when description contains only whitespace")
        void shouldThrowExceptionWhenDescriptionContainsOnlyWhitespace() {
            // Act & Assert
            assertThrows(IllegalStateException.class, () -> {
                new Product.ProductBuilder()
                    .builder(productId, sku, PRODUCT_NAME, "   ", price, categoryId)
                    .build();
            });
        }
    }

    @Nested
    @DisplayName("Status Tests")
    class StatusTests {
        @Test
        @DisplayName("Should activate product successfully")
        void shouldActivateProductSuccessfully() {
            // Arrange
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            // Act
            product.activate();

            // Assert
            assertEquals(ProductStatus.ACTIVE, product.getStatus());
            assertNotNull(product.getUpdatedAt());
        }

        @Test
        @DisplayName("Should throw exception when activating an already active product")
        void shouldThrowExceptionWhenActivatingActiveProduct() {
            // Arrange
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();
            product.activate();

            // Act & Assert
            assertThrows(IllegalStateException.class, () -> {
                product.activate();
            });
        }

        @Test
        @DisplayName("Should throw exception when activating a discontinued product")
        void shouldThrowExceptionWhenActivatingDiscontinuedProduct() {
            // Arrange
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();
            product.discontinue();

            // Act & Assert
            assertThrows(IllegalStateException.class, () -> {
                product.activate();
            });
        }

        @Test
        @DisplayName("Should deactivate product successfully")
        void shouldDeactivateProductSuccessfully() {
            // Arrange
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();
            product.activate();

            // Act
            product.deactivate();

            // Assert
            assertEquals(ProductStatus.INACTIVE, product.getStatus());
            assertNotNull(product.getUpdatedAt());
        }

        @Test
        @DisplayName("Should throw exception when deactivating an already inactive product")
        void shouldThrowExceptionWhenDeactivatingInactiveProduct() {
            // Arrange
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();
            product.deactivate();

            // Act & Assert
            assertThrows(IllegalStateException.class, () -> {
                product.deactivate();
            });
        }

        @Test
        @DisplayName("Should throw exception when deactivating a discontinued product")
        void shouldThrowExceptionWhenDeactivatingDiscontinuedProduct() {
            // Arrange
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();
            product.discontinue();

            // Act & Assert
            assertThrows(IllegalStateException.class, () -> {
                product.deactivate();
            });
        }

        @Test
        @DisplayName("Should discontinue product successfully")
        void shouldDiscontinueProductSuccessfully() {
            // Arrange
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            // Act
            product.discontinue();

            // Assert
            assertEquals(ProductStatus.DISCONTINUED, product.getStatus());
            assertNotNull(product.getUpdatedAt());
        }

        @Test
        @DisplayName("Should throw exception when discontinuing an already discontinued product")
        void shouldThrowExceptionWhenDiscontinuingDiscontinuedProduct() {
            // Arrange
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();
            product.discontinue();

            // Act & Assert
            assertThrows(IllegalStateException.class, () -> {
                product.discontinue();
            });
        }
    }

    @Nested
    @DisplayName("Price Tests")
    class PriceTests {
        @Test
        @DisplayName("Should update price successfully")
        void shouldUpdatePriceSuccessfully() {
            // Arrange
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            Money newPrice = new Money(new BigDecimal("150.00"), Currency.getInstance("BRL"));

            // Act
            product.updatePrice(newPrice);

            // Assert
            assertEquals(newPrice, product.getPrice());
            assertNotNull(product.getUpdatedAt());
        }

        @Test
        @DisplayName("Should throw exception when updating price with null")
        void shouldThrowExceptionWhenUpdatingPriceWithNull() {
            // Arrange
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                product.updatePrice(null);
            });
        }

        @Test
        @DisplayName("Should throw exception when updating price with different currency")
        void shouldThrowExceptionWhenUpdatingPriceWithDifferentCurrency() {
            // Arrange
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            Money newPrice = new Money(new BigDecimal("150.00"), Currency.getInstance("USD"));

            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                product.updatePrice(newPrice);
            });
        }
    }

    @Nested
    @DisplayName("Image Tests")
    class ImageTests {
        @Test
        @DisplayName("Should add image successfully")
        void shouldAddImageSuccessfully() throws Exception {
            // Arrange
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            Image newImage = new Image(URI.create(IMAGE_URL));

            // Act
            product.addImage(newImage);

            // Assert
            assertTrue(product.getImages().contains(newImage));
            assertNotNull(product.getUpdatedAt());
        }

        @Test
        @DisplayName("Should throw exception when adding null image")
        void shouldThrowExceptionWhenAddingNullImage() {
            // Arrange
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                product.addImage(null);
            });
        }

        @Test
        @DisplayName("Should throw exception when adding duplicate image")
        void shouldThrowExceptionWhenAddingDuplicateImage() throws Exception {
            // Arrange
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            Image newImage = new Image(URI.create(IMAGE_URL));
            product.addImage(newImage);

            // Act & Assert
            assertThrows(IllegalStateException.class, () -> {
                product.addImage(newImage);
            });
        }
    }

    @Nested
    @DisplayName("Specification Tests")
    class SpecificationTests {
        @Test
        @DisplayName("Should add specification successfully")
        void shouldAddSpecificationSuccessfully() {
            // Arrange
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            Specification newSpecification = new Specification("Size", "Large");

            // Act
            product.addSpecification(newSpecification);

            // Assert
            assertTrue(product.getSpecifications().contains(newSpecification));
            assertNotNull(product.getUpdatedAt());
        }

        @Test
        @DisplayName("Should throw exception when adding null specification")
        void shouldThrowExceptionWhenAddingNullSpecification() {
            // Arrange
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                product.addSpecification(null);
            });
        }

        @Test
        @DisplayName("Should throw exception when adding duplicate specification")
        void shouldThrowExceptionWhenAddingDuplicateSpecification() {
            // Arrange
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            Specification newSpecification = new Specification("Size", "Large");
            product.addSpecification(newSpecification);

            // Act & Assert
            assertThrows(IllegalStateException.class, () -> {
                product.addSpecification(newSpecification);
            });
        }
    }

    @Nested
    @DisplayName("Date Tests")
    class DateTests {
        @Test
        @DisplayName("Should create product with valid dates")
        void shouldCreateProductWithValidDates() {
            // Act
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            // Assert
            assertNotNull(product.getCreatedAt());
            assertNull(product.getUpdatedAt());
        }

        @Test
        @DisplayName("Should update dates when modifying product")
        void shouldUpdateDatesWhenModifyingProduct() throws Exception {
            // Arrange
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            LocalDateTime createdAt = product.getCreatedAt();
            Thread.sleep(100); // Pequena pausa para garantir que as datas sejam diferentes

            // Act
            product.activate();
            LocalDateTime firstUpdate = product.getUpdatedAt();
            assertTrue(firstUpdate.isAfter(createdAt));

            Thread.sleep(100);
            product.addImage(new Image(URI.create(IMAGE_URL)));
            LocalDateTime secondUpdate = product.getUpdatedAt();
            assertTrue(secondUpdate.isAfter(firstUpdate));

            Thread.sleep(100);
            product.addSpecification(new Specification("Size", "Large"));
            LocalDateTime thirdUpdate = product.getUpdatedAt();
            assertTrue(thirdUpdate.isAfter(secondUpdate));
        }
    }

    @Nested
    @DisplayName("Domain Events Tests")
    class DomainEventsTests {
        @Test
        @DisplayName("Should raise ProductPriceChangedEvent when price is updated")
        void shouldRaiseProductPriceChangedEventWhenPriceIsUpdated() {
            // Arrange
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            Money newPrice = new Money(new BigDecimal("150.00"), Currency.getInstance("BRL"));

            // Act
            product.updatePrice(newPrice);

            // Assert
            assertEquals(1, product.getDomainEvents().size());
            assertTrue(product.getDomainEvents().get(0) instanceof ProductPriceChangedEvent);
            ProductPriceChangedEvent event = (ProductPriceChangedEvent) product.getDomainEvents().get(0);
            assertEquals(productId, event.getProductId());
            assertEquals(price, event.getOldPrice());
            assertEquals(newPrice, event.getNewPrice());
        }

        @Test
        @DisplayName("Should raise ProductActivatedEvent when product is activated")
        void shouldRaiseProductActivatedEventWhenProductIsActivated() {
            // Arrange
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            // Act
            product.activate();

            // Assert
            assertEquals(1, product.getDomainEvents().size());
            assertTrue(product.getDomainEvents().get(0) instanceof ProductActivatedEvent);
            ProductActivatedEvent event = (ProductActivatedEvent) product.getDomainEvents().get(0);
            assertEquals(productId, event.getProductId());
        }

        @Test
        @DisplayName("Should raise ProductDeactivatedEvent when product is deactivated")
        void shouldRaiseProductDeactivatedEventWhenProductIsDeactivated() {
            // Arrange
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();
            product.activate();

            // Act
            product.deactivate();

            // Assert
            assertEquals(2, product.getDomainEvents().size());
            assertTrue(product.getDomainEvents().get(1) instanceof ProductDeactivatedEvent);
            ProductDeactivatedEvent event = (ProductDeactivatedEvent) product.getDomainEvents().get(1);
            assertEquals(productId, event.getProductId());
        }

        @Test
        @DisplayName("Should raise ProductDiscontinuedEvent when product is discontinued")
        void shouldRaiseProductDiscontinuedEventWhenProductIsDiscontinued() {
            // Arrange
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            // Act
            product.discontinue();

            // Assert
            assertEquals(1, product.getDomainEvents().size());
            assertTrue(product.getDomainEvents().get(0) instanceof ProductDiscontinuedEvent);
            ProductDiscontinuedEvent event = (ProductDiscontinuedEvent) product.getDomainEvents().get(0);
            assertEquals(productId, event.getProductId());
        }
    }

    @Nested
    @DisplayName("Collections Tests")
    class CollectionsTests {
        @Test
        @DisplayName("Should initialize empty collections when creating product")
        void shouldInitializeEmptyCollectionsWhenCreatingProduct() {
            // Act
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            // Assert
            assertNotNull(product.getImages());
            assertTrue(product.getImages().isEmpty());
            assertNotNull(product.getSpecifications());
            assertTrue(product.getSpecifications().isEmpty());
        }

        @Test
        @DisplayName("Should add multiple images successfully")
        void shouldAddMultipleImagesSuccessfully() throws Exception {
            // Arrange
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            Image image1 = new Image(URI.create(IMAGE_URL));
            Image image2 = new Image(URI.create("https://example.com/image2.jpg"));

            // Act
            product.addImage(image1);
            product.addImage(image2);

            // Assert
            assertEquals(2, product.getImages().size());
            assertTrue(product.getImages().contains(image1));
            assertTrue(product.getImages().contains(image2));
        }

        @Test
        @DisplayName("Should add multiple specifications successfully")
        void shouldAddMultipleSpecificationsSuccessfully() {
            // Arrange
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            Specification spec1 = new Specification("Color", "Blue");
            Specification spec2 = new Specification("Size", "Large");

            // Act
            product.addSpecification(spec1);
            product.addSpecification(spec2);

            // Assert
            assertEquals(2, product.getSpecifications().size());
            assertTrue(product.getSpecifications().contains(spec1));
            assertTrue(product.getSpecifications().contains(spec2));
        }

        @Test
        @DisplayName("Should maintain collection order when adding items")
        void shouldMaintainCollectionOrderWhenAddingItems() throws Exception {
            // Arrange
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            Image image1 = new Image(URI.create(IMAGE_URL));
            Image image2 = new Image(URI.create("https://example.com/image2.jpg"));
            Image image3 = new Image(URI.create("https://example.com/image3.jpg"));

            // Act
            product.addImage(image1);
            product.addImage(image2);
            product.addImage(image3);

            // Assert
            List<Image> images = new ArrayList<>(product.getImages());
            assertEquals(image1, images.get(0));
            assertEquals(image2, images.get(1));
            assertEquals(image3, images.get(2));
        }
    }

    @Nested
    @DisplayName("Equals and HashCode Tests")
    class EqualsAndHashCodeTests {
        @Test
        @DisplayName("Should return true when comparing same product")
        void shouldReturnTrueWhenComparingSameProduct() {
            // Arrange
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            // Act & Assert
            assertTrue(product.equals(product));
        }

        @Test
        @DisplayName("Should return false when comparing with null")
        void shouldReturnFalseWhenComparingWithNull() {
            // Arrange
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            // Act & Assert
            assertFalse(product.equals(null));
        }

        @Test
        @DisplayName("Should return false when comparing with different class")
        void shouldReturnFalseWhenComparingWithDifferentClass() {
            // Arrange
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            // Act & Assert
            assertFalse(product.equals(new Object()));
        }

        @Test
        @DisplayName("Should return true when comparing products with same id")
        void shouldReturnTrueWhenComparingProductsWithSameId() {
            // Arrange
            Product product1 = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            Product product2 = new Product.ProductBuilder()
                .builder(productId, sku, "Different Name", "Different Description", price, categoryId)
                .build();

            // Act & Assert
            assertTrue(product1.equals(product2));
        }

        @Test
        @DisplayName("Should return false when comparing products with different ids")
        void shouldReturnFalseWhenComparingProductsWithDifferentIds() {
            // Arrange
            Product product1 = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            Product product2 = new Product.ProductBuilder()
                .builder(new ProductId(), sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            // Act & Assert
            assertFalse(product1.equals(product2));
        }

        @Test
        @DisplayName("Should return same hashCode for equal products")
        void shouldReturnSameHashCodeForEqualProducts() {
            // Arrange
            Product product1 = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .build();

            Product product2 = new Product.ProductBuilder()
                .builder(productId, sku, "Different Name", "Different Description", price, categoryId)
                .build();

            // Act & Assert
            assertEquals(product1.hashCode(), product2.hashCode());
        }
    }

    @Nested
    @DisplayName("ProductBuilder Tests")
    class ProductBuilderTests {
        @Test
        @DisplayName("Should create product with image using builder")
        void shouldCreateProductWithImageUsingBuilder() throws Exception {
            // Arrange
            Image image = new Image(URI.create(IMAGE_URL));

            // Act
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .withImages(Collections.singletonList(image))
                .build();

            // Assert
            assertNotNull(product);
            assertEquals(1, product.getImages().size());
            assertTrue(product.getImages().contains(image));
        }

        @Test
        @DisplayName("Should create product with multiple images using builder")
        void shouldCreateProductWithMultipleImagesUsingBuilder() throws Exception {
            // Arrange
            Image image1 = new Image(URI.create(IMAGE_URL));
            Image image2 = new Image(URI.create("https://example.com/image2.jpg"));

            // Act
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .withImages(List.of(image1, image2))
                .build();

            // Assert
            assertNotNull(product);
            assertEquals(2, product.getImages().size());
            assertTrue(product.getImages().contains(image1));
            assertTrue(product.getImages().contains(image2));
        }

        @Test
        @DisplayName("Should create product with specification using builder")
        void shouldCreateProductWithSpecificationUsingBuilder() {
            // Arrange
            Specification specification = new Specification(SPECIFICATION_NAME, SPECIFICATION_VALUE);

            // Act
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .withSpecifications(Collections.singletonList(specification))
                .build();

            // Assert
            assertNotNull(product);
            assertEquals(1, product.getSpecifications().size());
            assertTrue(product.getSpecifications().contains(specification));
        }

        @Test
        @DisplayName("Should create product with multiple specifications using builder")
        void shouldCreateProductWithMultipleSpecificationsUsingBuilder() {
            // Arrange
            Specification spec1 = new Specification("Color", "Blue");
            Specification spec2 = new Specification("Size", "Large");

            // Act
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .withSpecifications(List.of(spec1, spec2))
                .build();

            // Assert
            assertNotNull(product);
            assertEquals(2, product.getSpecifications().size());
            assertTrue(product.getSpecifications().contains(spec1));
            assertTrue(product.getSpecifications().contains(spec2));
        }

        @Test
        @DisplayName("Should throw exception when adding duplicate image in builder")
        void shouldThrowExceptionWhenAddingDuplicateImageInBuilder() throws Exception {
            // Arrange
            Image image = new Image(URI.create(IMAGE_URL));
            Product.ProductBuilder builder = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .withImages(Collections.singletonList(image));

            // Act & Assert
            assertThrows(IllegalStateException.class, () -> {
                builder.withImages(Collections.singletonList(image));
            });
        }

        @Test
        @DisplayName("Should throw exception when adding duplicate specification in builder")
        void shouldThrowExceptionWhenAddingDuplicateSpecificationInBuilder() {
            // Arrange
            Specification specification = new Specification(SPECIFICATION_NAME, SPECIFICATION_VALUE);
            Product.ProductBuilder builder = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .withSpecifications(Collections.singletonList(specification));

            // Act & Assert
            assertThrows(IllegalStateException.class, () -> {
                builder.withSpecifications(Collections.singletonList(specification));
            });
        }

        @Test
        @DisplayName("Should throw exception when adding null image in builder")
        void shouldThrowExceptionWhenAddingNullImageInBuilder() {
            // Arrange
            Product.ProductBuilder builder = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId);

            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                builder.withImages(Collections.singletonList(null));
            });
        }

        @Test
        @DisplayName("Should throw exception when adding null specification in builder")
        void shouldThrowExceptionWhenAddingNullSpecificationInBuilder() {
            // Arrange
            Product.ProductBuilder builder = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId);

            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                builder.withSpecifications(Collections.singletonList(null));
            });
        }

        @Test
        @DisplayName("Should create product with all optional fields using builder")
        void shouldCreateProductWithAllOptionalFieldsUsingBuilder() throws Exception {
            // Arrange
            Image image = new Image(URI.create(IMAGE_URL));
            Specification specification = new Specification(SPECIFICATION_NAME, SPECIFICATION_VALUE);

            // Act
            Product product = new Product.ProductBuilder()
                .builder(productId, sku, PRODUCT_NAME, PRODUCT_DESCRIPTION, price, categoryId)
                .withImages(Collections.singletonList(image))
                .withSpecifications(Collections.singletonList(specification))
                .build();

            // Assert
            assertNotNull(product);
            assertEquals(1, product.getImages().size());
            assertEquals(1, product.getSpecifications().size());
            assertTrue(product.getImages().contains(image));
            assertTrue(product.getSpecifications().contains(specification));
        }
    }
} 