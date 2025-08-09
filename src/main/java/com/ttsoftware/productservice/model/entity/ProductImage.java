package com.ttsoftware.productservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ProductImage {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "color")
    private String color;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}