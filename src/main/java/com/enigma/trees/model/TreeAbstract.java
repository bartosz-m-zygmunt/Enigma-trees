package com.enigma.trees.model;

import com.enigma.trees.factory.TreeType;
import com.enigma.trees.strategy.GrowthStrategy;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@ToString
public abstract class TreeAbstract implements Tree {
    public static final double REACTION_TO_DROUGHT_PROBABILITY = 0.2;
    public static final double DEATH_BY_DROUGHT_PROBABILITY = 0.05;
    public static final double REACTION_TO_STRONG_WIND_PROBABILITY = 0.1;
    public static final int MAX_NEW_SEEDS_COUNT = 3;
    public static final int MIN_NEW_SEEDS_COUNT = 1;
    public static final double PRODUCTION_OF_SEEDS_PROBABILITY = 0.15;
    public static final int TREE_AGE_ELIGIBLE_FOR_SEED_PRODUCTION = 10;

    protected String trunk;
    protected String branches;
    protected String foliage;
    protected GrowthStrategy growthStrategy;
    protected int age;
    protected double trunkDiameter;
    protected List<String> branchList;
    protected boolean isAlive;
    protected HealthStatus healthStatus;
    protected TreeType treeType;

    protected TreeAbstract(String trunk, String branches, String foliage, GrowthStrategy growthStrategy, TreeType treeType) {
        this.trunk = trunk;
        this.branches = branches;
        this.foliage = foliage;
        this.growthStrategy = growthStrategy;
        this.age = 0;
        this.trunkDiameter = 0.1;
        this.branchList = new ArrayList<>();
        this.isAlive = true;
        this.healthStatus = HealthStatus.HEALTHY;
        this.treeType = treeType;
    }

    @Override
    public void displayInfo() {
        printAge();
        printHealthStatus();
        printAliveStatus();
        printTrunkDiameter();
        printBranchesStatusAndCount();
        printFoliage();
    }

    private void printAge() {
        System.out.printf("Age:\t\t\t%d years%n", age);
    }

    private void printHealthStatus() {
        System.out.printf("Health Status:\t%s%n", healthStatus.getDescription());
    }

    private void printAliveStatus() {
        System.out.printf("Alive:\t\t\t%s%n", (isAlive ? "Yes" : "No"));
    }

    private void printTrunkDiameter() {
        System.out.printf("Trunk:\t\t\t%s (diameter: %.2fm)%n", trunk, trunkDiameter);
    }

    private void printBranchesStatusAndCount() {
        System.out.printf("Branches:\t\t%s (%d branches)%n", branches, branchList.size());
    }

    private void printFoliage() {
        System.out.printf("Foliage:\t\t%s%n%n", foliage);
    }

    @Override
    public void grow() {
        if (isAlive) {
            growthStrategy.grow(this);
            absorbWater();
            absorbNutrients();
            increaseAge();
        } else {
            System.out.printf("%s cannot grow because it is dead.%n", getClass().getSimpleName());
        }
    }

    @Override
    public void increaseAge() {
        if (isAlive) {
            age++;
        }
    }

    @Override
    public void increaseTrunkDiameter(double amount) {
        if (isAlive) {
            trunkDiameter += amount;
        }
    }

    @Override
    public void addBranch() {
        if (isAlive) {
            branchList.add("Branch #" + (branchList.size() + 1));
            branches = "branching with " + branchList.size() + " branches";
            System.out.printf("%s grew a new branch.%n", getClass().getSimpleName());
        }
    }

    @Override
    public void reactToWeather(String weather) {
        if (isAlive) {
            System.out.printf("%s reacts to %s.%n", getClass().getSimpleName(), weather);

            switch (weather.toLowerCase()) {
                case "strong wind" -> reactToStrongWind();
                case "drought" -> reactToDrought();
                case "rain" -> reactToRain();
                default ->
                        System.out.printf("The weather '%s' has no effect on %s.%n", weather, getClass().getSimpleName());
            }
        } else {
            printDeadTreeReaction();
        }
    }

    private void printDeadTreeReaction() {
        System.out.printf("%s does not react to the weather.%n", getClass().getSimpleName());
    }

    private void reactToStrongWind() {
        if (reactionToStrongWindProbability()) {
            if (treeHasBranches()) {
                int branchesToRemove = getRandomPortionOfBranches();
                branchList.remove(branchList.size() - branchesToRemove);
                branches = "branching with " + branchList.size() + " branches";
                System.out.printf("Strong wind broke a branch of %s.%n", getClass().getSimpleName());
            } else {
                System.out.printf("Strong wind sways %s.%n", getClass().getSimpleName());
            }
        }
    }

    private boolean reactionToStrongWindProbability() {
        return new Random().nextDouble() < REACTION_TO_STRONG_WIND_PROBABILITY;
    }

