package com.ttsoftware.productservice.repository;

import com.ttsoftware.productservice.model.entity.Category;
import com.ttsoftware.productservice.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
