package com.codingbetter.infrastructure.persistence.mapper;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.codingbetter.domain.catalog.category.model.CategoryId;
import com.codingbetter.domain.catalog.product.model.Image;
import com.codingbetter.domain.catalog.product.model.Money;
import com.codingbetter.domain.catalog.product.model.Product;
import com.codingbetter.domain.catalog.product.model.ProductFactory;
import com.codingbetter.domain.catalog.product.model.ProductId;
import com.codingbetter.domain.catalog.product.model.ProductStatus;
import com.codingbetter.domain.catalog.product.model.Sku;
import com.codingbetter.domain.catalog.product.model.Specification;
import com.codingbetter.infrastructure.persistence.entity.ProductEntity;

/**
 * Mapper for converting between Product domain entity and ProductEntity persistence entity.
 */
@Component
public class InfraProductMapper {

    /**
     * Converts a domain Product to a persistence ProductEntity.
     *
     * @param product The domain product
     * @return The persistence entity
     */
    public ProductEntity toEntity(Product product) {
        if (product == null) {
            return null;
        }
        
        return ProductEntity.builder()
                .id(product.getId().getValue().getUuid().toString())
                .sku(product.getSku().getId().getValue())
                .name(product.getName())
                .description(product.getDescription())
                .categoryId(product.getCategoryId().getValue().toString())
                .price(product.getPrice().getAmount())
                .currency(product.getPrice().getCurrency().getCurrencyCode())
                .status(product.getStatus().name())
                .images(convertImagesToUrls(product.getImages()))
                .specifications(mapSpecificationsToMap(product.getSpecifications()))
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .quantity(0) // Default value
                .build();
    }

    /**
     * Converts a persistence ProductEntity to a domain Product.
     *
     * @param productEntity The persistence entity
     * @return The domain product
     */
    public Product toDomain(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }
        
        ProductFactory.Builder builder = ProductFactory.builder(
                    new ProductId(UUID.fromString(productEntity.getId())),
                    Sku.fromValue(productEntity.getSku()),
                    productEntity.getName(),
                    productEntity.getDescription(),
                    new Money(productEntity.getPrice(), Currency.getInstance(productEntity.getCurrency())),
                    new CategoryId(UUID.fromString(productEntity.getCategoryId())))
                .withStatus(ProductStatus.valueOf(productEntity.getStatus()));
        
        if (productEntity.getImages() != null && !productEntity.getImages().isEmpty()) {
            builder.withImages(convertUrlsToImages(productEntity.getImages()));
        }
        
        if (productEntity.getSpecifications() != null && !productEntity.getSpecifications().isEmpty()) {
            builder.withSpecifications(mapMapToSpecifications(productEntity.getSpecifications()));
        }
        
        return builder.build();
    }

    /**
     * Converts a list of domain Products to a list of persistence ProductEntities.
     *
     * @param products The domain products
     * @return The persistence entities
     */
    public List<ProductEntity> toEntities(List<Product> products) {
        if (products == null) {
            return null;
        }
        return products.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    /**
     * Converts a list of persistence ProductEntities to a list of domain Products.
     *
     * @param productEntities The persistence entities
     * @return The domain products
     */
    public List<Product> toDomains(List<ProductEntity> productEntities) {
        if (productEntities == null) {
            return null;
        }
        return productEntities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    // Helper methods for mapping collections

    private List<URL> convertImagesToUrls(List<Image> images) {
        if (images == null) {
            return new ArrayList<>();
        }
        
        List<URL> urls = new ArrayList<>();
        for (Image image : images) {
            urls.add(image.getUrl());
        }
        return urls;
    }

    private List<Image> convertUrlsToImages(List<URL> urls) {
        if (urls == null) {
            return new ArrayList<>();
        }
        
        List<Image> images = new ArrayList<>();
        for (URL url : urls) {
            images.add(new Image(URI.create(url.toString())));
        }
        return images;
    }

    private Map<String, String> mapSpecificationsToMap(List<Specification> specifications) {
        if (specifications == null) {
            return new HashMap<>();
        }
        return specifications.stream()
                .collect(Collectors.toMap(
                        Specification::getName,
                        Specification::getValue,
                        (existing, replacement) -> existing
                ));
    }

    private List<Specification> mapMapToSpecifications(Map<String, String> specificationsMap) {
        if (specificationsMap == null) {
            return new ArrayList<>();
        }
        return specificationsMap.entrySet().stream()
                .map(entry -> new Specification(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
