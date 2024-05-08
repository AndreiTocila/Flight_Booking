package com.project.paypal.exception;

public class OrderNotPayedException extends RuntimeException {
    public OrderNotPayedException(String errorMessage) {
        super(errorMessage);
    }

}