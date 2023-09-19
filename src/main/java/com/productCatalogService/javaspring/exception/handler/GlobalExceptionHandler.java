package com.productCatalogService.javaspring.exception.handler;

import com.productCatalogService.javaspring.exception.ErrorResponse;
import com.productCatalogService.javaspring.exception.ProductNotFoundException;
import com.productCatalogService.javaspring.exception.ServerException;
import com.productCatalogService.javaspring.exception.InvalidProductException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.info("handleException {}", exception.getMessage());
        var error = ErrorResponse.builder()
                .errorCode("500")
                .errorMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .httpStatusCode(500)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
    @ExceptionHandler(ServerException.class)
    public ResponseEntity<ErrorResponse> handleServerException(ServerException serverException) {
        log.info("handleServerException {}", serverException.getErrorMessage());
        var error = ErrorResponse.builder()
                .errorCode(String.valueOf(serverException.getHttpStatus().value()))
                .errorMessage(serverException.getErrorMessage())
                .httpStatusCode(serverException.getHttpStatus().value())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(serverException.getHttpStatus()).body(error);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
        log.info("handleProductNotFoundException {}", productNotFoundException.getErrorMessage());
        var error = ErrorResponse.builder()
                .errorCode(String.valueOf(productNotFoundException.getHttpStatus().value()))
                .errorMessage(productNotFoundException.getErrorMessage())
                .httpStatusCode(productNotFoundException.getHttpStatus().value())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(productNotFoundException.getHttpStatus()).body(error);

    }

    @ExceptionHandler(InvalidProductException.class)
    public ResponseEntity<ErrorResponse> handleInvalidProductException(InvalidProductException invalidProductException) {
        log.info("handleInvalidProductException: {}", invalidProductException.getErrorMessage());
        var error = ErrorResponse.builder()
                .errorCode(String.valueOf(invalidProductException.getHttpStatus().value()))
                .errorMessage(invalidProductException.getErrorMessage())
                .httpStatusCode(invalidProductException.getHttpStatus().value())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(invalidProductException.getHttpStatus()).body(error);
    }



}
