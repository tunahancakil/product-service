package com.ttsoftware.productservice.model.dto.order;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDto {
    private Long id;
    private Long productId;
    private String productColor;
    private Integer quantity;
    private BigDecimal price;
}
