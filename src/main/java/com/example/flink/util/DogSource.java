package com.example.flink.util;

import com.example.flink.util.simulator.DogSimulatorEngine;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import com.example.flink.model.DogEvent;

public class DogSource implements SourceFunction<DogEvent>{

  private DogSimulatorEngine simulatorEngine = new DogSimulatorEngine();
  private boolean running = true;

  @Override
  public void run(SourceContext<DogEvent> ctx) throws Exception {
    simulatorEngine.initialize(100); // Initialize with 100 dogs
    while (running) {
      DogEvent dogEvent = simulatorEngine.simulate();
      ctx.collect(dogEvent);
    }
  }

  @Override
  public void cancel() {
    this.running = false;
  }
}
