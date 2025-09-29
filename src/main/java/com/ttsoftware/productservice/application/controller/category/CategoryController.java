package com.ttsoftware.productservice.application.controller.category;

import com.ttsoftware.productservice.application.service.category.CategoryService;
import com.ttsoftware.productservice.model.dto.category.CategoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping(value = "/getCategoryById", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> getCategoryById(@RequestParam("id") Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping(value = "/createCategory", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.createCategory(categoryDto);
    }

    @DeleteMapping(value = "/deleteCategory", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteCategory(@RequestParam("id") Long id) {
        return categoryService.deleteCategory(id);
    }

    @PutMapping(value = "/updateCategory")
    public ResponseEntity<String> updateCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.updateCategory(categoryDto);
    }

    @GetMapping(value = "/getAllCategory", produces = APPLICATION_JSON_VALUE)
    public List<CategoryDto> getAllCategory() {
        return categoryService.getAllCategory();
    }

}