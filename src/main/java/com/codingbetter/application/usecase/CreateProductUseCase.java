package com.codingbetter.application.usecase;

import com.codingbetter.application.controller.request.CreateProductRequest;
import com.codingbetter.application.controller.response.ProductResponse;

public interface CreateProductUseCase {
    ProductResponse createProduct(CreateProductRequest request);
} 