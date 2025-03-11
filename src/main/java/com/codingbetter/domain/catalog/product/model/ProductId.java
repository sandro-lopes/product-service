package com.codingbetter.domain.catalog.product.model;

import java.util.UUID;

import com.codingbetter.domain.shared.model.Identity;

public class ProductId implements Identity<ProductId> {
    private final UUID value;

    public ProductId() {
        this.value = UUID.randomUUID();
    }

    public ProductId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        this.value = value;
    }

    @Override
    public ProductId getValue() {
        return this;
    }

    public UUID getUuid() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        ProductId that = (ProductId) other;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
