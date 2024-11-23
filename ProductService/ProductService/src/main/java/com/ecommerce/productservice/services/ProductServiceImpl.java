package com.ecommerce.productservice.services;

import com.ecommerce.productservice.adapters.mappers.ProductOutputMapper;
import com.ecommerce.productservice.dtos.ProductDTO;
import com.ecommerce.productservice.exceptions.ProductExceptions;
import com.ecommerce.productservice.repositories.ProductRepository;
import com.ecommerce.productservice.entities.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public ProductDTO getProductById(int id) throws ProductExceptions {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductExceptions("Product with ID :" + id + " not found"));
        info("Requested Product: " + product);
        return ProductOutputMapper.INSTANCE.mapToModel(product);
    }

//    @Override
//    public List<ProductDTO> getAllProducts(){
//        return productRepository.findAll();
//    }

}
