package com.stackroute.product.exception;

public class ProductDoesNotExistException extends  Exception {

    public ProductDoesNotExistException(String message) {
        super(message);
    }
}
