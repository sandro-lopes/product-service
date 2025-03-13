package com.codingbetter.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codingbetter.application.controller.request.CreateProductRequest;
import com.codingbetter.application.controller.response.ProductResponse;
import com.codingbetter.application.controller.response.PageResponse;
import com.codingbetter.application.controller.response.ProductResponse;
import com.codingbetter.application.usecase.ActivateProductUseCase;
import com.codingbetter.application.usecase.CreateProductUseCase;
import com.codingbetter.application.usecase.SearchProductsUseCase;

import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final ActivateProductUseCase activateProductUseCase;
    private final SearchProductsUseCase searchProductsUseCase;

    public ProductController(CreateProductUseCase createProductUseCase, ActivateProductUseCase activateProductUseCase, SearchProductsUseCase searchProductsUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.activateProductUseCase = activateProductUseCase;
        this.searchProductsUseCase = searchProductsUseCase;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody CreateProductRequest request) {
        ProductResponse response = createProductUseCase.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{productId}/activate")
    public ResponseEntity<Void> activateProduct(@PathVariable UUID productId) {
        activateProductUseCase.activateProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<PageResponse<ProductResponse>> getAllProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        PageResponse<ProductResponse> productPagination = searchProductsUseCase.searchAllProducts(page, size);
        return ResponseEntity.ok(productPagination);
    }
}
