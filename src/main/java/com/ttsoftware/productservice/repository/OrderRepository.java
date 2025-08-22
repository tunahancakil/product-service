package com.ttsoftware.productservice.repository;

import com.ttsoftware.productservice.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
