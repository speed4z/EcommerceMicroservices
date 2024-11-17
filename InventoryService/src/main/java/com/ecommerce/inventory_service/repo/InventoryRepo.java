package com.ecommerce.inventory_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.inventory_service.entities.Inventory;

import java.util.Optional;

public interface InventoryRepo extends JpaRepository<Inventory, String> {

    Optional<Inventory> findByProductId(String productId);

}
