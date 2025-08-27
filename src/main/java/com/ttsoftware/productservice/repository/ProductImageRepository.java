package com.ttsoftware.productservice.repository;

import com.ttsoftware.productservice.model.entity.Product;
import com.ttsoftware.productservice.model.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    void deleteByProduct(Product product);

    void deleteProductImageByProduct(Product product);

    List<ProductImage> findByProduct(Product product);
}
