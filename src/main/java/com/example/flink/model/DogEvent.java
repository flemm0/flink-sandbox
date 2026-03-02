package com.example.flink.model;

public class DogEvent {

  public String id;
  public String name;
  public String breed;
  public String age;
  public String gender;
  public String sound;

  public DogEvent() {}

  public DogEvent(String id, String name,
                  String breed, String age,
                  String gender, String sound) {
    this.id = id;
    this.name = name;
    this.breed = breed;
    this.age = age;
    this.gender = gender;
    this.sound = sound;
  }

}