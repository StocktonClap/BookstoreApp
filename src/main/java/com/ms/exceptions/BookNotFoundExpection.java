package com.ms.exceptions;

public class BookNotFoundExpection extends Throwable {
    public BookNotFoundExpection(String message) {
        super(message);
    }
}
