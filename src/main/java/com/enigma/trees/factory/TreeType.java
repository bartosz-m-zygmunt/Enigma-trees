package com.enigma.trees.factory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public enum TreeType {
    DECIDUOUS,
    CONIFEROUS,

    OAK("Oak"),
    MAPLE("Maple"),

    PINE("Pine"),
    SPRUCE("Spruce");

    private final String speciesName;

}
