package com.productCatalogService.javaspring.controller;

import com.productCatalogService.javaspring.model.Product;
import com.productCatalogService.javaspring.service.ReactiveProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/reactive/products")
public class ReactiveProductController {

    @Autowired
    private ReactiveProductService productService;

    @GetMapping("/external-data")
    public Mono<Product> fetchExternalData() {
        return productService.fetchSomeDataFromExternalService();
    }

    @PostMapping
    public Mono<ResponseEntity<Product>> createProduct(@RequestBody Product product) {
        return productService.createProduct(product)
                .map(newProduct -> new ResponseEntity<>(newProduct, HttpStatus.CREATED))
                .onErrorResume(e -> {
                    System.out.println("Error " + e);
                    return Mono.just(new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
                });
    }

    @GetMapping("/all")
    public Flux<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/name")
    public Flux<Product> getProducts(@RequestParam(required = true) String product) {
        return productService.getProducts(product);
    }

    @GetMapping("/price")
    public Flux<Product> getProductsByPrice(@RequestParam(required = true) Double price) {
        return productService.getProductsByPrice(price);
    }

    @DeleteMapping("/delete")
    public Mono<ResponseEntity<String>> deleteProductsByName(@RequestParam(required = true) String product) {
        return productService.deleteProductsByName(product)
                .thenReturn(ResponseEntity.ok("Products with name '" + product + "' deleted successfully."))
                .onErrorResume(e -> {
                    System.out.println("Error " + e);
                    return Mono.just(new ResponseEntity<>("Error deleting products with name '" + product + "'.", HttpStatus.INTERNAL_SERVER_ERROR));
                });
    }

    @DeleteMapping("/delete/{productId}")
    public Mono<ResponseEntity<String>> deleteProductById(@PathVariable Long productId) {
        return productService.deleteProductById(productId)
                .thenReturn(ResponseEntity.ok("Product with ID " + productId + " deleted successfully."))
                .onErrorResume(e -> {
                    System.out.println("Error " + e);
                    return Mono.just(new ResponseEntity<>("Error deleting product with ID " + productId + ".", HttpStatus.INTERNAL_SERVER_ERROR));
                });
    }
}
