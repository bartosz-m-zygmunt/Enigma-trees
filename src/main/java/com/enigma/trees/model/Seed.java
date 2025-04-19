package com.enigma.trees.model;

import com.enigma.trees.factory.TreeType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Seed {
    private final TreeType treeType;

    @Override
    public String toString() {
        return "Seed of " + treeType.toString().toLowerCase();
    }
}