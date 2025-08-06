package com.ttsoftware.productservice.controller.mapper;

import com.ttsoftware.productservice.model.dto.ProductDto;
import com.ttsoftware.productservice.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    List<ProductDto> toProductDtoList(List<Product> products);

    ProductDto toProductDto(Product product);
}