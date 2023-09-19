package com.productCatalogService.javaspring.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class InvalidProductException extends RuntimeException {
    private String errorMessage;
    private HttpStatus httpStatus;

    public InvalidProductException(String errorMessage, HttpStatus httpStatus) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }
}
