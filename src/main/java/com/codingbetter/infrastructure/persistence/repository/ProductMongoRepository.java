package com.codingbetter.infrastructure.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.codingbetter.infrastructure.persistence.entity.ProductEntity;

@Repository
public interface ProductMongoRepository extends MongoRepository<ProductEntity, String> {} 