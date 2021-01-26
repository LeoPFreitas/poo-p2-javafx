package org.example.domain.entities.person;

import java.util.Arrays;

public enum PersonType {
    FISICA("Física"),
    JURIDICA("Jurídica");

    private final String label;

    PersonType(String label) {
        this.label = label;
    }

    public static PersonType toEnum(String value) {
        return Arrays.stream(PersonType.values())
                .filter(c -> value.equals(c.toString()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public String toString() {
        return label;
    }
}
