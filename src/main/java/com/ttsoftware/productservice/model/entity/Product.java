package com.ttsoftware.productservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Where(clause = "is_deleted = 0")
@Table(name = "PRODUCT", schema = "TOPTANCICEK")
@SQLDelete(sql = "UPDATE product SET is_deleted = 1 WHERE id = ?", check = ResultCheckStyle.COUNT)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "ACTIVE")
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUB_CATEGORY_ID", nullable = false)
    private SubCategory subCategory;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> images;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "IS_DELETED")
    private boolean deleted;

    @PrePersist
    @Generated
    private void prePersist() {
        this.createdDate = LocalDateTime.now();
        this.userName = "PRODUCT_SERVICE_USER";
        this.deleted = false;
    }
}
