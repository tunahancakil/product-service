package com.ttsoftware.productservice.application.mapper;

import com.ttsoftware.productservice.infrastructure.request.CreateBannerRequest;
import com.ttsoftware.productservice.model.dto.order.OrderAddressDto;
import com.ttsoftware.productservice.model.dto.order.OrderDto;
import com.ttsoftware.productservice.model.dto.order.OrderItemDto;
import com.ttsoftware.productservice.model.entity.Banner;
import com.ttsoftware.productservice.model.entity.Order;
import com.ttsoftware.productservice.model.entity.OrderAddress;
import com.ttsoftware.productservice.model.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BannerMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Banner toBanner(CreateBannerRequest createBannerRequest);
}