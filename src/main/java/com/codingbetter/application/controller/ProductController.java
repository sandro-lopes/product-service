package com.codingbetter.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingbetter.application.controller.request.CreateProductRequest;
import com.codingbetter.application.controller.response.CreateProductResponse;
import com.codingbetter.application.usecase.ActivateProductUseCase;
import com.codingbetter.application.usecase.CreateProductUseCase;

import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final ActivateProductUseCase activateProductUseCase;

    public ProductController(CreateProductUseCase createProductUseCase, ActivateProductUseCase activateProductUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.activateProductUseCase = activateProductUseCase;
    }

    @PostMapping
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody CreateProductRequest request) {
        CreateProductResponse response = createProductUseCase.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{productId}/activate")
    public ResponseEntity<Void> activateProduct(@PathVariable UUID productId) {
        activateProductUseCase.activateProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
