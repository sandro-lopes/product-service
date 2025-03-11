package com.codingbetter.domain.catalog.product.repository;

import java.util.Optional;

import com.codingbetter.domain.catalog.product.model.Product;
import com.codingbetter.domain.catalog.product.model.ProductId;

public interface ProductRepository {
    void save(Product product);
    Optional<Product> findById(ProductId productId);
}
