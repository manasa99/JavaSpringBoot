package com.productCatalogService.javaspring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "product")
    private String product;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    public ProductEntity(String product, String description, double price) {
        this.product = product;
        this.description = description;
        this.price = price;
    }
}
