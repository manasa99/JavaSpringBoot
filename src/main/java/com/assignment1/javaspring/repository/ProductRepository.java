package com.example.javaspring.repository;

import com.example.javaspring.entity.ProductEntity;
import com.example.javaspring.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByProductContainingIgnoreCase(String product);
    List<ProductEntity> findByProduct(String productName);

}
