package com.enigma.trees.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HealthStatus {
    HEALTHY("healthy"),
    WEAKENED_BY_DROUGHT("weakened by drought"),
    SICK("sick"),
    RECOVERING("recovering"),
    DEAD("dead");

    private final String description;

    @Override
    public String toString() {
        return description;
    }
}
