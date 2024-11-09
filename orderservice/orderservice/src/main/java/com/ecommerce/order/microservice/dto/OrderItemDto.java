package com.ecommerce.order.microservice.dto;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class OrderItemDto {

    private String orderItemId;
    private String productId;
    private Integer quantity;
    private Double itemPrice;

    private String orderRequestId;


}
