package com.codingbetter.infrastructure.persistence.adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.codingbetter.domain.catalog.product.model.Product;
import com.codingbetter.domain.catalog.product.model.ProductId;
import com.codingbetter.domain.catalog.product.repository.ProductRepository;
import com.codingbetter.infrastructure.persistence.entity.ProductEntity;
import com.codingbetter.infrastructure.persistence.mapper.InfraProductMapper;
import com.codingbetter.infrastructure.persistence.repository.ProductMongoRepository;

/**
 * Adapter implementation of the ProductRepository interface.
 * Connects the domain layer to the MongoDB persistence infrastructure.
 */
@Component
public class ProductRepositoryAdapter implements ProductRepository {

    private final ProductMongoRepository productMongoRepository;
    private final InfraProductMapper productMapper;

    public ProductRepositoryAdapter(ProductMongoRepository productMongoRepository, InfraProductMapper productMapper) {
        this.productMongoRepository = productMongoRepository;
        this.productMapper = productMapper;
    }

    @Override
    public void save(Product product) {
        ProductEntity productEntity = productMapper.toEntity(product);
        productMongoRepository.save(productEntity);
    }

    @Override
    public Optional<Product> findById(ProductId productId) {
        return productMongoRepository.findById(productId.getValue().getUuid().toString())
            .map(productMapper::toDomain);
    }
} 