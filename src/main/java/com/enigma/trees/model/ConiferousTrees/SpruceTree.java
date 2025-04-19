package com.enigma.trees.model.ConiferousTrees;

import com.enigma.trees.factory.TreeType;
import com.enigma.trees.strategy.GrowthStrategy;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpruceTree extends ConiferousTree {

    private boolean hasBerries = false;

    public SpruceTree(GrowthStrategy growthStrategy) {
        super(growthStrategy);
    }

    public void growBerries() {
        if (isAlive && !hasBerries) {
            System.out.printf("Berries are growing on the spruce tree.%n");
            hasBerries = true;
        } else if (isAlive) {
            System.out.printf("The spruce tree already has berries.%n");
        } else {
            System.out.printf("A dead spruce tree does not grow berries.%n");
        }
    }

    public void dropBerries() {
        if (isAlive && hasBerries) {
            System.out.printf("Berries are falling from the spruce tree.%n");
        } else if (isAlive) {
            System.out.printf("The spruce tree does not have berries.%n");
        } else {
            System.out.printf("A dead spruce tree does not drop berries.%n");
        }
    }

    @Override
    public TreeType getTreeType() {
        return TreeType.SPRUCE;
    }
}
