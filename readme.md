# Tree Simulation

A simple application simulating the lifecycle of various tree species.

## Task Description

Implement in Java an abstraction for deciduous and coniferous trees (representing a typical object-oriented model of natural trees found in the environment).
Each tree should have a trunk, branches, and foliage (leaves or needles), as well as a method like grow() and other related behavior.

## Application Description

This Java application models basic tree behaviors such as growth, reaction to weather conditions, getting sick, recovering, and seed production. Currently, four tree species are simulated: Oak, Maple, Spruce, and Pine. Each tree type has a basic growth strategy assigned to it.

## Technologies

* Java
* Lombok (for automatic code generation)
* Spring Boot (for running the application)

## Running the Application

1.  **Requirements:**
    * Installed JDK (Java Development Kit) version 17 or higher.
    * Installed Maven or Gradle (the project is configured for Spring Boot, so Maven is used by default).

2.  **Navigate to the project directory in your terminal.**

3.  **Run the application:**

    **Using Maven:**
    ```bash
    mvn spring-boot:run
    ```

    **Using Gradle (if you are using Gradle):**
    ```bash
    ./gradlew bootRun
    ```

    The application will start and output information about the lifecycle of the simulated trees in the console.

## Features

* **Different Tree Types:** Simulation of Oak, Maple, Spruce, and Pine trees.
* **Growth Strategies:** Each tree type has a defined basic growth strategy.
* **Tree Age:** Trees age in each simulation cycle.
* **Trunk Diameter:** The trunk diameter of trees increases with age.
* **Branches:** Trees can grow new branches.
* **Weather Reaction:** Trees react to "strong wind" and "drought" (with a certain probability).
* **Diseases:** Trees can get sick.
* **Recovery:** Sick or weakened trees can recover.
* **Seed Production:** Mature trees produce seeds with a certain probability.
* **Seed Germination:** Seeds have a chance to sprout and create new trees.
* **Species-Specific Features:**
    * Deciduous trees (Oak, Maple) shed and grow leaves and change their color.
    * Coniferous trees (Spruce, Pine) shed and grow needles and change their color.
    * Pine trees produce pine cones.
    * Spruce trees produce berries.
    * Oak trees produce acorns.
    * Maple trees produce seeds.
* **Health Status:** Trees have a health status (healthy, weakened by drought, sick, recovering, dead).
* **Tree Information:** Basic information about each tree is displayed (age, health status, whether it is alive, trunk details, branches, and foliage).

## Future Development

This application is in the early stages of development. Planned future enhancements include:

* **More realistic growth models:** Incorporating seasons, access to light, water, and nutrients.
* **Seasons cycle:** Introducing changing seasons affecting tree behavior.
* **Seed dispersal:** Modeling how seeds are spread and germinate in different locations.
* **Inter-tree interactions:** Competition for resources, shading.
* **More complex diseases and pests:** Spreading, different effects.
* **Tree longevity:** Trees dying of old age.
* **Different soil types and their impact.**
* **Integration with a simple environment simulation.**
* **Visualization (optional).**