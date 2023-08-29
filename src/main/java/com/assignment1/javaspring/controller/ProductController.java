package com.assignment1.javaspring.controller;

import com.assignment1.javaspring.model.Product;
import com.assignment1.javaspring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        try{
            Product newProduct = productService.createProduct(product);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (Exception e){
            System.out.println("Error "+e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/all")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> productList = productService.getAllProducts();
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/products/name")
    public ResponseEntity<List<Product>> getProducts(@RequestParam(required = true) String product){
        List<Product> productList = productService.getProducts(product);
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/products/price")
    public ResponseEntity<List<Product>> getProductsByPrice(@RequestParam(required = true) Double price){
        List<Product> productList = productService.getProductsByPrice(price);
        return ResponseEntity.ok(productList);
    }

    @DeleteMapping("/products/delete")
    public ResponseEntity<String> deleteProductsByName(@RequestParam(required = true) String product){
        try {
            productService.deleteProductsByName(product);
            return ResponseEntity.ok("Products with name '" + product + "' deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error " + e);
            return new ResponseEntity<>("Error deleting products with name '" + product + "'.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/products/delete/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long productId){
        try {
            productService.deleteProductById(productId);
            return ResponseEntity.ok("Product with ID " + productId + " deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error " + e);
            return new ResponseEntity<>("Error deleting product with ID " + productId + ".", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
