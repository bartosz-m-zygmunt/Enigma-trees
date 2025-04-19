package com.enigma.trees.strategy.Coinferous;

import com.enigma.trees.model.Tree;
import com.enigma.trees.strategy.GrowthStrategy;

import java.util.Random;

public class ConiferousGrowthStrategy implements GrowthStrategy {
    private final Random random = new Random();

    @Override
    public void grow(Tree tree) {
        System.out.printf("%s grows, adding new needles.%n", tree.getClass().getSimpleName());
        if (random.nextDouble() < 0.2) {
            // Coniferous trees grow needles continuously but slower
        }
        if (random.nextDouble() < 0.08 && tree.isAlive()) {
            tree.increaseTrunkDiameter(0.005);
        }
        if (random.nextDouble() < 0.03 && tree.getAge() > 8 && tree.isAlive()) {
            tree.addBranch();
        }
    }
}
