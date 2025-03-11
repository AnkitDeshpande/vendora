package com.project.vendora.admin.controller;

import com.project.vendora.admin.dto.request.ProductRequestDto;
import com.project.vendora.admin.dto.request.StoreRequestDto;
import com.project.vendora.admin.dto.response.ProductResponseDto;
import com.project.vendora.admin.dto.response.StoreResponseDto;
import com.project.vendora.admin.model.request.ProductRequest;
import com.project.vendora.admin.model.request.StoreRequest;
import com.project.vendora.admin.service.StoreService;
import com.project.vendora.core.utility.Mapper;
import com.project.vendora.core.utility.MessageUtils;
import com.project.vendora.core.utility.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final MessageUtils messageUtils;
    private final Mapper mapper;

    @PostMapping("/add")
    public ResponseEntity<SuccessResponse<StoreResponseDto>> addStore(@RequestBody StoreRequestDto storeRequestDto) {
        StoreResponseDto storeResponseDto = storeService.addStore(mapper.convert(storeRequestDto, StoreRequest.class));
        return SuccessResponse.<StoreResponseDto>builder()
                .data(storeResponseDto)
                .statusCode(200)
                .message(messageUtils.getMessage("store.add.success", null))
                .build().getResponseEntity();
    }

    @PostMapping("/add-product")
    public ResponseEntity<SuccessResponse<ProductResponseDto>> addProduct(@RequestBody ProductRequestDto productRequestDto) {

        ProductResponseDto productResponseDto = storeService.addProduct(mapper.convert(productRequestDto, ProductRequest.class));
        return SuccessResponse.<ProductResponseDto>builder()
                .data(productResponseDto)
                .statusCode(200)
                .message(messageUtils.getMessage("product.add.success", null))
                .build().getResponseEntity();
    }
}