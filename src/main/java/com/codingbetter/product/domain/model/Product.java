package com.codingbetter.product.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Product {

    private final ProductId id;
    private final Sku sku;
    private final String name;
    private final String description;
    private final CategoryId categoryId;
    private Money price;
    private ProductStatus status;
    private List<Image> images;
    private List<Specification> specifications;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Product(ProductBuilder builder) {
        this.id = builder.id;
        this.sku = builder.sku;
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
        this.status = builder.status;
        this.categoryId = builder.categoryId;
        this.images = builder.images;
        this.specifications = builder.specifications;
        this.createdAt = builder.createdAt;
    }

    public void updatePrice(Money newPrice) {
        this.price = newPrice;
        this.updatedAt = LocalDateTime.now();
    }

    public void activate() {
        this.status = ProductStatus.ACTIVE;
        this.updatedAt = LocalDateTime.now();
    }

    public void deactivate() {
        this.status = ProductStatus.INACTIVE;
        this.updatedAt = LocalDateTime.now();
    }

    public void discontinue() {
        this.status = ProductStatus.DISCONTINUED;
        this.updatedAt = LocalDateTime.now();
    }

    public void addImage(Image image) {
        if(this.images == null) {
            this.images = new ArrayList<>();
        }
        this.images.add(image);
        this.updatedAt = LocalDateTime.now();
    }

    public void addSpecification(Specification specification) {
        if(this.specifications == null) {
            this.specifications = new ArrayList<>();
        }
        this.specifications.add(specification);
        this.updatedAt = LocalDateTime.now();
    }

    public ProductId getId() {
        return id;
    }
    public Sku getSku() {
        return sku;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public Money getPrice() {
        return price;
    }
    public ProductStatus getStatus() {
        return status;
    }
    public CategoryId getCategoryId() {
        return categoryId;
    }
    public List<Image> getImages() {
        return images;
    }
    public List<Specification> getSpecifications() {
        return specifications;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static class ProductBuilder {
        private ProductId id;
        private Sku sku;
        private String name;
        private String description;
        private Money price;
        private ProductStatus status;
        private CategoryId categoryId;
        private List<Image> images;
        private List<Specification> specifications;
        private LocalDateTime createdAt;

        public ProductBuilder builder(ProductId id, Sku sku, String name, String description, Money price, CategoryId categoryId) {
            this.id = id;
            this.sku = sku;
            this.name = name;
            this.description = description;
            this.price = price;
            this.categoryId = categoryId;
            this.status = ProductStatus.DRAFT;
            this.createdAt = LocalDateTime.now();
            return this;
        }

        public ProductBuilder withImage(Image image) {
            if (this.images == null) {
                this.images = new ArrayList<>();
            }
            this.images.add(image);
            return this;
        }

        public ProductBuilder withSpecification(Specification specification) {
            if (this.specifications == null) {
                this.specifications = new ArrayList<>();
            }
            this.specifications.add(specification);
            return this;
        }

        public Product build() {
            if (id == null) {
                id = new ProductId();
            }

            if (sku == null) {
                throw new IllegalStateException("SKU is required");
            }

            if (name == null) {
                throw new IllegalStateException("Name is required");
            }

            if (description == null) {
                throw new IllegalStateException("Description is required");
            }

            if (price == null) {
                throw new IllegalStateException("Price is required");
            }

            if(categoryId == null) {
                throw new IllegalStateException("CategoryId is required");
            }

            return new Product(this);
        }
    }
}
