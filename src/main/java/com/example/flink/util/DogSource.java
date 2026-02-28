package com.example.flink.util;

import org.apache.flink.streaming.api.functions.source.SourceFunction;
import com.example.flink.model.Dog;
import com.github.javafaker.Faker;

public class DogSource implements SourceFunction<Dog>{

  private static final Faker faker = new Faker();
  private boolean running = true;

  @Override
  public void run(SourceContext<Dog> ctx) throws Exception {
    while (running) {
      Dog dog = new Dog();
      dog.name = faker.dog().name();
      dog.age = faker.number().numberBetween(1, 15);
      dog.breed = faker.dog().breed();
      dog.sound = faker.dog().sound();
      dog.size = faker.dog().size();
      ctx.collect(dog);
      Thread.sleep(2000); // Emit a dog every 2 seconds
    }
  }

  @Override
  public void cancel() {
    this.running = false;
  }
}
