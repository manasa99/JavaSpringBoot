package com.productCatalogService.javaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.productCatalogService.javaspring.config")
public class productCatalogService {

    public static void main(String[] args) {
        SpringApplication.      run(productCatalogService.class, args);
    }

}
