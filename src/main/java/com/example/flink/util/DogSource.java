package com.example.flink.util;

import com.example.flink.util.simulator.DogSimulatorEngine;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import com.example.flink.util.simulator.Dog;

public class DogSource implements SourceFunction<Dog>{

  private DogSimulatorEngine simulatorEngine = new DogSimulatorEngine();
  private boolean running = true;

  @Override
  public void run(SourceContext<Dog> ctx) throws Exception {
    simulatorEngine.initialize(100); // Initialize with 100 dogs
    while (running) {
      Dog dog = simulatorEngine.dogPool.getRandomDog();
      ctx.collect(dog);
      Thread.sleep(2000); // Emit a dog every 2 seconds
    }
  }

  @Override
  public void cancel() {
    this.running = false;
  }
}
