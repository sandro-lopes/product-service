package com.codingbetter.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codingbetter.application.controller.mapper.PageMapper;
import com.codingbetter.application.controller.mapper.ProductMapper;
import com.codingbetter.application.controller.request.CreateProductRequest;
import com.codingbetter.application.controller.response.ProductResponse;
import com.codingbetter.application.controller.response.PageResponse;
import com.codingbetter.application.usecase.ActivateProductUseCase;
import com.codingbetter.application.usecase.CreateProductUseCase;
import com.codingbetter.application.usecase.SearchProductsUseCase;
import com.codingbetter.domain.catalog.product.exception.ProductNotFoundException;
import com.codingbetter.domain.catalog.product.model.Image;
import com.codingbetter.domain.catalog.product.model.Money;
import com.codingbetter.domain.catalog.product.model.Product;
import com.codingbetter.domain.catalog.product.model.ProductId;
import com.codingbetter.domain.catalog.product.model.ProductRepository;
import com.codingbetter.domain.catalog.product.model.Specification;
import com.codingbetter.domain.shared.event.DomainEventPublisher;
import com.codingbetter.domain.shared.model.Page;

import java.util.UUID;

@Service
public class ProductApplicationService implements CreateProductUseCase, ActivateProductUseCase, SearchProductsUseCase {
    
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final DomainEventPublisher eventPublisher;

    public ProductApplicationService(ProductRepository productRepository, DomainEventPublisher eventPublisher, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.eventPublisher = eventPublisher;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public ProductResponse createProduct(CreateProductRequest request) {
        Product newProduct = productMapper.toEntity(request);
        productRepository.save(newProduct);
        publishDomainEvents(newProduct);
        return productMapper.toResponse(newProduct);
    }

    @Override
    @Transactional
    public void activateProduct(UUID productId) {
        executeAndPublishEvents(new ProductId(productId), Product::activate);
    }

    @Override
    public PageResponse<ProductResponse> searchAllProducts(int page, int size) {
        Page<Product> products = productRepository.findAll(page, size);
        return PageMapper.toResponse(products, productMapper::toResponse);
    }

    public void updatePrice(ProductId productId, Money newPrice) {
        executeAndPublishEvents(productId, product -> product.updatePrice(newPrice));
    }

    public void deactivate(ProductId productId) {
        executeAndPublishEvents(productId, Product::deactivate);
    }

    public void discontinue(ProductId productId) {
        executeAndPublishEvents(productId, Product::discontinue);
    }

    public void addImage(ProductId productId, Image image) {
        executeAndPublishEvents(productId, product -> product.addImage(image));
    }

    public void addSpecification(ProductId productId, Specification specification) {
        executeAndPublishEvents(productId, product -> product.addSpecification(specification));
    }

    @Transactional(readOnly = true)
    public Product findProductById(ProductId productId) {
        return productRepository.findById(productId)
            .orElseThrow(() -> new ProductNotFoundException(
                String.format("Product with ID %s was not found", productId.getUuid())));
    }

    private void executeAndPublishEvents(ProductId productId, ProductOperation operation) {
        Product product = findProductById(productId);
        operation.execute(product);
        productRepository.save(product);
        publishDomainEvents(product);
    }

    private void publishDomainEvents(Product product) {
        product.getDomainEvents().forEach(eventPublisher::publish);
        product.clearDomainEvents();
    }

    @FunctionalInterface
    public interface ProductOperation {
        void execute(Product product);
    }
}