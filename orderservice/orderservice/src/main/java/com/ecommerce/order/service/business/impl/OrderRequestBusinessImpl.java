package com.ecommerce.order.service.business.impl;

import com.ecommerce.order.service.business.OrderRequestBusiness;
import com.ecommerce.order.service.entities.OrderItem;
import com.ecommerce.order.service.entities.OrderRequest;
import com.ecommerce.order.service.repository.OrderItemRepository;
import com.ecommerce.order.service.repository.OrderRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderRequestBusinessImpl implements OrderRequestBusiness {

    private Logger logger= LoggerFactory.getLogger(OrderRequestBusinessImpl.class);
    private final OrderItemRepository orderItemRepository;
    private final OrderRequestRepository orderRequestRepository;

    public OrderRequestBusinessImpl(OrderItemRepository orderItemRepository,OrderRequestRepository orderRequestRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRequestRepository = orderRequestRepository;
    }

    @Override
    public Optional<OrderRequest> getOrderById(String orderId) {
        return orderRequestRepository.findById(orderId);
    }

    @Override
    public List<OrderRequest> listAllOrders() {
        return orderRequestRepository.findAll();
    }

    @Override
    public List<OrderRequest> listAllOrdersByUserId(String userId) {
        return orderRequestRepository.findByUserId(userId);
    }

    @Override
    public List<OrderRequest> listAllOrdersByInventoryId(String inventoryId) {
        return orderRequestRepository.findByInventoryId(inventoryId);
    }

    @Override
    public List<OrderRequest> listAllOrdersHavingProductId(String productId) {
        List<OrderItem> orderItems = orderItemRepository.findByProductId(productId);
        return orderRequestRepository.findByOrderIdIn(orderItems.stream()
                .map(OrderItem::getOrderRequestId)
                .collect(Collectors.toList()));
    }

    @Override
    public List<OrderRequest> listAllOrdersHavingProductIds(List<String> productIds) {
        List<OrderItem> orderItems = orderItemRepository.findByProductIdIn(productIds);
        return orderRequestRepository.findByOrderIdIn(orderItems.stream()
                .map(OrderItem::getOrderRequestId)
                .collect(Collectors.toList()));
    }

    @Override
    public Optional<OrderRequest> deleteOrderById(String orderId) {
        Optional<OrderRequest> deletedOrderRequest = getOrderById(orderId);
        orderRequestRepository.deleteById(orderId);
        return deletedOrderRequest;
    }

    @Override
    public OrderRequest placeOrder(OrderRequest orderRequest) {
        String orderUUID = UUID.randomUUID().toString();
        orderRequest.setOrderId(orderUUID);
        orderRequest.getOrderItems().forEach(item -> item.setOrderRequestId(orderRequest.getOrderId()));
        return orderRequestRepository.save(orderRequest);
    }

    @Override
    public List<OrderRequest> getOrdersForUserId(String userId) {
        return orderRequestRepository.findByUserId(userId);
    }
}
