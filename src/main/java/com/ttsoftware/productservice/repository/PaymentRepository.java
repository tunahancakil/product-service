package com.ttsoftware.productservice.repository;

import com.ttsoftware.productservice.model.entity.Payment;
import com.ttsoftware.productservice.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
