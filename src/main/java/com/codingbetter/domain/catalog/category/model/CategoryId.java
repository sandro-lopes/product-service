package com.codingbetter.domain.catalog.category.model;

import java.util.UUID;

import com.codingbetter.domain.shared.model.Identity;

public class CategoryId implements Identity<UUID> {
    private final UUID value;

    public CategoryId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("Category ID is required");
        }
        this.value = value;
    }

    @Override
    public UUID getValue() {
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
        CategoryId categoryId = (CategoryId) other;
        return value.equals(categoryId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
