package com.codingbetter.domain.catalog.product.model;

import java.time.LocalDateTime;
import java.util.List;

import com.codingbetter.domain.catalog.category.model.CategoryId;
import com.codingbetter.domain.catalog.product.event.ProductActivatedEvent;
import com.codingbetter.domain.catalog.product.event.ProductDeactivatedEvent;
import com.codingbetter.domain.catalog.product.event.ProductDiscontinuedEvent;
import com.codingbetter.domain.catalog.product.event.ProductPriceChangedEvent;
import com.codingbetter.domain.shared.model.AbstractAggregateRoot;
import com.codingbetter.domain.shared.model.Entity;
import com.codingbetter.domain.shared.util.CollectionUtils;

public class Product extends AbstractAggregateRoot implements Entity<Product, ProductId> {

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

    Product(ProductFactory.Builder builder) {
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
        ProductValidations.validatePrice(this.price, newPrice);
        Money oldPrice = this.price;
        this.price = newPrice;
        this.updatedAt = LocalDateTime.now();
        this.addDomainEvent(new ProductPriceChangedEvent(this.id, oldPrice, newPrice));
    }

    public void draft() {
        this.status = ProductStatus.DRAFT;
        this.updatedAt = LocalDateTime.now();
    }

    public void activate() {
        ProductValidations.validateStatus(this.status, ProductStatus.ACTIVE);
        this.status = ProductStatus.ACTIVE;
        this.updatedAt = LocalDateTime.now();
        this.addDomainEvent(new ProductActivatedEvent(this.id));
    }

    public void deactivate() {
        ProductValidations.validateStatus(this.status, ProductStatus.INACTIVE);
        this.status = ProductStatus.INACTIVE;
        this.updatedAt = LocalDateTime.now();
        this.addDomainEvent(new ProductDeactivatedEvent(this.id));
    }

    public void discontinue() {
        ProductValidations.validateStatus(this.status, ProductStatus.DISCONTINUED);
        this.status = ProductStatus.DISCONTINUED;
        this.updatedAt = LocalDateTime.now();
        this.addDomainEvent(new ProductDiscontinuedEvent(this.id));
    }

    public void addImage(Image image) {
        this.images = CollectionUtils.initializeList(this.images);
        ProductValidations.validateImage(image, this.images);
        this.images.add(image);
        this.updatedAt = LocalDateTime.now();
    }

    public void addSpecification(Specification specification) {
        this.specifications = CollectionUtils.initializeList(this.specifications);
        ProductValidations.validateSpecification(specification, this.specifications);
        this.specifications.add(specification);
        this.updatedAt = LocalDateTime.now();
    }

    @Override
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

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Product product = (Product) other;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
