package com.enigma.trees.model.DecidousTrees;

import com.enigma.trees.factory.TreeType;
import com.enigma.trees.strategy.GrowthStrategy;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapleTree extends DeciduousTree {
    private boolean hasSeeds = false;

    public MapleTree(GrowthStrategy growthStrategy) {
        super(growthStrategy);
    }

    public void growLeaves() {
        if (isAlive && !foliage.equals("green leaves")) {
            System.out.printf("New green leaves are sprouting on the maple tree.%n");
            foliage = "green leaves";
        } else if (isAlive) {
            System.out.printf("The maple tree already has green leaves.%n");
        } else {
            System.out.printf("A dead maple tree does not grow leaves.%n");
        }
    }

    public void shedLeaves() {
        if (isAlive) {
            System.out.println("The maple tree sheds its leaves in autumn.");
            foliage = "no leaves";
        } else {
            System.out.println("A dead maple tree does not shed leaves.");
        }
    }

    public void growSeeds() {
        if (isAlive && !hasSeeds) {
            System.out.printf("Seeds are growing on the maple tree.%n");
            hasSeeds = true;
        } else if (isAlive) {
            System.out.printf("The maple tree already has seeds.%n");
        } else {
            System.out.printf("A dead maple tree does not grow seeds.%n");
        }
    }

    public void dropSeeds() {
        if (isAlive && hasSeeds) {
            System.out.printf("Seeds are falling from the maple tree.%n");
        } else if (isAlive) {
            System.out.printf("The maple tree does not have seeds.%n");
        } else {
            System.out.printf("A dead maple tree does not drop seeds.%n");
        }
    }

    @Override
    public TreeType getTreeType() {
        return TreeType.MAPLE;
    }
}
