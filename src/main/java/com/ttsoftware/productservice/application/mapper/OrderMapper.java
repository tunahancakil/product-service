package com.ttsoftware.productservice.application.mapper;

import com.ttsoftware.productservice.model.dto.order.OrderAddressDto;
import com.ttsoftware.productservice.model.dto.order.OrderDto;
import com.ttsoftware.productservice.model.dto.order.OrderItemDto;
import com.ttsoftware.productservice.model.entity.Order;
import com.ttsoftware.productservice.model.entity.OrderAddress;
import com.ttsoftware.productservice.model.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "product.id", target = "productId")
    List<OrderDto> toOrderDtoList(List<Order> orderList);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "orderItems", target = "orderItems")
    @Mapping(source = "orderAddress", target = "orderAddressDto")
    OrderDto toOrderDto(Order order);

    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "product.id", target = "productId")
    OrderItemDto toOrderItemDto(OrderItem orderItem);

    List<OrderItemDto> toOrderItemDtoList(List<OrderItem> orderItems);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    OrderAddress toOrderAddressEntity(OrderAddressDto orderAddressDto);
}