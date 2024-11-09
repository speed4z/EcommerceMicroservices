package com.ecommerce.order.microservice.services.impl;

import com.ecommerce.order.microservice.exceptions.ResourceNotFoundException;
import com.ecommerce.order.microservice.services.OrderRequestBusiness;
import com.ecommerce.order.microservice.entities.OrderItem;
import com.ecommerce.order.microservice.entities.OrderRequest;
import com.ecommerce.order.microservice.repository.OrderItemRepository;
import com.ecommerce.order.microservice.repository.OrderRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderRequestBusinessImpl implements OrderRequestBusiness {

    private final Logger logger= LoggerFactory.getLogger(OrderRequestBusinessImpl.class);
    private final OrderItemRepository orderItemRepository;
    private final OrderRequestRepository orderRequestRepository;

    public OrderRequestBusinessImpl(OrderItemRepository orderItemRepository,OrderRequestRepository orderRequestRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRequestRepository = orderRequestRepository;
    }

    @Override
    public OrderRequest getOrderById(String orderId) {
        OrderRequest order = orderRequestRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("ORDER ID "+orderId+ " NOT FOUND!"));
        logger.info("ORDER :- "+ order);
        return order;
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
    public OrderRequest deleteOrderById(String orderId) {
        OrderRequest orderToDelete = orderRequestRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("ORDER ID "+orderId+ " DOES NOT EXIST"));
        orderRequestRepository.deleteById(orderToDelete.getOrderId());
        logger.info("DELETED ORDER :- "+ orderToDelete);
        return orderToDelete;
    }

    @Override
    public OrderRequest placeOrder(OrderRequest orderRequest) {

        Double totalPrice = orderRequest.getOrderItems().stream()
                .map(item -> item.getItemPrice()* item.getQuantity())
                .mapToDouble(Double::valueOf)
                .sum();

        orderRequest.setTotalPrice(totalPrice);
        String orderUUID = UUID.randomUUID().toString();
        orderRequest.setOrderId(orderUUID);
        orderRequest.getOrderItems().forEach(item -> item.setOrderRequestId(orderRequest.getOrderId()));

        logger.info("ORDER CREATED : " + orderRequest);

        return orderRequestRepository.save(orderRequest);
    }

    @Override
    public List<OrderRequest> getOrdersForUserId(String userId) {
        return orderRequestRepository.findByUserId(userId);
    }
}
