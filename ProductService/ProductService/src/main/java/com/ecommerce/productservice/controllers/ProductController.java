package com.ecommerce.productservice.controllers;

import com.ecommerce.productservice.dtos.ProductDTO;
import com.ecommerce.productservice.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productServiceImpl;

    ProductController(ProductService productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @GetMapping("/testsanity")
    public ResponseEntity<String> sanity(){
        return ResponseEntity.ok("Project is up and running!");
    }

    @PostMapping("/saveproduct")
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO product){
        return ResponseEntity.ok(productServiceImpl.save(product));
    }

    @GetMapping("/products")
    public List<ProductDTO> getAllProducts(){
        return productServiceImpl.getAllProducts();
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable String productId) {
        return ResponseEntity.ok(productServiceImpl.getProductById(productId));
    }
}