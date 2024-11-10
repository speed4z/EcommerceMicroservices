package com.ecommerce.order.microservice.services.impl;

import com.ecommerce.order.microservice.dto.OrderRequestDto;
import com.ecommerce.order.microservice.exceptions.ResourceNotFoundException;
import com.ecommerce.order.microservice.mapper.ModelMapperEx;
import com.ecommerce.order.microservice.services.OrderRequestBusiness;
import com.ecommerce.order.microservice.entities.OrderItem;
import com.ecommerce.order.microservice.entities.OrderRequest;
import com.ecommerce.order.microservice.repository.OrderItemRepository;
import com.ecommerce.order.microservice.repository.OrderRequestRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderRequestBusinessImpl implements OrderRequestBusiness {

//    @Autowired
//    private ModelMapper modelMapper;

    private final Logger logger= LoggerFactory.getLogger(OrderRequestBusinessImpl.class);
    private final OrderItemRepository orderItemRepository;
    private final OrderRequestRepository orderRequestRepository;
    private final ModelMapperEx modelMapper;

    public OrderRequestBusinessImpl(OrderItemRepository orderItemRepository
            ,OrderRequestRepository orderRequestRepository
            ,ModelMapperEx modelMapper) {
        this.orderItemRepository = orderItemRepository;
        this.orderRequestRepository = orderRequestRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderRequestDto getOrderById(String orderId) {
        OrderRequest order = orderRequestRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("ORDER ID "+orderId+ " NOT FOUND!"));
        logger.info("ORDER :- "+ order);
        return modelMapper.map(order,OrderRequestDto.class);
    }

    @Override
    public List<OrderRequestDto> listAllOrders() {
        List<OrderRequestDto> orderDtoList = modelMapper.mapList(orderRequestRepository.findAll(),OrderRequestDto.class);
        logger.info(orderDtoList.toString());
        return orderDtoList;
    }

    @Override
    public List<OrderRequestDto> listAllOrdersByUserId(String userId) {
        List<OrderRequestDto> orderDtoList = modelMapper.mapList(orderRequestRepository.findByUserId(userId),OrderRequestDto.class);
        logger.info(orderDtoList.toString());
        return orderDtoList;
    }

    @Override
    public List<OrderRequestDto> listAllOrdersByInventoryId(String inventoryId) {
        List<OrderRequestDto> orderDtoList = modelMapper.mapList(orderRequestRepository.findByInventoryId(inventoryId),OrderRequestDto.class);
        logger.info(orderDtoList.toString());
        return orderDtoList;
    }

    @Override
    public List<OrderRequestDto> listAllOrdersHavingProductId(String productId) {
        List<OrderItem> orderItems = orderItemRepository.findByProductId(productId);
        List<OrderRequestDto> orderDtoList = modelMapper.mapList(orderRequestRepository.findByOrderIdIn(orderItems.stream()
                .map(OrderItem::getOrderRequestId)
                .collect(Collectors.toList())),OrderRequestDto.class);
        logger.info(orderDtoList.toString());
        return orderDtoList;
    }

    @Override
    public List<OrderRequestDto> listAllOrdersHavingProductIds(List<String> productIds) {
        List<OrderItem> orderItems = orderItemRepository.findByProductIdIn(productIds);
        List<OrderRequestDto> orderDtoList = modelMapper.mapList( orderRequestRepository.findByOrderIdIn(orderItems.stream()
                .map(OrderItem::getOrderRequestId)
                .collect(Collectors.toList())),OrderRequestDto.class);
        logger.info(orderDtoList.toString());
        return orderDtoList;
    }

    @Override
    public OrderRequestDto deleteOrderById(String orderId) {
        OrderRequest orderToDelete = orderRequestRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("ORDER ID "+orderId+ " DOES NOT EXIST"));
        orderRequestRepository.deleteById(orderToDelete.getOrderId());
        logger.info("DELETED ORDER :- "+ orderToDelete);
        return modelMapper.map(orderToDelete, OrderRequestDto.class) ;
    }

    @Override
    public OrderRequestDto placeOrder(OrderRequestDto orderRequestDto) {

        Double totalPrice = orderRequestDto.getOrderItems().stream()
                .map(item -> item.getItemPrice()* item.getQuantity())
                .mapToDouble(Double::valueOf)
                .sum();
        orderRequestDto.setTotalPrice(totalPrice);

        String orderUUID = UUID.randomUUID().toString();
        orderRequestDto.setOrderId(orderUUID);
        orderRequestDto.getOrderItems().forEach(item -> item.setOrderRequestId(orderRequestDto.getOrderId()));

        OrderRequest orderEntity = modelMapper.map(orderRequestDto, OrderRequest.class);
        logger.info("ORDER CREATED : " + orderEntity);
        orderRequestRepository.save(orderEntity);

        return orderRequestDto;
    }

    @Override
    public List<OrderRequestDto> getOrdersForUserId(String userId) {
        List<OrderRequestDto> orderDtoList = modelMapper.mapList (orderRequestRepository.findByUserId(userId),OrderRequestDto.class);
        logger.info(orderDtoList.toString());
        return orderDtoList;
    }
}
