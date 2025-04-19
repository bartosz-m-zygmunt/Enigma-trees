package com.enigma.trees.strategy.Coinferous;

import com.enigma.trees.model.Tree;
import com.enigma.trees.strategy.GrowthStrategy;

public class SpruceGrowthStrategy implements GrowthStrategy {
    @Override
    public void grow(Tree tree) {
        System.out.println("The spruce tree grows tall with thick branches.");
    }
}
