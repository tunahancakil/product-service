package com.ttsoftware.productservice.application.mapper;

import com.ttsoftware.productservice.model.dto.category.CategoryDto;
import com.ttsoftware.productservice.model.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryDto categoryDto);

    CategoryDto toCategoryDto(Category category);

    List<CategoryDto> toCategoryDtoList(List<Category> categories);
}