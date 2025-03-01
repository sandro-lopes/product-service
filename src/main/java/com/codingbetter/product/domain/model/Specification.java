package com.codingbetter.product.domain.model;

public class Specification {

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
}
