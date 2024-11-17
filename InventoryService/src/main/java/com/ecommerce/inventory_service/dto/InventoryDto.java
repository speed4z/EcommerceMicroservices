package com.ecommerce.inventory_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class InventoryDto {
	private String inventoryId;
	private String productId;
	private Integer productQuantity;
}
