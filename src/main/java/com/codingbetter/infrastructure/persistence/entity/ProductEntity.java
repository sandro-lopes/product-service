package com.codingbetter.infrastructure.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class ProductEntity {
    
    @Id
    private String id;

    @Indexed(unique = true)
    private String sku;
    private String name;
    private String description;
    private String categoryId;
    private BigDecimal price;
    private String currency;
    private int quantity;
    private String status;
    private List<URL> images;
    private Map<String, String> specifications;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 