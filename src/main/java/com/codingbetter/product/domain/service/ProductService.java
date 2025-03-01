package com.codingbetter.product.domain.service;

import com.codingbetter.product.domain.model.Sku;

public interface ProductService {
    boolean skuExists(Sku sku);
}
