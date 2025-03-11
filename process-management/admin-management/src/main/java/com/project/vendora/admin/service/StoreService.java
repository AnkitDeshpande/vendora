package com.project.vendora.admin.service;


import com.project.vendora.admin.dto.response.ProductResponseDto;
import com.project.vendora.admin.dto.response.StoreResponseDto;
import com.project.vendora.admin.model.request.ProductRequest;
import com.project.vendora.admin.model.request.StoreRequest;

public interface StoreService {
    StoreResponseDto addStore(StoreRequest storeRequest);

    ProductResponseDto addProduct(ProductRequest productRequest);
}