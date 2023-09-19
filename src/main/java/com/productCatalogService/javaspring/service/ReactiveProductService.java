package com.productCatalogService.javaspring.service;

import com.productCatalogService.javaspring.entity.ProductEntity;
import com.productCatalogService.javaspring.model.Product;
import com.productCatalogService.javaspring.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
public class ReactiveProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WebClient webClient;

    public Mono<Product> fetchSomeDataFromExternalService() {
        log.info("Fetching data from external service");
        return webClient.get()
                .uri("/endpoint") // Specify the URI of the external service
                .retrieve()
                .bodyToMono(Product.class); // Assuming the response can be mapped to a Product class
    }

    public Mono<Product> createProduct(Product product) {
        log.info("Creating product: {}", product);
        return Mono.fromCallable(() -> {
            ProductEntity productEntity = productRepository.save(new ProductEntity(product.getProduct(),
                    product.getDescription(), product.getPrice()));
            return new Product(productEntity.getId(), productEntity.getProduct(), productEntity.getDescription(), productEntity.getPrice());
        });
    }

    public Flux<Product> getAllProducts() {
        log.info("Getting all Products");
        return Flux.defer(() -> Flux.fromIterable(mapEntityToModel(productRepository.findAll())));
    }

    public Flux<Product> getProducts(String product) {
        log.info("Getting all Products containing {}", product);
        return Flux.defer(() -> Flux.fromIterable(mapEntityToModel(productRepository.findByProductContainingIgnoreCase(product))));
    }

    public Flux<Product> getProductsByPrice(Double price) {
        log.info("Getting all Products by price {}", price);
        return Flux.defer(() -> Flux.fromIterable(mapEntityToModel(productRepository.findByPrice(price))));
    }

    public Mono<Void> deleteProductsByName(String productName) {
        log.info("Deleting all Products by name {}", productName);
        return Mono.fromRunnable(() -> {
            List<ProductEntity> productsToDelete = productRepository.findByProduct(productName);
            productRepository.deleteAll(productsToDelete);
        });
    }

    public Mono<Void> deleteProductById(Long productId) {
        log.info("Deleting product with {}", productId);
        return Mono.fromRunnable(() -> productRepository.deleteById(productId));
    }

    private List<Product> mapEntityToModel(List<ProductEntity> productEntities) {
        log.info("Mapping Entity to Models");
        return productEntities.stream().map(productEntity -> new Product(productEntity.getId(), productEntity.getProduct(), productEntity.getDescription(), productEntity.getPrice())).toList();
    }
}
