package com.codingbetter.infrastructure.persistence.repository;

import com.codingbetter.infrastructure.persistence.entity.ProductEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@DataMongoTest
@Testcontainers
public class ProductMongoRepositoryIntegrationTest {

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:7.0.0"));

    @Autowired
    private ProductMongoRepository productMongoRepository;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        mongoDBContainer.start();
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Configuration
    @EnableMongoRepositories(basePackages = "com.codingbetter.infrastructure.persistence.repository")
    static class TestConfig {}

    @BeforeEach
    void setUp() {
        productMongoRepository.deleteAll();
        productMongoRepository.saveAll(TestProductDataBuilder.buildProductsList());
    }

    @Test
    void testFindAll() {
        Page<ProductEntity> products = productMongoRepository.findAll(PageRequest.of(0, 10));
        Assertions.assertFalse(products.isEmpty());
        Assertions.assertEquals(3, products.getTotalElements());
    }

    @Test
    void testFindById() {
        List<ProductEntity> products = productMongoRepository.findAll();
        Assertions.assertFalse(products.isEmpty());
        
        String id = products.get(0).getId();
        Optional<ProductEntity> foundProduct = productMongoRepository.findById(id);
        
        Assertions.assertTrue(foundProduct.isPresent());
    }

    @Test
    void testSaveProduct() {
        ProductEntity newProduct = TestProductDataBuilder.buildProduct("SKU999", "Novo Produto");
        
        ProductEntity savedProduct = productMongoRepository.save(newProduct);
        
        Assertions.assertNotNull(savedProduct.getId());
        Assertions.assertEquals("SKU999", savedProduct.getSku());
        
        List<ProductEntity> allProducts = productMongoRepository.findAll();
        Assertions.assertEquals(4, allProducts.size());
    }

    @Test
    void testFindByCategory() {
        String categoryId = "eletronicos";
        Page<ProductEntity> products = productMongoRepository.findByCategoryId(categoryId, PageRequest.of(0, 10));
        
        Assertions.assertFalse(products.isEmpty());
        Assertions.assertEquals(2, products.getTotalElements());
        Assertions.assertEquals(categoryId, products.getContent().get(0).getCategoryId());
    }

    @Test
    void testDeleteProduct() {
        List<ProductEntity> products = productMongoRepository.findAll();
        Assertions.assertFalse(products.isEmpty());
        
        String id = products.get(0).getId();
        productMongoRepository.deleteById(id);
        
        Optional<ProductEntity> deletedProduct = productMongoRepository.findById(id);
        Assertions.assertFalse(deletedProduct.isPresent());
        
        Assertions.assertEquals(2, productMongoRepository.findAll().size());
    }
    
    static class TestProductDataBuilder {
        
        public static List<ProductEntity> buildProductsList() {
            return List.of(
                buildProduct("SKU123", "Smartphone Galaxy", "eletronicos"),
                buildProduct("SKU456", "Notebook Dell", "eletronicos"),
                buildProduct("SKU789", "Livro Clean Code", "livros")
            );
        }
        
        public static ProductEntity buildProduct(String sku, String name) {
            return buildProduct(sku, name, "geral");
        }
        
        public static ProductEntity buildProduct(String sku, String name, String categoryId) {
            ProductEntity product = new ProductEntity();
            product.setSku(sku);
            product.setName(name);
            product.setDescription("Description of product " + name);
            product.setCategoryId(categoryId);
            product.setPrice(BigDecimal.valueOf(Math.random() * 1000).setScale(2, RoundingMode.HALF_UP));
            product.setCurrency("BRL");
            product.setQuantity((int) (Math.random() * 100));
            product.setStatus("ACTIVE");
            product.setSpecifications(Map.of(
                "color", "varied",
                "weight", Math.random() * 5 + "kg",
                "dimensions", "custom"
            ));
            
            LocalDateTime now = LocalDateTime.now();
            product.setCreatedAt(now);
            product.setUpdatedAt(now);
            
            return product;
        }
    }
}

