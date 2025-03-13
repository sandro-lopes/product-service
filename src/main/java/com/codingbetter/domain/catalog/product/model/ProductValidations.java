package com.codingbetter.domain.catalog.product.model;

import java.time.LocalDateTime;
import java.util.List;

import com.codingbetter.domain.catalog.category.model.CategoryId;

public class ProductValidations {
    private ProductValidations() {
        // Private constructor to prevent instantiation
    }

    public static void validateImage(Image image, List<Image> existingImages) {
        if(image == null) {
            throw new IllegalArgumentException("Image is required");
        }
        if(existingImages != null && existingImages.contains(image)) {
            throw new IllegalStateException("Image already exists in the product");
        }
    }

    public static void validateSpecification(Specification specification, List<Specification> existingSpecifications) {
        if(specification == null) {
            throw new IllegalArgumentException("Specification is required");
        }
        if(existingSpecifications != null && existingSpecifications.contains(specification)) {
            throw new IllegalStateException("Specification already exists in the product");
        }
    }

    public static void validateStatus(ProductStatus currentStatus, ProductStatus newStatus) {
        if (currentStatus == newStatus) {
            throw new IllegalStateException("Product is already " + newStatus.name().toLowerCase());
        }
        if (currentStatus == ProductStatus.DISCONTINUED) {
            throw new IllegalStateException("Cannot modify a discontinued product");
        }
    }

    public static void validatePrice(Money currentPrice, Money newPrice) {
        if(newPrice == null) {
            throw new IllegalArgumentException("New price is required");
        }
        if(!newPrice.getCurrency().equals(currentPrice.getCurrency())) {
            throw new IllegalArgumentException("New price must have the same currency as the old price");
        }
    }

    public static void validateProductCreation(ProductId id, Sku sku, String name, String description, Money price, ProductStatus status, CategoryId categoryId, LocalDateTime createdAt) {
        if (sku == null) {
            throw new IllegalStateException("SKU is required");
        }

        if (name == null || name.trim().isEmpty() || name.length() < 3) {
            throw new IllegalStateException("Name must have at least 3 characters");
        }

        if (description == null || description.trim().isEmpty() || description.length() < 10) {
            throw new IllegalStateException("Description must have at least 10 characters");
        }

        if (price == null) {
            throw new IllegalStateException("Price is required");
        }

        if(status == null) {
            throw new IllegalStateException("Status is required");
        }

        if(categoryId == null) {
            throw new IllegalStateException("CategoryId is required");
        }

        if (createdAt == null) {
            throw new IllegalStateException("Created date is required");
        }
    }
} 