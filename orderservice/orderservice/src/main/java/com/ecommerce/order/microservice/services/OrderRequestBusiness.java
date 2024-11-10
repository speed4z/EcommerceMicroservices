package com.ecommerce.order.microservice.services;

import com.ecommerce.order.microservice.dto.OrderRequestDto;
import com.ecommerce.order.microservice.entities.OrderRequest;

import java.util.List;
import java.util.Optional;


public interface OrderRequestBusiness {

    List<OrderRequestDto> listAllOrders();
    List<OrderRequestDto> listAllOrdersByUserId(String userId);
    List<OrderRequestDto> listAllOrdersByInventoryId(String inventoryId);
    List<OrderRequestDto> listAllOrdersHavingProductId(String productId);
    List<OrderRequestDto> listAllOrdersHavingProductIds(List<String> productIds);
    OrderRequestDto getOrderById(String orderId);
    OrderRequestDto deleteOrderById(String orderId);
    OrderRequestDto placeOrder(OrderRequestDto orderRequest);
    List<OrderRequestDto> getOrdersForUserId(String userId);


}
