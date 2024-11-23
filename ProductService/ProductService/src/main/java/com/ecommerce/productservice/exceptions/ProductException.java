package com.ecommerce.productservice.exceptions;


public class ProductException extends RuntimeException{

    public ProductException(){super("Resource Not Found Exception !!!");}

    public ProductException(String msg){
        super(msg);
    }

}
