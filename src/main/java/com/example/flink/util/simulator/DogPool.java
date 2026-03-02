package com.example.flink.util.simulator;

import java.util.*;
import java.util.Random;
import com.github.javafaker.Faker;
import com.example.flink.util.simulator.Dog;

public class DogPool {
  
  public List<Dog> dogs = new ArrayList<>();

  public void initializePool(int count) {
    for (int i = 0; i < count; i++) {
      com.github.javafaker.Dog dogGenerator = new Faker().dog();
      dogs.add(new Dog(
          dogGenerator.name(),
          dogGenerator.breed(),
          dogGenerator.age(),
          dogGenerator.gender()
      ));
    }
  }

  public List<Dog> getDogs() {
    return dogs;
  }

  public Dog getRandomDog() {
    Random random = new Random();
    return dogs.get(random.nextInt(dogs.size()));
  }

}
