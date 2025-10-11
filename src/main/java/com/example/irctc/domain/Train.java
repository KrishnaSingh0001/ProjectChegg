package com.example.irctc.domain;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "train", indexes = {
    @Index(columnList = "number", unique = true)
})
public class Train {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 10)
  private String number; // e.g., 12951

  @Column(nullable = false)
  private String name;

  @OneToMany(mappedBy = "train", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("sequenceIndex ASC")
  private List<TrainRouteStop> routeStops = new ArrayList<>();

  @OneToMany(mappedBy = "train", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Coach> coaches = new ArrayList<>();

  public Train() {}

  public Train(String number, String name) {
    this.number = number;
    this.name = name;
  }

  public Long getId() { return id; }
  public String getNumber() { return number; }
  public void setNumber(String number) { this.number = number; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public List<TrainRouteStop> getRouteStops() { return routeStops; }
  public List<Coach> getCoaches() { return coaches; }
}
