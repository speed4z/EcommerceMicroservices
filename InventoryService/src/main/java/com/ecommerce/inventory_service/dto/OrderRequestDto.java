package com.ecommerce.inventory_service.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class OrderRequestDto {

    private String orderId;
    private String userId;
    private String inventoryId;
    private Date date;
    private Double totalPrice;

    private List<OrderItemDto> orderItems;

}
