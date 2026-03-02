package com.example.flink.util.simulator;

import com.example.flink.model.DogEvent;
import java.util.Random;

public class DogSimulatorEngine {
  
  public final DogPool dogPool = new DogPool();
  private Random random = new Random();

  public void initialize(int count) {
    dogPool.initializePool(count);
  }

  public DogEvent simulate() {
    Dog randomDog = dogPool.getRandomDog();
    DogEvent dogEvent = new DogEvent(
      randomDog.getId(),
      randomDog.getName(),
      randomDog.getBreed(),
      randomDog.getAge(),
      randomDog.getGender(),
      randomDog.makeSound()
    );
    try {
      Thread.sleep(2000); // Simulate some processing time
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return dogEvent;
  }

  public static void main(String[] args) {
    DogSimulatorEngine engine = new DogSimulatorEngine();
    engine.initialize(100);
    engine.simulate();
  }

}