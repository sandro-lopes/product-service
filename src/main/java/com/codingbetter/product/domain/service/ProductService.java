package com.codingbetter.product.domain.service;

import com.codingbetter.product.domain.model.Product;

import java.util.UUID;

public interface ProductService {
    void validate(Product product);
    boolean checkAvailability(UUID productId);
}
