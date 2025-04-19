package com.enigma.trees.strategy.Decidous;

import com.enigma.trees.model.DecidousTrees.DeciduousTree;
import com.enigma.trees.model.Tree;
import com.enigma.trees.strategy.GrowthStrategy;

import java.util.Random;

public class DeciduousGrowthStrategy implements GrowthStrategy {
    private final Random random = new Random();

    @Override
    public void grow(Tree tree) {
        System.out.printf("%s grows, adding new leaves.%n", tree.getClass().getSimpleName());
        if (random.nextDouble() < 0.3) {
            ((DeciduousTree) tree).growLeaves();
        }
        if (random.nextDouble() < 0.1 && tree.isAlive()) {
            tree.increaseTrunkDiameter(0.01);
        }
        if (random.nextDouble() < 0.05 && tree.getAge() > 5 && tree.isAlive()) {
            tree.addBranch();
        }
    }
}
