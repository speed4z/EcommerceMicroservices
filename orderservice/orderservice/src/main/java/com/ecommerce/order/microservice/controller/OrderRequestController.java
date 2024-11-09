package com.ecommerce.order.microservice.controller;

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
    public ResponseEntity<OrderRequest> placeOrder(@RequestBody OrderRequest orderRequest){
        orderRequestBusiness.placeOrder(orderRequest);
        return ResponseEntity.ok(orderRequest);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderRequest> getOrderById(@PathVariable String orderId){
        OrderRequest orderRequestResponse = orderRequestBusiness.getOrderById(orderId);
        return ResponseEntity.ok(orderRequestResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderRequest>> getAllOrders(){
        List<OrderRequest> orderRequests = orderRequestBusiness.listAllOrders();
        return ResponseEntity.ok(orderRequests);
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<OrderRequest> deleteOrderById(@PathVariable String orderId){
        OrderRequest orderRequestResponse = orderRequestBusiness.deleteOrderById(orderId);
        return ResponseEntity.ok(orderRequestResponse);
    }

}
