package com.enigma.trees.model.DecidousTrees;

import com.enigma.trees.factory.TreeType;
import com.enigma.trees.model.TreeAbstract;
import com.enigma.trees.strategy.GrowthStrategy;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class DeciduousTree extends TreeAbstract {
    public static final double SHEDDING_LEAVES_PROBABILITY = 0.05;
    private boolean hasLeaves = true;
    private boolean testForceShed = false;

    public DeciduousTree(GrowthStrategy growthStrategy) {
        super("thick deciduous trunk", "branching deciduous branches", "green leaves", growthStrategy,
                TreeType.DECIDUOUS);
    }

    public void enableTestForceShed() {
        this.testForceShed = true;
    }

    public void shedLeaves() {
        if (isAlive && hasLeaves && shedLeavesProbability()) {
            System.out.printf("%s are falling from the deciduous tree.%n", foliage);
            foliage = "bare branches";
            hasLeaves = false;
        } else if (isAlive) {
            System.out.printf("The deciduous tree has no leaves to shed.%n");
        } else {
            System.out.printf("A dead tree does not shed leaves.%n");
        }
    }

    private boolean shedLeavesProbability() {
        if (testForceShed) return true;
        return new Random().nextDouble() < SHEDDING_LEAVES_PROBABILITY;
    }

    public void growLeaves() {
        if (isAlive && !hasLeaves) {
            System.out.printf("New green leaves are appearing on the deciduous tree.%n");
            foliage = "green leaves";
            hasLeaves = true;
        } else if (isAlive) {
            System.out.printf("The deciduous tree already has leaves.%n");
        } else {
            System.out.printf("A dead tree does not grow leaves.%n");
        }
    }

    public void changeLeafColor(String color) {
        if (isAlive && hasLeaves) {
            System.out.printf("The leaves of the deciduous tree are changing color to %s.%n", color);
            foliage = color + " leaves";
        } else if (isAlive) {
            System.out.printf("The deciduous tree has no leaves, so they cannot change color.%n");
        } else {
            System.out.printf("A dead tree's leaves do not change color.%n");
        }
    }

    public TreeType getTreeType() {
        return TreeType.DECIDUOUS;
    }
}
