package com.ecommerce.order.service.controller;

import com.ecommerce.order.service.business.OrderRequestBusiness;
import com.ecommerce.order.service.entities.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Optional<OrderRequest> orderRequestResponse = orderRequestBusiness.getOrderById(orderId);
        return orderRequestResponse.map(ResponseEntity::ok).orElseGet(() -> (ResponseEntity<OrderRequest>) ResponseEntity.notFound());
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderRequest>> getAllOrders(){
        List<OrderRequest> orderRequests = orderRequestBusiness.listAllOrders();
        return ResponseEntity.ok(orderRequests);
    }


}
