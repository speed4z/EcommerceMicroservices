package com.ecommerce.inventory_service.business.impl;

import com.ecommerce.inventory_service.business.InventoryBusiness;
import com.ecommerce.inventory_service.dto.InventoryDto;
import com.ecommerce.inventory_service.entities.Inventory;
import com.ecommerce.inventory_service.exceptions.ResourceNotFoundException;
import com.ecommerce.inventory_service.repo.InventoryRepo;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class InventoryBusinessImpl implements InventoryBusiness {

    private final Logger logger = LoggerFactory.getLogger(InventoryBusinessImpl.class);

    private final InventoryRepo inventoryRepo;
    private final ModelMapper modelMapper;

    public InventoryBusinessImpl(InventoryRepo inventoryRepo,ModelMapper modelMapper){
        this.inventoryRepo = inventoryRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void clearInventory() {
        inventoryRepo.deleteAll();
    }

    @Override
    public boolean isProductAvailable(String productId) {
        return (inventoryRepo.findByProductId(productId)).isPresent();
    }

    @Override
    public InventoryDto getInventoryByProductId(String productId) {
        Inventory inventory = inventoryRepo.findByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product Id :" + productId + " NOT FOUND!"));
        logger.info("Inventory : " + inventory);
        return modelMapper.map(inventory, InventoryDto.class);
    }

    @Override
    public InventoryDto addProductStock(String productId, Integer quantity) {
        Inventory inventory = inventoryRepo.findByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product Id :" + productId + " NOT FOUND!"));
        inventory.setProductQuantity(inventory.getProductQuantity() + quantity);
        inventoryRepo.save(inventory);
        logger.info("Added to Inventory : " + inventory);
        return modelMapper.map(inventory, InventoryDto.class);
    }

    @Override
    public InventoryDto reduceProductStock(String productId, Integer quantity) {
        Inventory inventory = inventoryRepo.findByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product Id :" + productId + " NOT FOUND!"));
        inventory.setProductQuantity(inventory.getProductQuantity() - quantity);
        inventoryRepo.save(inventory);
        logger.info("Reduced from Inventory : " + inventory);
        return modelMapper.map(inventory, InventoryDto.class);
    }
}
