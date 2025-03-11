package com.project.vendora.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The quantity ordered of a product.
 */
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "web_order_quantities")
public class WebOrderQuantities extends AuditCommonBaseModel {

    /**
     * The product being ordered.
     * -- GETTER --
     * Gets the product ordered.
     * <p>
     * <p>
     * -- SETTER --
     * Sets the product.
     *
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    /**
     * The quantity being ordered.
     * -- GETTER --
     * Gets the quantity ordered.
     * <p>
     * <p>
     * -- SETTER --
     * Sets the quantity ordered.
     *
     */
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    /**
     * The order itself.
     * -- GETTER --
     * Gets the order.
     * <p>
     * <p>
     * -- SETTER --
     * Sets the order.
     *
     */
    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private WebOrder order;

}