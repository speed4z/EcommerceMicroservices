package com.ecommerce.productservice.adapters.mappers;

import com.ecommerce.productservice.dtos.ProductDTO;
import com.ecommerce.productservice.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductOutputMapper {

    ProductOutputMapper INSTANCE = Mappers.getMapper(ProductOutputMapper.class);
    ProductDTO mapToModel(Product product);

    Product mapFromModel(ProductDTO product);

}
