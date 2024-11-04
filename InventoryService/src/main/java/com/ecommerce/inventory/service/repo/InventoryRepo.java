package com.ecommerce.inventory.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.inventory.service.entities.Inventory;

public interface InventoryRepo extends JpaRepository<Inventory, String> {

}
