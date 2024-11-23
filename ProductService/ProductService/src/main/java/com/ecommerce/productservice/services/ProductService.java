package com.ecommerce.productservice.services;

import com.ecommerce.productservice.dtos.ProductDTO;
import com.ecommerce.productservice.entities.Product;
import com.ecommerce.productservice.exceptions.ProductExceptions;

import java.util.List;

public interface ProductService {
    ProductDTO save(ProductDTO product);

//    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(int id) throws ProductExceptions;
}
