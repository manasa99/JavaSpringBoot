package com.productCatalogService.javaspring.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private long id;
    private String product;
    private String description;
    private double price;

}