package com.ttsoftware.productservice.application.mapper;

import com.ttsoftware.productservice.model.dto.ProductDto;
import com.ttsoftware.productservice.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    List<ProductDto> toProductDtoList(List<Product> products);

    @Mapping(target = "category", source = "category.name")
    @Mapping(target = "subCategory", source = "subCategory.name")
    ProductDto toProductDto(Product product);
}