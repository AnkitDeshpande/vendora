package com.project.vendora.core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "store")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Store extends AuditCommonBaseModel {

    @Column(name = "store_name", nullable = false)
    private String storeName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "contact_number", nullable = false)
    private String contactNumber;

    @Column(name = "business_type", nullable = false)
    private String businessType;

    @Column(name = "address_line_1")
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "country")
    private String country;

    @Column(name = "tax_id")
    private String taxId;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "website")
    private String website;

    @Column(name = "description")
    private String description;

    @Column(name = "currency")
    private String currency;

    @Column(name = "timezone")
    private String timezone;

    @Column(name = "opening_time")
    private LocalTime openingTime;

    @Column(name = "closing_time")
    private LocalTime closingTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(name = "facebook_url")
    private String facebookUrl;

    @Column(name = "instagram_url")
    private String instagramUrl;

    @Column(name = "twitter_url")
    private String twitterUrl;
}
