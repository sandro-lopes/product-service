package com.codingbetter.product.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Product {

    private ProductId id;
    private String name;
    private String description;
    private Money price;
    private ProductStatus status;
    private CategoryId categoryId;
    private List<Image> images;
    private List<Specification> specifications;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void create(Product product) {

    }

    public void updatePrice(Money newPrice) {

    }

    public void activate() {
        status = ProductStatus.ACTIVE;
    }

    public void deactivate() {
        status = ProductStatus.INACTIVE;
    }

    public void discontinue() {
        status = ProductStatus.DISCONTINUED;
    }

    public void addImage(Image image) {
        if(images == null) {
            images = new ArrayList<>();
        }
        images.add(image);
    }

    public void addSpecification(Specification specification) {
        if(specifications == null) {
            specifications = new ArrayList<>();
        }
        specifications.add(specification);
    }
}
