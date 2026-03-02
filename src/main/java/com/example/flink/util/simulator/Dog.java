package com.example.flink.util.simulator;

import com.github.javafaker.Faker;
import java.util.UUID;

public class Dog {
  
  private String id;
  private String name;
  private String breed;
  private String age;
  private String gender;

  private static final Faker faker = new Faker();

  public Dog() {}

  public Dog(String name, String breed, String age, String gender) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
    this.breed = breed;
    this.age = age;
    this.gender = gender;
  }

  public String makeSound() {
    return faker.dog().sound();
  }

  public String bark() {
    return "Bark!";
  }

  public String getName() {
    return name;
  }

  public String getId() {
    return id;
  }

  public String getBreed() {
    return breed;
  }

  public String getAge() {
    return age;
  }

  public String getGender() {
    return gender;
  }

  @Override
  public String toString() {
    return "Dog{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", breed='" + breed + '\'' +
            ", age='" + age + '\'' +
            ", gender='" + gender + '\'' +
            '}';
  }

}
