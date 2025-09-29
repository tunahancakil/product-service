package com.ttsoftware.productservice.application.mapper;

import com.ttsoftware.productservice.model.dto.subcategory.category.SubcategoryDto;
import com.ttsoftware.productservice.model.entity.SubCategory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubcategoryMapper {
    SubCategory toSubcategory(SubcategoryDto subcategoryDto);

    SubcategoryDto toSubcategoryDto(SubCategory subCategory);

    List<SubcategoryDto> toSubcategoryDtoList(List<SubCategory> subCategoryList);
}