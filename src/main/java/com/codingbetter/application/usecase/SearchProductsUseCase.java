package com.codingbetter.application.usecase;

import com.codingbetter.application.controller.response.PageResponse;
import com.codingbetter.application.controller.response.ProductResponse;

public interface SearchProductsUseCase {
    PageResponse<ProductResponse> searchAllProducts(int page, int size);
}
