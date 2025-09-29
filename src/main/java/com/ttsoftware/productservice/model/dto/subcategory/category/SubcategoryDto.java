package com.ttsoftware.productservice.model.dto.subcategory.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubcategoryDto {
    private Long id;
    private String name;
    private Long categoryId;
    private boolean isDeleted;
}
