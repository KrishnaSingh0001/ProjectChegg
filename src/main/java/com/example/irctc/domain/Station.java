package com.example.irctc.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "station", indexes = {
    @Index(columnList = "code", unique = true)
})
public class Station {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 5)
  private String code; // e.g., NDLS, BCT

  @Column(nullable = false)
  private String name;

  public Station() {}

  public Station(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public Long getId() { return id; }
  public String getCode() { return code; }
  public void setCode(String code) { this.code = code; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
}
