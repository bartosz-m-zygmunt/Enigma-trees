package com.enigma.trees.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Disease {
    POWDERY_MILDEW("powdery mildew"),
    RUST("rust"),
    DIEBACK("dieback");

    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
