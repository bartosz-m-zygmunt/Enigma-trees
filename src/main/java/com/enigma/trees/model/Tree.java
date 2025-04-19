package com.enigma.trees.model;

import com.enigma.trees.factory.TreeType;

import java.util.List;

public interface Tree {
    String getTrunk();

    String getBranches();

    String getFoliage();

    int getAge();

    boolean isAlive();

    HealthStatus getHealthStatus();

    void displayInfo();

    void grow();

    void increaseAge();

    void increaseTrunkDiameter(double amount);

    void addBranch();

    void reactToWeather(String weather);

    void absorbWater();

    void absorbNutrients();

    void getSick(Disease disease);

    void recover();

    List<Seed> produceSeeds();

    TreeType getTreeType();

    void die();
}
