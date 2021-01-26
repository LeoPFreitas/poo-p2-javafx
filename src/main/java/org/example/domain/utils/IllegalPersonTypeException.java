package org.example.domain.utils;

public class IllegalPersonTypeException extends RuntimeException {
    public IllegalPersonTypeException(String message) {
        super(message);
    }
}
