package com.enigma.trees.strategy.Coinferous;

import com.enigma.trees.model.Tree;
import com.enigma.trees.strategy.GrowthStrategy;

public class PineGrowthStrategy implements GrowthStrategy {
    @Override
    public void grow(Tree tree) {
        System.out.println("The pine tree grows tall with long needles.");
    }
}
