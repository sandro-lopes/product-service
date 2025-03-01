package com.codingbetter.product.domain.repository;

import com.codingbetter.product.domain.model.CategoryId;
import com.codingbetter.product.domain.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductRepository {
    void save(Product product);
    Product findById(UUID id);
    List<Product> findByCategory(CategoryId categoryId);
    void update(Product product);
}
