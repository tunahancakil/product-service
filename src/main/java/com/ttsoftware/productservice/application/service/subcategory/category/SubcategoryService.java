package com.ttsoftware.productservice.application.service.subcategory.category;

import com.ttsoftware.productservice.application.mapper.SubcategoryMapper;
import com.ttsoftware.productservice.model.dto.category.CategoryDto;
import com.ttsoftware.productservice.model.dto.subcategory.category.SubcategoryDto;
import com.ttsoftware.productservice.model.entity.Category;
import com.ttsoftware.productservice.model.entity.SubCategory;
import com.ttsoftware.productservice.repository.CategoryRepository;
import com.ttsoftware.productservice.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubcategoryService {

    private final SubcategoryMapper subcategoryMapper;
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    public ResponseEntity<SubcategoryDto> getSubcategoryById(Long id) {
        Optional<SubCategory> subCategory  = subCategoryRepository.findById(id);
        return new ResponseEntity<>(subCategory.map(subcategoryMapper::toSubcategoryDto).orElse(null), HttpStatus.OK);
    }

    public ResponseEntity<SubcategoryDto> createSubcategory(SubcategoryDto subcategoryDto) {
        SubCategory subCategory = new SubCategory();
        if (subcategoryDto.getCategoryId() != null) {
            Optional<Category> optionalCategory = categoryRepository.findById(subcategoryDto.getCategoryId());
            if (optionalCategory.isPresent()) {
                subCategory.setCategory(optionalCategory.get());
            } else {
                return new ResponseEntity("Category is not found.", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity("Category id is required.", HttpStatus.BAD_REQUEST);
        }
        subCategory.setDeleted(false);
        subCategory.setName(subcategoryDto.getName());
        return new ResponseEntity<>(subcategoryMapper.toSubcategoryDto(subCategoryRepository.save(subCategory)), HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteSubcategory(Long id) {
        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(id);
        if (optionalSubCategory.isPresent()) {
            SubCategory subCategory = optionalSubCategory.get();
            subCategory.setDeleted(true);
            subCategoryRepository.save(subCategory);
            return new ResponseEntity<>("Subcategory has been deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Subcategory is not found.", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> updateSubcategory(SubcategoryDto subcategoryDto) {
        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(subcategoryDto.getId());
        if (optionalSubCategory.isPresent()) {
            SubCategory subCategory = optionalSubCategory.get();
            if (subcategoryDto.getCategoryId() != null) {
                Optional<Category> optionalCategory = categoryRepository.findById(subcategoryDto.getCategoryId());
                if (optionalCategory.isPresent()) {
                    subCategory.setCategory(optionalCategory.get());
                } else {
                    return new ResponseEntity<>("Category is not found.", HttpStatus.NOT_FOUND);
                }
            }
            subCategory.setName(subcategoryDto.getName());
            subCategoryRepository.save(subCategory);
            return  new ResponseEntity<>("Subcategory has been updated.", HttpStatus.OK);
        } else  {
            return new ResponseEntity<>("Category id is required.", HttpStatus.BAD_REQUEST);
        }
    }

    public List<SubcategoryDto> getAllCategory() {
        return subcategoryMapper.toSubcategoryDtoList(subCategoryRepository.findAll());
    }
}