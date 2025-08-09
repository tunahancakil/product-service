package com.ttsoftware.productservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Where(clause = "is_deleted = 0")
@Table(name = "CATEGORY", schema = "TOPTANCICEK")
@SQLDelete(sql = "UPDATE category SET is_deleted = 1 WHERE id = ?", check = ResultCheckStyle.COUNT)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "IS_DELETED")
    private boolean deleted;

    @PrePersist
    @Generated
    private void prePersist() {
        this.deleted = false;
    }
}
