package com.enigma.trees;

import com.enigma.trees.factory.TreeFactory;
import com.enigma.trees.factory.TreeType;
import com.enigma.trees.model.ConiferousTrees.ConiferousTree;
import com.enigma.trees.model.ConiferousTrees.PineTree;
import com.enigma.trees.model.ConiferousTrees.SpruceTree;
import com.enigma.trees.model.DecidousTrees.DeciduousTree;
import com.enigma.trees.model.DecidousTrees.MapleTree;
import com.enigma.trees.model.DecidousTrees.OakTree;
import com.enigma.trees.model.Disease;
import com.enigma.trees.model.HealthStatus;
import com.enigma.trees.model.Seed;
import com.enigma.trees.model.TreeAbstract;
import com.enigma.trees.strategy.Coinferous.ConiferousGrowthStrategy;
import com.enigma.trees.strategy.Decidous.DeciduousGrowthStrategy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TreesApplicationTests {

    private TreeFactory treeFactory = new TreeFactory();

    private OakTree createOakTree() {
        return (OakTree) treeFactory.createTree(TreeType.OAK);
    }

    private MapleTree createMapleTree() {
        return (MapleTree) treeFactory.createTree(TreeType.MAPLE);
    }

    private SpruceTree createSpruceTree() {
        return (SpruceTree) treeFactory.createTree(TreeType.SPRUCE);
    }

    private PineTree createPineTree() {
        return (PineTree) treeFactory.createTree(TreeType.PINE);
    }

    private DeciduousTree createDeciduousTree() {
        return (DeciduousTree) treeFactory.createTree(TreeType.DECIDUOUS);
    }

    private ConiferousTree createConiferousTree() {
        return (ConiferousTree) treeFactory.createTree(TreeType.CONIFEROUS);
    }

    @Test
    void testOakTreeGrowth() {
        OakTree tree = createOakTree();
        int age = tree.getAge();
        double diameter = tree.getTrunkDiameter();
        int branches = tree.getBranchList().size();

        tree.grow();

        assertAll("Oak tree after growth", () -> assertTrue(tree.getAge() > age), () -> assertTrue(tree.getTrunkDiameter() >= diameter), () -> assertTrue(tree.getBranchList().size() >= branches), () -> assertTrue(tree.isAlive()), () -> assertEquals(HealthStatus.HEALTHY, tree.getHealthStatus()));
    }

    @Test
    void testMapleTreeGrowth() {
        MapleTree tree = createMapleTree();
        int age = tree.getAge();
        double diameter = tree.getTrunkDiameter();

        tree.grow();

        assertAll("Maple tree after growth", () -> assertTrue(tree.getAge() > age), () -> assertTrue(tree.getTrunkDiameter() >= diameter), () -> assertTrue(tree.isAlive()), () -> assertEquals(HealthStatus.HEALTHY, tree.getHealthStatus()));
    }

    @Test
    void testSpruceTreeGrowth() {
        SpruceTree tree = createSpruceTree();
        int age = tree.getAge();
        double diameter = tree.getTrunkDiameter();
        int branches = tree.getBranchList().size();

        tree.grow();

        assertAll("Spruce tree after growth", () -> assertTrue(tree.getAge() > age), () -> assertTrue(tree.getTrunkDiameter() >= diameter), () -> assertTrue(tree.getBranchList().size() >= branches), () -> assertTrue(tree.isAlive()), () -> assertEquals(HealthStatus.HEALTHY, tree.getHealthStatus()));
    }

    @Test
    void testPineTreeGrowth() {
        PineTree tree = createPineTree();
        int age = tree.getAge();
        double diameter = tree.getTrunkDiameter();

        tree.grow();

        assertAll("Pine tree after growth", () -> assertTrue(tree.getAge() > age), () -> assertTrue(tree.getTrunkDiameter() >= diameter), () -> assertTrue(tree.isAlive()), () -> assertEquals(HealthStatus.HEALTHY, tree.getHealthStatus()));
    }

    @Test
    void testDeciduousTreeShedLeavesAndGrowBack() {
        DeciduousTree tree = new DeciduousTree(new DeciduousGrowthStrategy());
        tree.enableTestForceShed();

        assertTrue(tree.isHasLeaves());
        String initialFoliage = tree.getFoliage();

        tree.shedLeaves();

        assertAll("After shedding leaves", () -> assertFalse(tree.isHasLeaves()), () -> assertNotEquals(initialFoliage, tree.getFoliage()), () -> assertEquals("bare branches", tree.getFoliage()));

        tree.growLeaves();

        assertAll("After growing leaves", () -> assertTrue(tree.isHasLeaves()), () -> assertEquals("green leaves", tree.getFoliage()));
    }

    @Test
    void testConiferousTreeShedNeedlesAndStayGreen() {
        ConiferousTree tree = new ConiferousTree(new ConiferousGrowthStrategy());

        assertTrue(tree.isAlive());
        assertEquals("green needles", tree.getFoliage());

        String initialFoliage = tree.getFoliage();

        tree.shedNeedles();

        assertAll("After attempting to shed needles", () -> assertEquals(initialFoliage, tree.getFoliage(), "Needles should not be shed"), () -> assertTrue(tree.isAlive()));

        tree.grow();

        assertAll("After growing", () -> assertEquals("green needles", tree.getFoliage()), () -> assertTrue(tree.isAlive()));
    }

    @Test
    void testTreeReactToStrongWind() {
        DeciduousTree deciduousTree = createDeciduousTree();
        for (int i = 0; i < 5; i++) deciduousTree.grow();
        int initialDeciduousBranches = deciduousTree.getBranchList().size();

        deciduousTree.reactToWeather("strong wind");

        assertAll("Deciduous tree after wind", () -> assertTrue(deciduousTree.getBranchList().size() <= initialDeciduousBranches), () -> assertTrue(deciduousTree.isAlive()));

        ConiferousTree coniferousTree = createConiferousTree();
        for (int i = 0; i < 5; i++) coniferousTree.grow();
        int initialConiferousBranches = coniferousTree.getBranchList().size();

        coniferousTree.reactToWeather("strong wind");

        assertAll("Coniferous tree after wind", () -> assertTrue(coniferousTree.getBranchList().size() <= initialConiferousBranches), () -> assertTrue(coniferousTree.isAlive()));
    }

    @Test
    void testTreeGetSickAndRecover() {
        DeciduousTree tree = createDeciduousTree();

        assertEquals(HealthStatus.HEALTHY, tree.getHealthStatus());

        tree.getSick(Disease.POWDERY_MILDEW);
        assertAll("Tree after getting sick", () -> assertEquals(HealthStatus.SICK, tree.getHealthStatus()), () -> assertTrue(tree.isAlive()));

        tree.recover();
        assertAll("Tree after recovery", () -> assertEquals(HealthStatus.HEALTHY, tree.getHealthStatus()), () -> assertTrue(tree.isAlive()));
    }

    @Test
    void testTreeDie() {
        DeciduousTree tree = createDeciduousTree();

        tree.die();

        assertAll("Tree after dying", () -> assertFalse(tree.isAlive()), () -> assertEquals(HealthStatus.DEAD, tree.getHealthStatus()), () -> assertEquals("dead foliage", tree.getFoliage()), () -> assertEquals("dead branches", tree.getBranches()), () -> assertEquals("dead trunk", tree.getTrunk()));

        int ageAfterDeath = tree.getAge();
        tree.grow();

        assertEquals(ageAfterDeath, tree.getAge(), "Dead tree should not grow.");

        tree.shedLeaves(); // Should not change
        assertEquals("dead foliage", tree.getFoliage(), "Dead tree should not shed leaves.");
    }

    @Test
    void testTreeFactoryCreation() {
        assertAll("Tree factory creations", () -> assertNotNull(treeFactory.createTree(TreeType.DECIDUOUS)), () -> assertInstanceOf(DeciduousTree.class, treeFactory.createTree(TreeType.DECIDUOUS)), () -> assertNotNull(treeFactory.createTree(TreeType.CONIFEROUS)), () -> assertInstanceOf(ConiferousTree.class, treeFactory.createTree(TreeType.CONIFEROUS)), () -> assertNotNull(treeFactory.createTree(TreeType.OAK)), () -> assertInstanceOf(OakTree.class, treeFactory.createTree(TreeType.OAK)), () -> assertNotNull(treeFactory.createTree(TreeType.MAPLE)), () -> assertInstanceOf(MapleTree.class, treeFactory.createTree(TreeType.MAPLE)), () -> assertNotNull(treeFactory.createTree(TreeType.PINE)), () -> assertInstanceOf(PineTree.class, treeFactory.createTree(TreeType.PINE)), () -> assertNotNull(treeFactory.createTree(TreeType.SPRUCE)), () -> assertInstanceOf(SpruceTree.class, treeFactory.createTree(TreeType.SPRUCE)), () -> assertThrows(IllegalArgumentException.class, () -> treeFactory.createTree(null)));
    }

    @Test
    void testOakSpecificFeatures() {
        OakTree oakTree = createOakTree();
        assertFalse(oakTree.isHasAcorns());
        oakTree.growAcorns();
        assertTrue(oakTree.isHasAcorns());
        oakTree.dropAcorns(); // Just prints a message, no state change in this basic simulation
        assertEquals("green leaves", oakTree.getFoliage());
        oakTree.shedLeaves();
        assertEquals("no leaves", oakTree.getFoliage());
        oakTree.growLeaves();
        assertEquals("green leaves", oakTree.getFoliage());
    }

    @Test
    void testMapleSpecificFeatures() {
        MapleTree mapleTree = createMapleTree();
        assertFalse(mapleTree.isHasSeeds());
        mapleTree.growSeeds();
        assertTrue(mapleTree.isHasSeeds());
        mapleTree.dropSeeds(); // Just prints a message
        assertEquals("green leaves", mapleTree.getFoliage());
        mapleTree.shedLeaves();
        assertEquals("no leaves", mapleTree.getFoliage());
        mapleTree.growLeaves();
        assertEquals("green leaves", mapleTree.getFoliage());
    }

    @Test
    void testSpruceSpecificFeatures() {
        SpruceTree spruceTree = createSpruceTree();
        assertFalse(spruceTree.isHasBerries());
        spruceTree.growBerries();
        assertTrue(spruceTree.isHasBerries());
        spruceTree.dropBerries(); // Just prints a message
        assertEquals("green needles", spruceTree.getFoliage());
        spruceTree.shedNeedles(); // Probability-based, so we just check it's alive
        assertTrue(spruceTree.isAlive());
        spruceTree.growNeedles(); // Basic implementation just prints
        spruceTree.changeNeedleColor("blue-green");
        assertEquals("blue-green needles", spruceTree.getFoliage());
    }

    @Test
    void testPineSpecificFeatures() {
        PineTree pineTree = createPineTree();
        assertTrue(pineTree.isHasPineCones()); // Default is true
        pineTree.dropPineCones(); // Just prints a message
        assertEquals("green needles", pineTree.getFoliage());
        pineTree.shedNeedles(); // Probability-based, just check it's alive
        assertTrue(pineTree.isAlive());
        pineTree.growNeedles(); // Basic implementation just prints
        pineTree.changeNeedleColor("yellow-green");
        assertEquals("yellow-green needles", pineTree.getFoliage());
    }

    @Test
    void testSeedProduction() {
        OakTree oakTree = createOakTree();
        for (int i = 0; i < TreeAbstract.TREE_AGE_ELIGIBLE_FOR_SEED_PRODUCTION + 1; i++) {
            oakTree.increaseAge();
        }
        List<Seed> seeds = oakTree.produceSeeds();
        assertNotNull(seeds);
    }
}