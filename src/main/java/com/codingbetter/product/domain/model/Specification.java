package com.codingbetter.product.domain.model;

public class Specification {

    private final String name;
    private final String value;

    public Specification(String name, String value) {
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
