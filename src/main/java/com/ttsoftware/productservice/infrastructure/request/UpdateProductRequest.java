package com.ttsoftware.productservice.infrastructure.request;

import com.ttsoftware.productservice.model.dto.product.ProductImageDto;
import lombok.Data;

import java.util.List;

@Data
public class UpdateProductRequest {
    private Long id;
    private boolean isActive;
}
