package com.ecommerce.inventory_service;

import com.ecommerce.inventory_service.business.InventoryBusiness;
import com.ecommerce.inventory_service.dto.InventoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryBusiness inventoryBusiness;

    public InventoryController(InventoryBusiness inventoryBusiness){
        this.inventoryBusiness = inventoryBusiness;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<InventoryDto> getInventoryByProduct(@PathVariable String productId){
        InventoryDto inventoryDto = inventoryBusiness.getInventoryByProductId(productId);
        return ResponseEntity.ok(inventoryDto);
    }

    @GetMapping("/isAvailable/{productId}")
    public ResponseEntity<Boolean> isProductAvailable(@PathVariable String productId){
        boolean isAvailable = inventoryBusiness.isProductAvailable(productId);
        return ResponseEntity.ok(isAvailable);
    }

    @PostMapping("/{productId}/add")
    public ResponseEntity<InventoryDto> addProductStock(@PathVariable String productId,@RequestParam Integer quantity){
        InventoryDto inventoryDto = inventoryBusiness.addProductStock(productId,quantity);
        return ResponseEntity.ok(inventoryDto);
    }

    @PostMapping("/{productId}/reduce")
    public ResponseEntity<InventoryDto> reduceProductStock(@PathVariable String productId,@RequestParam Integer quantity){
        InventoryDto inventoryDto = inventoryBusiness.reduceProductStock(productId,quantity);
        return ResponseEntity.ok(inventoryDto);
    }

}
