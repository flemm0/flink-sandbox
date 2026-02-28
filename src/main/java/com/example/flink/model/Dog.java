package com.example.flink.model;

public class Dog {
  public String name;
  public int age;
  public String breed;
  public String sound;
  public String size;

  public Dog() {}

  public Dog(String name,
             int age,
             String breed,
             String sound,
             String size) {
    this.name = name;
    this.age = age;
    this.breed = breed;
    this.sound = sound;
    this.size = size;
  }

  public void bark() {
    System.out.println(name + " says: " + sound);
  }

  @Override
  public String toString() {
    return "Dog{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", breed='" + breed + '\'' +
            ", sound='" + sound + '\'' +
            ", size='" + size + '\'' +
            '}';
  }

}
