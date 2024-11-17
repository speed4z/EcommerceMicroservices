package com.ecommerce.inventory_service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {
	
	@Id
	private String inventoryId;
	private String productId;
	private Integer productQuantity;
}
