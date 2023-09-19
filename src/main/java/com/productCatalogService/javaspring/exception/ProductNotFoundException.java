package com.productCatalogService.javaspring.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ProductNotFoundException extends RuntimeException {
    private String errorMessage;
    private HttpStatus httpStatus;

    public ProductNotFoundException(String errorMessage, HttpStatus httpStatus) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }
}
