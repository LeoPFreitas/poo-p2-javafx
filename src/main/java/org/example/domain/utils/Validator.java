package org.example.domain.utils;

import java.util.Collection;

public abstract class Validator<T> {
    public static boolean nullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public static boolean nullOrEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public abstract Notification validate(T type);
}
