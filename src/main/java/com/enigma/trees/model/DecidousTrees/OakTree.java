package com.enigma.trees.model.DecidousTrees;

import com.enigma.trees.factory.TreeType;
import com.enigma.trees.model.TreeAbstract;
import com.enigma.trees.strategy.GrowthStrategy;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OakTree extends DeciduousTree {
    private boolean hasAcorns = false;

    public OakTree(GrowthStrategy growthStrategy) {
        super(growthStrategy);
    }

    public void growLeaves() {
        if (isAlive && !foliage.equals("green leaves")) {
            System.out.printf("New green leaves are sprouting on the oak tree.%n");
            foliage = "green leaves";
        } else if (isAlive) {
            System.out.printf("The oak tree already has green leaves.%n");
        } else {
            System.out.printf("A dead oak tree does not grow leaves.%n");
        }
    }

    public void shedLeaves() {
        if (isAlive) {
            System.out.println("The oak tree sheds its leaves in autumn.");
            foliage = "no leaves";
        } else {
            System.out.println("A dead oak tree does not shed leaves.");
        }
    }

    public void growAcorns() {
        if (isAlive && !hasAcorns) {
            System.out.printf("Acorns are growing on the oak tree.%n");
            hasAcorns = true;
        } else if (isAlive) {
            System.out.printf("The oak tree already has acorns.%n");
        } else {
            System.out.printf("A dead oak tree does not grow acorns.%n");
        }
    }

    public void dropAcorns() {
        if (isAlive && hasAcorns) {
            System.out.printf("Acorns are falling from the oak tree.%n");
        } else if (isAlive) {
            System.out.printf("The oak tree does not have acorns.%n");
        } else {
            System.out.printf("A dead oak tree does not drop acorns.%n");
        }
    }

    @Override
    public TreeType getTreeType() {
        return TreeType.OAK;
    }
}

