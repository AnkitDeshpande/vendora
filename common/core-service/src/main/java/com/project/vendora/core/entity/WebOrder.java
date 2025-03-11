package com.project.vendora.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Order generated from the website.
 */
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "web_order")
public class WebOrder extends AuditCommonBaseModel {

    /**
     * The user of the order.
     * -- GETTER --
     * Gets the user of the order.
     * <p>
     * <p>
     * -- SETTER --
     * Sets the user of the order.
     *
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    /**
     * The shipping address of the order.
     * -- GETTER --
     * Gets the address of the order.
     * <p>
     * <p>
     * -- SETTER --
     * Sets the address of the order.
     *
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    /**
     * The quantities ordered.
     * -- GETTER --
     * Gets the quantities ordered.
     * <p>
     * <p>
     * -- SETTER --
     * Sets the quantities ordered.
     *
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<WebOrderQuantities> quantities = new ArrayList<>();

}