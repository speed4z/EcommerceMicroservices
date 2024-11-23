package com.ecommerce.productservice.exceptions;

public class ProductExceptions extends Exception{
    private int code;
    private String message;

    ProductExceptions(int code, String message){
        this.code = code;
        this.message = message;
    }

    public ProductExceptions(String message){
        super(message);
    }
}
