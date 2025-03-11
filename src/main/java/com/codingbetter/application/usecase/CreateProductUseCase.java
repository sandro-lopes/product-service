package com.codingbetter.application.usecase;

import com.codingbetter.application.controller.request.CreateProductRequest;
import com.codingbetter.application.controller.response.CreateProductResponse;

public interface CreateProductUseCase {
    CreateProductResponse createProduct(CreateProductRequest request);
} 