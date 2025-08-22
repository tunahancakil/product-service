package com.ttsoftware.productservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Where(clause = "is_deleted = 0")
@Table(name = "PAYMENT", schema = "TOPTANCICEK")
@SQLDelete(sql = "UPDATE payment SET is_deleted = 1 WHERE id = ?", check = ResultCheckStyle.COUNT)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;

    @Column(name = "AMOUNT", precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "CURRENCY", length = 10)
    private String currency;

    @Column(name = "STATUS", length = 20)
    private String status;

    @Column(name = "PAYMENT_METHOD", length = 50)
    private String paymentMethod;

    @Column(name = "TRANSACTION_ID", length = 100)
    private String transactionId;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @Column(name = "UPDATED_DATE")
    private LocalDateTime updatedDate;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "IS_DELETED")
    private boolean deleted;

    @PrePersist
    @Generated
    private void prePersist() {
        this.createdDate = LocalDateTime.now();
        this.userName = "PAYMENT_SERVICE_USER";
        this.deleted = false;
    }
}