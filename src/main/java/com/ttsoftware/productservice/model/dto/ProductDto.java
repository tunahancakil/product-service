package com.ttsoftware.productservice.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private Double price;
    private String category;
    private String subCategory;
    private List<ProductImageDto> images;
}
