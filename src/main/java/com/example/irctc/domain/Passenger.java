package com.example.irctc.domain;

import jakarta.persistence.*;

@Embeddable
public class Passenger {
  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private int age;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Gender gender;

  public Passenger() {}

  public Passenger(String name, int age, Gender gender) {
    this.name = name;
    this.age = age;
    this.gender = gender;
  }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public int getAge() { return age; }
  public void setAge(int age) { this.age = age; }
  public Gender getGender() { return gender; }
  public void setGender(Gender gender) { this.gender = gender; }
}
