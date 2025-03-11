package com.project.vendora.core.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "shipping")
public class Shipping extends AuditCommonBaseModel {

    @OneToOne
    @JoinColumn(name = "web_order_id", nullable = false)
    private WebOrder webOrder;

    @Column(name = "tracking_number")
    private String trackingNumber;

    @Column(name = "carrier")
    private String carrier;

    @Column(name = "estimated_delivery")
    private LocalDateTime estimatedDelivery;
}
