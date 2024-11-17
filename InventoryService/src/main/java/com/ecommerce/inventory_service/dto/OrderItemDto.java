package com.ecommerce.inventory_service.dto;

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
