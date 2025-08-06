package com.ttsoftware.productservice.repository;

import com.ttsoftware.productservice.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
