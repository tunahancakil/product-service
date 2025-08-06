package com.ttsoftware.productservice.repository;

import com.ttsoftware.productservice.model.entity.Product;
import com.ttsoftware.productservice.model.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
