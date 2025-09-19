package com.ttsoftware.productservice.repository;

import com.ttsoftware.productservice.model.entity.Order;
import com.ttsoftware.productservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrderByUser(User user);
}
