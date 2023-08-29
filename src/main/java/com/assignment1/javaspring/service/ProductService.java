package com.example.javaspring.service;

import com.example.javaspring.entity.ProductEntity;
import com.example.javaspring.model.Product;
import com.example.javaspring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product){
        ProductEntity productEntity = productRepository.save(new ProductEntity(product.getProduct(),
                product.getDescription(), product.getPrice()));
        return new Product(productEntity.getId(), productEntity.getProduct(), productEntity.getDescription(), productEntity.getPrice());

    }

    public List<Product> getAllProducts(){
        List<ProductEntity> productEntities = new ArrayList<>();
        productEntities.addAll(productRepository.findAll());
        return mapEntityToModel(productEntities);
    }

    public  List<Product>  getProducts(String product){
        List<ProductEntity> productEntities = new ArrayList<>();
        if (product == null) productEntities.addAll(productRepository.findAll());
        else productEntities.addAll(productRepository.findByProductContainingIgnoreCase(product));
        return mapEntityToModel(productEntities);
    }

    public void deleteProductsByName(String productName) {
        List<ProductEntity> productsToDelete = productRepository.findByProduct(productName);
        productRepository.deleteAll(productsToDelete);
    }

    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }

    private List<Product> mapEntityToModel(List<ProductEntity> productEntities) {
        return productEntities.stream().map(productEntity -> new Product(productEntity.getId(), productEntity.getProduct(), productEntity.getDescription(), productEntity.getPrice())).toList();
    }

}
