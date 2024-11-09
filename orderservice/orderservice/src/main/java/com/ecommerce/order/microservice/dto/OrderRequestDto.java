package com.ecommerce.order.microservice.dto;

import jakarta.persistence.*;
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