    private boolean treeHasBranches() {
        return !branchList.isEmpty();
    }

    private int getRandomPortionOfBranches() {
        int currentBranchSize = branchList.size();
        if (currentBranchSize == 0) return 0;

        int divisor = getRandomDivisorForBranchRemoval(currentBranchSize);
        int portion = currentBranchSize / divisor;

        return ensureValidPortionIsRemoved(portion, currentBranchSize);
    }

    private int getRandomDivisorForBranchRemoval(int currentBranchSize) {
        return new Random().nextInt(Math.min(4, currentBranchSize)) + 2;
    }

    private int ensureValidPortionIsRemoved(int portion, int currentBranchSize) {
        return Math.max(1, Math.min(portion, currentBranchSize));
    }

    private void reactToDrought() {
        if (healthStatus == HealthStatus.HEALTHY && reactionToDroughtProbability()) {
            healthStatus = HealthStatus.WEAKENED_BY_DROUGHT;
            System.out.printf("%s is suffering from drought.%n", getClass().getSimpleName());
            if (deathByDroughtProbability()) {
                die();
            }
        }
    }

    private boolean reactionToDroughtProbability() {
        return new Random().nextDouble() < REACTION_TO_DROUGHT_PROBABILITY;
    }

    private boolean deathByDroughtProbability() {
        return new Random().nextDouble() < DEATH_BY_DROUGHT_PROBABILITY;
    }

    private void reactToRain() {
        System.out.printf("%s drinks the rain.%n", getClass().getSimpleName());
    }

    @Override
    public void absorbWater() {
        if (isAlive) {
            System.out.printf("%s absorbs water from the soil.%n", getClass().getSimpleName());
            if (healthStatus == HealthStatus.WEAKENED_BY_DROUGHT) {
                healthStatus = HealthStatus.RECOVERING;
            }
        }
    }

    @Override
    public void absorbNutrients() {
        if (isAlive) {
            System.out.printf("%s absorbs nutrients from the soil.%n", getClass().getSimpleName());
            if (healthStatus == HealthStatus.RECOVERING) {
                healthStatus = HealthStatus.HEALTHY;
            }
        }
    }

    @Override
    public void getSick(Disease disease) {
        if (isAlive && healthStatus != HealthStatus.SICK) {
            healthStatus = HealthStatus.SICK;
            System.out.printf("%s got sick with %s.%n", getClass().getSimpleName(), disease.getName());
            if (new Random().nextDouble() < 0.1) {
                die();
            }
        }
    }

    @Override
    public void recover() {
        if (isAlive && healthStatus == HealthStatus.SICK) {
            healthStatus = HealthStatus.HEALTHY;
            System.out.printf("%s recovered.%n", getClass().getSimpleName());
        } else if (isAlive && healthStatus == HealthStatus.WEAKENED_BY_DROUGHT) {
            healthStatus = HealthStatus.HEALTHY;
            System.out.printf("%s recovered from the drought.%n", getClass().getSimpleName());
        } else if (isAlive && healthStatus == HealthStatus.RECOVERING) {
            healthStatus = HealthStatus.HEALTHY;
            System.out.printf("%s fully recovered.%n", getClass().getSimpleName());
        }
    }

    @Override
    public List<Seed> produceSeeds() {
        List<Seed> seeds = new ArrayList<>();
        if (isAlive && isMatureForSeedProduction() && produceSeedsProbability()) {
            int numberOfSeeds = getRandomNumberOfSeedsToProduce();
            produceSeeds(numberOfSeeds, seeds);
            System.out.printf("%s produced %d seeds.%n", getClass().getSimpleName(), numberOfSeeds);
        } else {
            System.out.printf("%s is too young or conditions are not right to produce seeds.%n", getClass().getSimpleName());
        }
        return seeds;
    }

    private boolean isMatureForSeedProduction() {
        return age > TREE_AGE_ELIGIBLE_FOR_SEED_PRODUCTION;
    }

    private boolean produceSeedsProbability() {
        return new Random().nextDouble() < PRODUCTION_OF_SEEDS_PROBABILITY;
    }

    private int getRandomNumberOfSeedsToProduce() {
        return new Random().nextInt(MAX_NEW_SEEDS_COUNT) + MIN_NEW_SEEDS_COUNT;
    }

    private void produceSeeds(int numberOfSeeds, List<Seed> seeds) {
        for (int i = 0; i < numberOfSeeds; i++) {
            seeds.add(new Seed(getTreeType()));
        }
    }

    @Override
    public void die() {
        if (isAlive) {
            isAlive = false;
            healthStatus = HealthStatus.DEAD;
            System.out.printf("%s has died.%n", getClass().getSimpleName());
            foliage = "dead foliage";
            branches = "dead branches";
            trunk = "dead trunk";
        } else {
            System.out.printf("%s is already dead.%n", getClass().getSimpleName());
        }
    }
}
