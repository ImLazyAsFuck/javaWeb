package com.ss17.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product_cart", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"customerId", "productId"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int customerId;

    @Column(nullable = false)
    private int productId;

    @Column(nullable = false)
    private int quantity;
}
