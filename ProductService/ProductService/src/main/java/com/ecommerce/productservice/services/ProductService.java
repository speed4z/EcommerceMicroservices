package com.ecommerce.productservice.services;

import com.ecommerce.productservice.dtos.ProductDTO;
import com.ecommerce.productservice.exceptions.ProductException;

import java.util.List;

public interface ProductService {
    ProductDTO save(ProductDTO product);

    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(String productId);
}
