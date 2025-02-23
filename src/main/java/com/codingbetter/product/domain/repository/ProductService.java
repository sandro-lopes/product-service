package com.codingbetter.product.domain.repository;

import com.codingbetter.product.domain.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    void save(Product product);
    Product findById(UUID id);
    List<Product> findByCategory(Category category);
    void update(Product product);
}
