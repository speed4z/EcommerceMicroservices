package com.ecommerce.order.service.business;

import com.ecommerce.order.service.entities.OrderRequest;

import java.util.List;
import java.util.Optional;


public interface OrderRequestBusiness {

    List<OrderRequest> listAllOrders();
    List<OrderRequest> listAllOrdersByUserId(String userId);
    List<OrderRequest> listAllOrdersByInventoryId(String inventoryId);
    List<OrderRequest> listAllOrdersHavingProductId(String productId);
    List<OrderRequest> listAllOrdersHavingProductIds(List<String> productIds);
    Optional<OrderRequest> getOrderById(String orderId);
    Optional<OrderRequest> deleteOrderById(String orderId);
    OrderRequest placeOrder(OrderRequest orderRequest);
    List<OrderRequest> getOrdersForUserId(String userId);


}
