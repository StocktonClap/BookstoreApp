package com.ms.exceptions;

public class UserPaymentNotFoundException extends Throwable {
    public UserPaymentNotFoundException(String message) {
        super(message);
    }
}
