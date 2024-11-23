package com.ecommerce.productservice.services;

import com.ecommerce.productservice.adapters.mappers.ProductOutputMapper;
import com.ecommerce.productservice.dtos.ProductDTO;
import com.ecommerce.productservice.exceptions.ProductException;
import com.ecommerce.productservice.repositories.ProductRepository;
import com.ecommerce.productservice.entities.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.helpers.Reporter.info;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDTO save(ProductDTO product) {
        Product mappedProduct = ProductOutputMapper.INSTANCE.mapFromModel(product);
        info("Product saved! " + productRepository.save(mappedProduct));
        return product;
    }

    @Override
    public ProductDTO getProductById(String productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("Product with ID :" + productId + " not found"));
        info("Requested Product: " + product);
        return ProductOutputMapper.INSTANCE.mapToModel(product);
    }

    @Override
    public List<ProductDTO> getAllProducts(){
        List<ProductDTO> productDTOs = new ArrayList<>();
        List<Product>  listProducts = productRepository.findAll();
        info("All Products : "+listProducts);
        for(Product p : listProducts){
            productDTOs.add(ProductOutputMapper.INSTANCE.mapToModel(p));
        }
        return productDTOs;
    }

}
