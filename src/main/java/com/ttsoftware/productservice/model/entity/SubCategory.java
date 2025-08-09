package com.ttsoftware.productservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@NoArgsConstructor
@Where(clause = "is_deleted = 0")
@Table(name = "SUBCATEGORY", schema = "TOPTANCICEK")
@SQLDelete(sql = "UPDATE subcategory SET is_deleted = 1 WHERE id = ?", check = ResultCheckStyle.COUNT)
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "IS_DELETED")
    private boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category category;

    @PrePersist
    @Generated
    private void prePersist() {
        this.deleted = false;
    }
}
