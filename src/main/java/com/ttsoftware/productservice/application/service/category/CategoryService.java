package com.ttsoftware.productservice.application.service.category;

import com.ttsoftware.productservice.application.mapper.CategoryMapper;
import com.ttsoftware.productservice.model.dto.category.CategoryDto;
import com.ttsoftware.productservice.model.dto.product.ProductDto;
import com.ttsoftware.productservice.model.entity.Category;
import com.ttsoftware.productservice.repository.CategoryRepository;
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
public class CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public ResponseEntity<CategoryDto> getCategoryById(Long id) {
        Optional<Category> category  = categoryRepository.findById(id);
        return new ResponseEntity<>(category.map(categoryMapper::toCategoryDto).orElse(null), HttpStatus.OK);
    }

    public ResponseEntity<CategoryDto> createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setDeleted(false);
        category.setName(categoryDto.getName());
        return new ResponseEntity<>(categoryMapper.toCategoryDto(categoryRepository.save(category)), HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteCategory(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setDeleted(true);
            categoryRepository.save(category);
            return new ResponseEntity<>("Category has been deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Category is not found.", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> updateCategory(CategoryDto request) {
        Optional<Category> optionalCategory = categoryRepository.findById(request.getId());
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            if (request.getName() != null && !request.getName().isEmpty()) {
                category.setName(request.getName());
            } else {
                return new ResponseEntity<>("Category name is required.", HttpStatus.BAD_REQUEST);
            }
            categoryRepository.save(category);
            return new ResponseEntity<>("Category has been updated.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Category is not found.", HttpStatus.NOT_FOUND);
        }
    }

    public List<CategoryDto> getAllCategory() {
        return categoryMapper.toCategoryDtoList(categoryRepository.findAll());
    }

}
