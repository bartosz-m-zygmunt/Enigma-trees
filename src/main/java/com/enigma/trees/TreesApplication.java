package com.enigma.trees;

import com.enigma.trees.factory.TreeFactory;
import com.enigma.trees.factory.TreeType;
import com.enigma.trees.model.ConiferousTrees.PineTree;
import com.enigma.trees.model.ConiferousTrees.SpruceTree;
import com.enigma.trees.model.DecidousTrees.MapleTree;
import com.enigma.trees.model.DecidousTrees.OakTree;
import com.enigma.trees.model.Disease;
import com.enigma.trees.model.Seed;
import com.enigma.trees.model.Tree;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class TreesApplication {

    public static final double SEED_SPROUT_PROBABILITY_BY_DECIDUOS = 0.3;
    public static final double SEED_SPROUT_PROBABILITY_BY_CONIFEROUS = 0.2;
    public static final double SEED_SPROUT_PROBABILITY_BY_DEFAULT = 0.1;

    public static void main(String[] args) {
        SpringApplication.run(TreesApplication.class, args);

        TreeFactory factory = new TreeFactory();

        handleTreeLifecycle(factory, TreeType.OAK, 20);
        handleTreeLifecycle(factory, TreeType.MAPLE, 25);
        handleTreeLifecycle(factory, TreeType.SPRUCE, 25);
        handleTreeLifecycle(factory, TreeType.PINE, 25);
    }

    private static void handleTreeLifecycle(TreeFactory factory, TreeType type, int cycles) {
        Tree tree = factory.createTree(type);
        displayTreeHeader(type);
        tree.displayInfo();

        simulateTreeLifecycle(tree, factory, type, cycles);

        displayTreeFooter(type);
        tree.displayInfo();
    }

    private static void displayTreeHeader(TreeType type) {
        System.out.printf("\n%s Tree:\n", typeToDisplayName(type));
    }

    private static void displayTreeFooter(TreeType type) {
        System.out.printf("\n%s Tree Status After Time:\n", typeToDisplayName(type));
    }

    private static String typeToDisplayName(TreeType type) {
        return switch (type) {
            case OAK -> "Oak";
            case MAPLE -> "Maple";
            case SPRUCE -> "Spruce";
            case PINE -> "Pine";
            default -> "Unknown Tree";
        };
    }

    private static void simulateTreeLifecycle(Tree tree, TreeFactory factory, TreeType type, int cycles) {
        Random random = new Random();

        for (int i = 0; i < cycles; i++) {
            tree.grow();

            if (type == TreeType.OAK) {
                simulateOakCycleEvents((OakTree) tree, i);
            } else if (type == TreeType.MAPLE) {
                simulateMapleCycleEvents((MapleTree) tree, i);
            } else if (type == TreeType.SPRUCE) {
                simulateSpruceCycleEvents((SpruceTree) tree, i);
            } else if (type == TreeType.PINE) {
                simulatePineCycleEvents((PineTree) tree, i);
            }

            List<Seed> producedSeeds = tree.produceSeeds();
            attemptSeedGermination(producedSeeds, factory, random, type);
        }
    }

    private static void simulateOakCycleEvents(OakTree tree, int cycle) {
        if (cycle == 5) tree.shedLeaves();
        if (cycle == 8) tree.growLeaves();
        if (cycle == 10) tree.changeLeafColor("golden");
        if (cycle == 12) tree.reactToWeather("strong wind");
        if (cycle == 13) tree.getSick(Disease.POWDERY_MILDEW);
        if (cycle == 15) tree.reactToWeather("drought");
        if (cycle == 18 && tree.isAlive()) tree.die();
    }

    private static void simulateMapleCycleEvents(MapleTree tree, int cycle) {
        if (cycle == 5) tree.shedLeaves();
        if (cycle == 8) tree.growLeaves();
        if (cycle == 10) tree.changeLeafColor("orange");
        if (cycle == 12) tree.reactToWeather("strong wind");
        if (cycle == 13) tree.getSick(Disease.POWDERY_MILDEW);
        if (cycle == 15) tree.reactToWeather("drought");
        if (cycle == 18 && tree.isAlive()) tree.die();
    }

    private static void simulateSpruceCycleEvents(SpruceTree tree, int cycle) {
        if (cycle == 7) tree.shedNeedles();
        if (cycle == 8) tree.growNeedles();
        if (cycle == 10) tree.changeNeedleColor("blue-green");
        if (cycle == 15) tree.reactToWeather("drought");
        if (cycle == 22 && tree.isAlive()) tree.die();
    }

    private static void simulatePineCycleEvents(PineTree tree, int cycle) {
        if (cycle == 6) tree.shedNeedles();
        if (cycle == 9) tree.growNeedles();
        if (cycle == 12) tree.changeNeedleColor("yellow-green");
        if (cycle == 15) tree.reactToWeather("drought");
        if (cycle == 20 && tree.isAlive()) tree.die();
    }

    private static void attemptSeedGermination(List<Seed> seeds, TreeFactory factory, Random random, TreeType parentType) {
        for (Seed seed : seeds) {
            System.out.printf("Found a seed: %s\n", seed);
            if (shouldSeedSprout(random, parentType)) {
                Tree newTree = factory.createTree(seed.getTreeType());
                System.out.printf("A new tree grew from the seed:\n");
                newTree.displayInfo();
            } else {
                System.out.printf("The seed did not sprout.\n");
            }
        }
    }

    private static boolean shouldSeedSprout(Random random, TreeType type) {
        double sproutChance = switch (type) {
            case OAK, MAPLE -> SEED_SPROUT_PROBABILITY_BY_DECIDUOS;
            case SPRUCE, PINE -> SEED_SPROUT_PROBABILITY_BY_CONIFEROUS;
            default -> SEED_SPROUT_PROBABILITY_BY_DEFAULT;
        };
        return random.nextDouble() < sproutChance;
    }
}
