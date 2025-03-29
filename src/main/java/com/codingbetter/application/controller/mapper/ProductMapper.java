package com.codingbetter.application.controller.mapper;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URL;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.codingbetter.application.controller.request.CreateProductRequest;
import com.codingbetter.application.controller.response.ProductResponse;
import com.codingbetter.application.controller.response.ProductResponse;
import com.codingbetter.domain.catalog.category.model.CategoryId;
import com.codingbetter.domain.catalog.product.model.Image;
import com.codingbetter.domain.catalog.product.model.Money;
import com.codingbetter.domain.catalog.product.model.Product;
import com.codingbetter.domain.catalog.product.model.ProductFactory;
import com.codingbetter.domain.catalog.product.model.ProductId;
import com.codingbetter.domain.catalog.product.model.ProductStatus;
import com.codingbetter.domain.catalog.product.model.Sku;
import com.codingbetter.domain.catalog.product.model.Specification;

/**
 * MapStruct mapper for converting between domain entities and DTOs in the application layer.
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {

    /**
     * Converts a Product domain entity to a CreateProductResponse DTO.
     *
     * @param product The product domain entity
     * @return The create product response
     */
    @Mapping(target = "id", source = "id", qualifiedByName = "productIdToString")
    @Mapping(target = "sku", source = "sku", qualifiedByName = "skuToString")
    @Mapping(target = "price", source = "price.amount", qualifiedByName = "bigDecimalToString")
    @Mapping(target = "currency", source = "price.currency.currencyCode")
    @Mapping(target = "categoryId", source = "categoryId", qualifiedByName = "categoryIdToString")
    @Mapping(target = "images", source = "images", qualifiedByName = "imagesToUrls")
    @Mapping(target = "specifications", source = "specifications", qualifiedByName = "specificationsToMap")
    ProductResponse toResponse(Product product);


    /**
     * Converts a CreateProductRequest DTO to a Product domain entity.
     * This is a facade method that delegates to manual mapping due to 
     * Product's builder pattern.
     */
    default Product toEntity(CreateProductRequest request) {
        if (request == null) {
            return null;
        }
        
        return ProductFactory.builder(
                    new ProductId(),
                    Sku.fromValue(request.getSku()),
                    request.getName(),
                    request.getDescription(),
                    new Money(request.getPrice(), Currency.getInstance(request.getCurrency())),
                    new CategoryId(request.getCategoryId()))
                .withStatus(ProductStatus.DRAFT)
                .withImages(convertImages(request.getImages()))
                .withSpecifications(convertSpecifications(request.getSpecifications()))
                .build();
    }

    default List<Image> convertImages(List<String> images) {
        return images.stream().map(url -> new Image(URI.create(url))).collect(Collectors.toList());
    }

    default List<Specification> convertSpecifications(Map<String, String> specifications) {
        return specifications.entrySet().stream().map(entry -> new Specification(entry.getKey(), entry.getValue())).collect(Collectors.toList());
    }

    // Custom mapping methods

    @Named("productIdToString")
    default String productIdToString(ProductId productId) {
        return productId.getValue().getUuid().toString();
    }

    @Named("skuToString")
    default String skuToString(Sku sku) {
        return sku.getId().getValue();
    }

    @Named("categoryIdToString")
    default String categoryIdToString(CategoryId categoryId) {
        return categoryId.getValue().toString();
    }

    @Named("bigDecimalToString")
    default String bigDecimalToString(BigDecimal value) {
        return value != null ? value.toString() : null;
    }

    @Named("imagesToUrls")
    default List<URL> imagesToUrls(List<Image> images) {
        if (images == null) {
            return null;
        }
        return images.stream()
                .map(Image::getUrl)
                .collect(Collectors.toList());
    }

    @Named("specificationsToMap")
    default Map<String, String> specificationsToMap(List<Specification> specifications) {
        if (specifications == null) {
            return null;
        }
        return specifications.stream()
                .collect(Collectors.toMap(
                        Specification::getName,
                        Specification::getValue,
                        (existing, replacement) -> existing
                ));
    }
}
