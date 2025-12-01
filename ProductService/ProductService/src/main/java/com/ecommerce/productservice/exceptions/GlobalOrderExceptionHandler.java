package com.ecommerce.productservice.exceptions;

import com.ecommerce.productservice.Payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalOrderExceptionHandler {

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ProductException ex)
    {
        String message = ex.getMessage();
        ApiResponse response = ApiResponse
                .builder()
                .setMessage(message)
                .setSuccess(true)
                .setHttpStatus(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(response,response.getHttpStatus());
    }

}
