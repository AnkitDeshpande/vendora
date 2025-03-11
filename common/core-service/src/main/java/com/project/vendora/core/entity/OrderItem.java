package com.project.vendora.core.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
public class OrderItem extends AuditCommonBaseModel {

    @ManyToOne
    @JoinColumn(name = "web_order_id", nullable = false)
    private WebOrder webOrder;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private BigDecimal price;
}
