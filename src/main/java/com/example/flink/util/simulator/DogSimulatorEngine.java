package com.example.flink.util.simulator;

public class DogSimulatorEngine {
  
  public final DogPool dogPool = new DogPool();

  public void initialize(int count) {
    dogPool.initializePool(count);
  }

  public void simulate() {
    for (Dog dog : dogPool.getDogs()) {
      System.out.println(dog);
      System.out.println("Sound: " + dog.makeSound());
    }
  }

  public static void main(String[] args) {
    DogSimulatorEngine engine = new DogSimulatorEngine();
    engine.initialize(100);
    engine.simulate();
  }

}