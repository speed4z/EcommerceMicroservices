package com.ecommerce.order.microservice.repository;

import com.ecommerce.order.microservice.entities.OrderRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRequestRepository extends JpaRepository<OrderRequest , String> {

    List<OrderRequest> findByUserId(@Param("userId") String userId);

    List<OrderRequest> findByInventoryId(@Param("inventoryId") String inventoryId);

    List<OrderRequest> findByOrderIdIn(List<String> orderIds);

}
