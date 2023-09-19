package com.productCatalogService.javaspring.service;

import com.productCatalogService.javaspring.entity.ProductEntity;
import com.productCatalogService.javaspring.exception.ProductNotFoundException;
import com.productCatalogService.javaspring.exception.ServerException;
import com.productCatalogService.javaspring.model.Product;
import com.productCatalogService.javaspring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        productEntities.addAll(productRepository.findByProductContainingIgnoreCase(product));
        if(productEntities.isEmpty()) {
            throw new ProductNotFoundException("No record found for " + product, HttpStatus.NOT_FOUND);
        }
        return mapEntityToModel(productEntities);
    }

    public List<Product> getProductsByPrice(Double price){
        List<ProductEntity> productEntities = new ArrayList<>();
        productEntities.addAll(productRepository.findByPrice(price));
        if(productEntities.isEmpty()) {
            throw new ProductNotFoundException("No record found for price" + price, HttpStatus.NOT_FOUND);
        }
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
