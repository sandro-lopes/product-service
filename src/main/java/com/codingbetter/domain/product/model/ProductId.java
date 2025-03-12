package com.codingbetter.domain.product.model;

import com.codingbetter.domain.shared.model.Identity;

import java.util.Objects;
import java.util.UUID;

/**
 * Value object representing a Product identifier.
 */
public class ProductId implements Identity {
    
    private final String value;
    
    private ProductId(String value) {
        this.value = Objects.requireNonNull(value, "Product ID cannot be null");
        if (value.isBlank()) {
            throw new IllegalArgumentException("Product ID cannot be empty");
        }
    }
    
    /**
     * Creates a new ProductId with a random UUID.
     * 
     * @return A new ProductId
     */
    public static ProductId create() {
        return new ProductId(UUID.randomUUID().toString());
    }
    
    /**
     * Creates a ProductId from an existing ID string.
     * 
     * @param id The ID string
     * @return A ProductId
     */
    public static ProductId of(String id) {
        return new ProductId(id);
    }
    
    @Override
    public String toString() {
        return value;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductId productId = (ProductId) o;
        return Objects.equals(value, productId.value);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    
    public String getValue() {
        return value;
    }
} 