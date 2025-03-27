package com.codingbetter.domain.catalog.product.model;

import com.codingbetter.domain.shared.model.Identity;

public class SkuId implements Identity<String> {
    // Format: [Brand(3)][ProductType(4)][Size(2)][Color(3)][ID(3)][Location(3)]
    private static final String REGEX_SKU = "^[A-Z]{3}[A-Z]{4}[A-Z]{2}[A-Z]{3}\\d{3}\\d{3}$";
    
    private final String value;

    public SkuId(String value) {
        if (value == null) {
            throw new IllegalArgumentException("SKU is required");
        }
        if (!value.matches(REGEX_SKU)) {
            throw new IllegalArgumentException("SKU must match pattern: [A-Z]{3}[A-Z]{4}[A-Z]{2}[A-Z]{3}\\d{3}\\d{3}");
        }
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        SkuId that = (SkuId) other;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
} 