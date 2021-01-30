package org.example.domain.utils;

public class IllegalDateRangeException extends RuntimeException {
    public IllegalDateRangeException(String message) {
        super(message);
    }
}
