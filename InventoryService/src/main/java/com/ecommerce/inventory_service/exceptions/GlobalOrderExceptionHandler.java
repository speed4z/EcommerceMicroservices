package com.ecommerce.inventory_service.exceptions;

import com.ecommerce.inventory_service.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalOrderExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex)
    {
        String message = ex.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).success(true).httpStatus(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(response,response.getHttpStatus());
    }

}
