package com.ecommerce.order.microservice.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.HashMap;

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
