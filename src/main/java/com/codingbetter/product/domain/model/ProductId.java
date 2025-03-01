package com.codingbetter.product.domain.model;

import java.util.UUID;

public class ProductId {

    private final UUID id;

    public ProductId() {
        this.id = UUID.randomUUID();
    }
    public UUID getId() {
        return id;
    }
}
