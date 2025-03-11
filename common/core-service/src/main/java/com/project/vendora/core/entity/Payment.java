package com.project.vendora.core.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public class Payment extends AuditCommonBaseModel {

    @ManyToOne
    @JoinColumn(name = "web_order_id", nullable = false)
    private WebOrder webOrder;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "is_successful")
    private boolean isSuccessful;
}
