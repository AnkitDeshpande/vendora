package com.project.vendora.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A product available for purchasing.
 */
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product extends AuditCommonBaseModel {

    /**
     * The name of the product.
     */
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    /**
     * The short description of the product.
     */
    @Column(name = "short_description", nullable = false)
    private String shortDescription;
    /**
     * The long description of the product.
     */
    @Column(name = "long_description")
    private String longDescription;
    /**
     * The price of the product.
     */
    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "sku", nullable = false, unique = true)
    private String sku;

    @Column(name = "barcode", unique = true)
    private String barcode;

    @Column(name = "currency", nullable = false)
    private String currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    /**
     * The inventory of the product.
     */
    @OneToOne(mappedBy = "product", cascade = CascadeType.REMOVE, optional = false, orphanRemoval = true)
    private Inventory inventory;

}