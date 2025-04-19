package com.enigma.trees.model.ConiferousTrees;

import com.enigma.trees.factory.TreeType;
import com.enigma.trees.strategy.GrowthStrategy;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PineTree extends ConiferousTree {

    private boolean hasPineCones = true;

    public PineTree(GrowthStrategy growthStrategy) {
        super(growthStrategy);
    }

    public void dropPineCones() {
        if (isAlive && hasPineCones) {
            System.out.printf("Pine cones are falling from the pine tree.%n");
        } else if (isAlive) {
            System.out.printf("The pine tree does not have pine cones.%n");
        } else {
            System.out.printf("A dead pine tree does not drop pine cones.%n");
        }
    }

    public void growPineCones() {
        if (isAlive && !hasPineCones) {
            System.out.printf("New pine cones are growing on the pine tree.%n");
            hasPineCones = true;
        } else if (isAlive) {
            System.out.printf("The pine tree already has pine cones.%n");
        } else {
            System.out.printf("A dead pine tree does not grow pine cones.%n");
        }
    }

    @Override
    public TreeType getTreeType() {
        return TreeType.PINE;
    }
}
