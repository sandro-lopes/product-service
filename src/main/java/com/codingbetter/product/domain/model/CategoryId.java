package com.codingbetter.product.domain.model;

import java.util.UUID;

public class CategoryId {
    private final UUID categoryId;

    public CategoryId(UUID categoryId) {
        if (categoryId == null) {
            throw new IllegalArgumentException("Category ID is required");
        }
        this.categoryId = categoryId;   
    }

    public UUID getValue() {
        return categoryId;
    }
}
