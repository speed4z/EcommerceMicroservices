package com.ecommerce.inventory_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class ProductDto {

    private String productId;
    private String name;
    private String type;
    private Integer price;
    private String Description;


}
