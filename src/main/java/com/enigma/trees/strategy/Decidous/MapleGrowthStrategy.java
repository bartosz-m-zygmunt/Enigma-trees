package com.enigma.trees.strategy.Decidous;

import com.enigma.trees.model.Tree;
import com.enigma.trees.strategy.GrowthStrategy;

public class MapleGrowthStrategy implements GrowthStrategy {
    @Override
    public void grow(Tree tree) {
        System.out.println("The maple tree grows quickly with strong branches.");
    }
}
