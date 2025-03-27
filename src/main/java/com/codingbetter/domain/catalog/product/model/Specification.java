package com.codingbetter.domain.catalog.product.model;

import java.util.Objects;

import com.codingbetter.domain.shared.model.ValueObject;

public class Specification implements ValueObject<Specification> {

    private final String name;
    private final String value;

    public Specification(String name, String value) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name of specification is required");
        }
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Value of specification is required");
        }
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }
    public String getValue() {
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
        Specification specification = (Specification) other;
        return sameValueAs(specification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    @Override
    public boolean sameValueAs(Specification other) {
        return name.equals(other.name) && value.equals(other.value);
    }
}
