package com.ttsoftware.productservice.application.mapper;

import com.ttsoftware.productservice.model.dto.order.OrderDto;
import com.ttsoftware.productservice.model.dto.payment.PaymentDto;
import com.ttsoftware.productservice.model.entity.Order;
import com.ttsoftware.productservice.model.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    List<OrderDto> toOrderDtoList(List<Order> orderList);

    @Mapping(source = "user.id", target = "userId")
    OrderDto toOrderDto(Order order);

    Order toOrder(OrderDto orderDto);
}