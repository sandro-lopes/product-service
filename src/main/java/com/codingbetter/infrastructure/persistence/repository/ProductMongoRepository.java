package com.codingbetter.infrastructure.persistence.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import com.codingbetter.infrastructure.persistence.entity.ProductEntity;

@Repository
public interface ProductMongoRepository extends MongoRepository<ProductEntity, String> {

    @NonNull
    Page<ProductEntity> findAll(@NonNull Pageable pageable);
    
    @NonNull
    Page<ProductEntity> findByCategoryId(@NonNull String category, @NonNull Pageable pageable);
} 