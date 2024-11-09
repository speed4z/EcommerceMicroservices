package com.ecommerce.order.microservice.payload;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {

    private String message;
    private boolean success;
    private HttpStatus httpStatus;

}
