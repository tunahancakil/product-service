package com.ttsoftware.productservice.repository;

import com.ttsoftware.productservice.model.entity.Product;
import com.ttsoftware.productservice.model.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    SubCategory findByName(String name);
}
