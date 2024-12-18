package com.ecommerce.order.microservice.repository;

import com.ecommerce.order.microservice.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, String> {

    List<OrderItem> findByProductId(@Param("productId") String productId);

    List<OrderItem> findByProductIdIn(List<String> productIds);

}
