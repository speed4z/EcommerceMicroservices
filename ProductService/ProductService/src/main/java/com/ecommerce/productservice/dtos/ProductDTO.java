package com.ecommerce.productservice.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Builder
public class ProductDTO {

    private Integer productId;
    private String name;
    private String type;
    private Double price;
    private String description;

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
