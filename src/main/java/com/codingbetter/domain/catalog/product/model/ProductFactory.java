package com.codingbetter.domain.catalog.product.model;

import com.codingbetter.domain.catalog.category.model.CategoryId;
import com.codingbetter.domain.shared.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductFactory {

    /**
     * Cria um novo builder para construir um produto
     */
    public static Builder builder(ProductId id, Sku sku, String name, String description, Money price, CategoryId categoryId) {
        return new Builder(id, sku, name, description, price, categoryId);
    }

      
    /**
     * Builder interno para construção de produtos
     */
    public static class Builder {
        ProductId id;
        Sku sku;
        String name;
        String description;
        Money price;
        ProductStatus status;
        CategoryId categoryId;
        List<Image> images;
        List<Specification> specifications;
        LocalDateTime createdAt;

        public Builder(ProductId id, Sku sku, String name, String description, Money price, CategoryId categoryId) {
            this.id = id;
            this.sku = sku;
            this.name = name;
            this.description = description;
            this.price = price;
            this.categoryId = categoryId;
            this.createdAt = LocalDateTime.now();
            this.images = new ArrayList<>();
            this.specifications = new ArrayList<>();
        }

        public Builder withImages(List<Image> images) {
            this.images = CollectionUtils.initializeList(this.images);
            if (images != null) {
                images.forEach(image -> {
                    ProductValidations.validateImage(image, this.images);
                    this.images.add(image);
                });
            }
            return this;
        }

        public Builder withSpecifications(List<Specification> specifications) {
            this.specifications = CollectionUtils.initializeList(this.specifications);
            if (specifications != null) {
                specifications.forEach(specification -> {
                    ProductValidations.validateSpecification(specification, this.specifications);
                    this.specifications.add(specification);
                });
            }
            return this;
        }

        public Builder withStatus(ProductStatus status) {
            this.status = status;
            return this;
        }

        public Product build() {
            if (id == null) {
                id = new ProductId();
            }
            if (status == null) {
                status = ProductStatus.DRAFT;
            }

            ProductValidations.validateProductCreation(id, sku, name, description, price, status, categoryId, createdAt);
            return new Product(this);
        }
    }
} 