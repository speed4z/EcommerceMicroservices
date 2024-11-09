package com.ecommerce.order.microservice.services;

import com.ecommerce.order.microservice.dto.OrderRequestDto;
import com.ecommerce.order.microservice.entities.OrderRequest;

import java.util.List;
import java.util.Optional;


public interface OrderRequestBusiness {

    List<OrderRequestDto> listAllOrders();
    List<OrderRequest> listAllOrdersByUserId(String userId);
    List<OrderRequest> listAllOrdersByInventoryId(String inventoryId);
    List<OrderRequest> listAllOrdersHavingProductId(String productId);
    List<OrderRequest> listAllOrdersHavingProductIds(List<String> productIds);
    OrderRequestDto getOrderById(String orderId);
    OrderRequestDto deleteOrderById(String orderId);
    OrderRequestDto placeOrder(OrderRequestDto orderRequest);
    List<OrderRequest> getOrdersForUserId(String userId);


}
