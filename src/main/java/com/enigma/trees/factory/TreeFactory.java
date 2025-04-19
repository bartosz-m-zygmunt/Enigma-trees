package com.enigma.trees.factory;

import com.enigma.trees.model.ConiferousTrees.ConiferousTree;
import com.enigma.trees.model.ConiferousTrees.PineTree;
import com.enigma.trees.model.ConiferousTrees.SpruceTree;
import com.enigma.trees.model.DecidousTrees.DeciduousTree;
import com.enigma.trees.model.DecidousTrees.MapleTree;
import com.enigma.trees.model.DecidousTrees.OakTree;
import com.enigma.trees.model.Tree;
import com.enigma.trees.strategy.Coinferous.ConiferousGrowthStrategy;
import com.enigma.trees.strategy.Coinferous.PineGrowthStrategy;
import com.enigma.trees.strategy.Coinferous.SpruceGrowthStrategy;
import com.enigma.trees.strategy.Decidous.DeciduousGrowthStrategy;
import com.enigma.trees.strategy.Decidous.MapleGrowthStrategy;
import com.enigma.trees.strategy.Decidous.OakGrowthStrategy;
import com.enigma.trees.strategy.GrowthStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TreeFactory {
    private final Map<TreeType, GrowthStrategy> growthStrategies = new HashMap<>();

    public TreeFactory() {
        registerGrowthStrategy(TreeType.DECIDUOUS, new DeciduousGrowthStrategy());
        registerGrowthStrategy(TreeType.CONIFEROUS, new ConiferousGrowthStrategy());
        registerGrowthStrategy(TreeType.OAK, new OakGrowthStrategy());
        registerGrowthStrategy(TreeType.MAPLE, new MapleGrowthStrategy());
        registerGrowthStrategy(TreeType.PINE, new PineGrowthStrategy());
        registerGrowthStrategy(TreeType.SPRUCE, new SpruceGrowthStrategy());
    }

    public void registerGrowthStrategy(TreeType type, GrowthStrategy strategy) {
        growthStrategies.put(type, strategy);
    }

    public Tree createTree(TreeType type) {
        GrowthStrategy strategy = Optional.ofNullable(growthStrategies.get(type))
                .orElseThrow(() -> new IllegalArgumentException("No growth strategy for type: " + type));

        return switch (type) {
            case OAK -> new OakTree(strategy);
            case MAPLE -> new MapleTree(strategy);
            case PINE -> new PineTree(strategy);
            case SPRUCE -> new SpruceTree(strategy);
            case DECIDUOUS -> new DeciduousTree(strategy);
            case CONIFEROUS -> new ConiferousTree(strategy);
            default -> throw new IllegalArgumentException("Unknown tree type: " + type);
        };
    }
}