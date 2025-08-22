package com.ttsoftware.productservice.model.dto.payment;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentDto {
    private Long id;
    private Long orderId;
    private Long userId;
    private BigDecimal amount;
    private String currency;
    private String status;
    private String paymentMethod;
    private String transactionId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
