package com.ecommerce.inventory_service.business;


import com.ecommerce.inventory_service.dto.InventoryDto;

public interface InventoryBusiness {

    void clearInventory();
    boolean isProductAvailable(String productId);
    InventoryDto getInventoryByProductId(String productId);
    InventoryDto addProductStock(String productId, Integer quantity);
    InventoryDto reduceProductStock(String productId, Integer quantity);

}
