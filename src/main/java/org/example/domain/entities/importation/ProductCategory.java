package org.example.domain.entities.importation;

import java.util.Arrays;

public enum ProductCategory {
    ELECTRONICS("Eletrônicos"),
    BOOKS("Livros"),
    CLOTHS("Vestimentas"),
    TOOLS("Ferramentas"),
    HOME_APPLIANCES("Eletrodomésticos");

    private final String label;

    ProductCategory(String label) {
        this.label = label;
    }

    public static ProductCategory toEnum(String value) {
        return Arrays.stream(ProductCategory.values())
                .filter(c -> value.equals(c.toString()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public String toString() {
        return label;
    }
}
