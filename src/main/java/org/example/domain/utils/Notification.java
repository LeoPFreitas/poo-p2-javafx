package org.example.domain.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Notification {

    private final List<Error> errors = new ArrayList<>();

    public void addError(String message) {
        addError(message, null);
    }

    public void addError(String message, Exception e) {
        errors.add(new Error(message, e));
    }

    public boolean isCorrect() {
        return errors.isEmpty();
    }

    public boolean hasErrors() {
        return !isCorrect();
    }

    public String errorMessage() {
        return errors.stream()
                .map(e -> e.message)
                .collect(Collectors.joining(", "));
    }

    private static class Error {
        String message;
        Exception cause;

        public Error(String message, Exception cause) {
            this.message = message;
            this.cause = cause;
        }
    }
}
