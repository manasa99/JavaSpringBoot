package com.productCatalogService.javaspring.repository;

import com.productCatalogService.javaspring.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByProductContainingIgnoreCase(String product);
    List<ProductEntity> findByProduct(String productName);
    List<ProductEntity> findByPrice(Double price);

}
