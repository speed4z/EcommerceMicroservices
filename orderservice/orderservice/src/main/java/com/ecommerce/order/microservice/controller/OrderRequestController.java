package com.ecommerce.order.microservice.controller;

import com.ecommerce.order.microservice.dto.OrderRequestDto;
import com.ecommerce.order.microservice.services.OrderRequestBusiness;
import com.ecommerce.order.microservice.entities.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order-request")
public class OrderRequestController {

    @Autowired
    private OrderRequestBusiness orderRequestBusiness;

    @PostMapping("/place")
    public ResponseEntity<OrderRequestDto> placeOrder(@RequestBody OrderRequestDto orderRequest){
        OrderRequestDto orderRequestSaved = orderRequestBusiness.placeOrder(orderRequest);
        return ResponseEntity.ok(orderRequestSaved);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderRequestDto> getOrderById(@PathVariable String orderId){
        OrderRequestDto orderRequestResponse = orderRequestBusiness.getOrderById(orderId);
        return ResponseEntity.ok(orderRequestResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderRequestDto>> getAllOrders(){
        List<OrderRequestDto> orderRequests = orderRequestBusiness.listAllOrders();
        return ResponseEntity.ok(orderRequests);
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<OrderRequestDto> deleteOrderById(@PathVariable String orderId){
        OrderRequestDto deletedorder = orderRequestBusiness.deleteOrderById(orderId);
        return ResponseEntity.ok(deletedorder);
    }

}
