package com.codingbetter.domain.catalog.product.repository;

import java.util.Optional;

import com.codingbetter.domain.catalog.product.model.Product;
import com.codingbetter.domain.catalog.product.model.ProductId;
import com.codingbetter.domain.shared.model.Page;

public interface ProductRepository {
    void save(Product product);
    Optional<Product> findById(ProductId productId);
    Page<Product> findAll(int page, int size);
    Page<Product> findByCategory(String category, int page, int size);
}
