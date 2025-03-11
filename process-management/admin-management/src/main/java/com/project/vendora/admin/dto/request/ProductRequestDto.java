package com.project.vendora.admin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {
    private Long id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private Double price;
    private Integer quantity;
    private String sku;
    private String barcode;
    private Long categoryId;
    private Long storeId;
    private String currency;
    private Boolean isActive;
}