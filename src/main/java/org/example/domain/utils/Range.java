package org.example.domain.utils;

abstract class Range<T> {
    private T start;
    private T end;

    abstract boolean includes(T range);
}
