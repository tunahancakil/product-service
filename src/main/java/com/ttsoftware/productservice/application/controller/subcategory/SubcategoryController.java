package com.ttsoftware.productservice.application.controller.subcategory;

import com.ttsoftware.productservice.application.service.category.CategoryService;
import com.ttsoftware.productservice.application.service.subcategory.category.SubcategoryService;
import com.ttsoftware.productservice.model.dto.category.CategoryDto;
import com.ttsoftware.productservice.model.dto.subcategory.category.SubcategoryDto;
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
@RequestMapping("/api/subcategory")
public class SubcategoryController {

    private final SubcategoryService subcategoryService;

    @GetMapping(value = "/getSubcategoryById", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<SubcategoryDto> getCategoryById(@RequestParam("id") Long id) {
        return subcategoryService.getSubcategoryById(id);
    }

    @PostMapping(value = "/createSubcategory", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<SubcategoryDto> createCategory(@RequestBody SubcategoryDto subcategoryDto) {
        return subcategoryService.createSubcategory(subcategoryDto);
    }

    @DeleteMapping(value = "/deleteSubcategory", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteCategory(@RequestParam("id") Long id) {
        return subcategoryService.deleteSubcategory(id);
    }

    @PutMapping(value = "/updateSubcategory")
    public ResponseEntity<String> updateCategory(@RequestBody SubcategoryDto subcategoryDto) {
        return subcategoryService.updateSubcategory(subcategoryDto);
    }

    @GetMapping(value = "/getAllSubcategory", produces = APPLICATION_JSON_VALUE)
    public List<SubcategoryDto> getAllCategory() {
        return subcategoryService.getAllCategory();
    }

}