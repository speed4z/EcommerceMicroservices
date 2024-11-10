package com.ecommerce.order.microservice.controller;

import com.ecommerce.order.microservice.dto.InventoryDto;
import com.ecommerce.order.microservice.dto.OrderRequestDto;
import com.ecommerce.order.microservice.dto.ProductDto;
import com.ecommerce.order.microservice.dto.UserDto;
import com.ecommerce.order.microservice.services.OrderRequestBusiness;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("order-request")
public class OrderRequestController {

    private final OrderRequestBusiness orderRequestBusiness;

    public OrderRequestController(OrderRequestBusiness orderRequestBusiness){
        this.orderRequestBusiness = orderRequestBusiness;
    }

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
        OrderRequestDto deletedOrder = orderRequestBusiness.deleteOrderById(orderId);
        return ResponseEntity.ok(deletedOrder);
    }

//    List<OrderRequest> listAllOrdersByUserId(String userId);
    @PostMapping("/user-orders")
    public ResponseEntity<List<OrderRequestDto>> getAllOrdersForUser(@RequestBody UserDto user){
        List<OrderRequestDto> orderRequests = orderRequestBusiness.listAllOrdersByUserId(user.getUserId());
        return ResponseEntity.ok(orderRequests);
    }

    @PostMapping("/inventory-orders")
    public ResponseEntity<List<OrderRequestDto>> getAllOrdersForInventory(@RequestBody InventoryDto inventoryDto){
        List<OrderRequestDto> orderRequests = orderRequestBusiness.listAllOrdersByInventoryId(inventoryDto.getInventoryId());
        return ResponseEntity.ok(orderRequests);
    }

    @PostMapping("/orders-with-products")
    public ResponseEntity<List<OrderRequestDto>> getAllOrdersForInventory(@RequestBody List<ProductDto> productDtoList){
        List<OrderRequestDto> orderRequests = orderRequestBusiness.
                listAllOrdersHavingProductIds(
                        productDtoList.stream()
                                .map(ProductDto::getProductId)
                                .collect(Collectors.toList())
                );
        return ResponseEntity.ok(orderRequests);
    }
}
