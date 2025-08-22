package com.ttsoftware.productservice.application.mapper;

import com.ttsoftware.productservice.model.dto.payment.PaymentDto;
import com.ttsoftware.productservice.model.dto.product.ProductDto;
import com.ttsoftware.productservice.model.entity.Payment;
import com.ttsoftware.productservice.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    List<PaymentDto> toPaymentDtoList(List<Payment> paymentList);

    @Mapping(target = "orderId", source = "order.id")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    PaymentDto toPaymentDto(Payment payment);
}