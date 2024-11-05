package com.ecommerce.order.service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ordertable")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderRequest {

    @Id
    private String orderId;
    private String userId;
    private String inventoryId;
    private Date date;
    private Double totalPrice;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "orderRequestId")
    private List<OrderItem> orderItems;

}
