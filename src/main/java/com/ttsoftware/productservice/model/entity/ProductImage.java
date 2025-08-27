package com.ttsoftware.productservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@Where(clause = "is_deleted = 0")
@Table(name = "PRODUCT_IMAGE", schema = "TOPTANCICEK")
@SQLDelete(sql = "UPDATE product_image SET is_deleted = 1 WHERE id = ?", check = ResultCheckStyle.COUNT)
public class ProductImage {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "color")
    private String color;

    @Column(name = "image")
    private String image;

    @Column(name = "IS_DELETED")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}