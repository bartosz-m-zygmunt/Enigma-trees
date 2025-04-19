package com.enigma.trees.model.ConiferousTrees;

import com.enigma.trees.factory.TreeType;
import com.enigma.trees.model.TreeAbstract;
import com.enigma.trees.strategy.GrowthStrategy;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class ConiferousTree extends TreeAbstract {
    public static final double SHEDDING_NEEDLES_PROBABILITY = 0.05;
    private boolean hasNeedles = true;
    private boolean testForceShed = false;

    public ConiferousTree(GrowthStrategy growthStrategy) {
        super("tall coniferous trunk", "regular coniferous branches", "green needles", growthStrategy,
                TreeType.CONIFEROUS);
    }

    public void enableTestForceShed() {
        this.testForceShed = true;
    }

    public void shedNeedles() {
        if (isAlive && hasNeedles && sheddingNeedlesProbability()) {
            System.out.printf("Old needles are falling from the coniferous tree.%n");
        } else if (isAlive) {
            System.out.printf("The coniferous tree retains its needles.%n");
        } else {
            System.out.printf("A dead coniferous tree does not shed needles.%n");
        }
    }

    private boolean sheddingNeedlesProbability() {
        if (testForceShed) return true;
        return new Random().nextDouble() < SHEDDING_NEEDLES_PROBABILITY;
    }

    public void growNeedles() {
        if (isAlive && !hasNeedles) {
            System.out.printf("New green needles are sprouting on the coniferous tree.%n");
            foliage = "green needles";
            hasNeedles = true;
        } else if (isAlive) {
            System.out.printf("The coniferous tree already has needles.%n");
        } else {
            System.out.printf("A dead coniferous tree does not grow needles.%n");
        }
    }

    public void changeNeedleColor(String color) {
        if (isAlive && hasNeedles) {
            System.out.printf("The needles of the coniferous tree are changing color to %s.%n", color);
            foliage = color + " needles";
        } else if (isAlive) {
            System.out.printf("The coniferous tree has no needles, so they cannot change color.%n");
        } else {
            System.out.printf("A dead coniferous tree's needles do not change color.%n");
        }
    }

    public TreeType getTreeType() {
        return TreeType.CONIFEROUS;
    }
}