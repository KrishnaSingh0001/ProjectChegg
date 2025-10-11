package com.example.irctc.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "coach", indexes = {
    @Index(columnList = "train_id, code", unique = true)
})
public class Coach {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false)
  private Train train;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 10)
  private CoachClass coachClass;

  @Column(nullable = false, length = 5)
  private String code; // e.g., A1, B1, S1

  @Column(nullable = false)
  private int totalSeats;

  public Coach() {}

  public Coach(Train train, CoachClass coachClass, String code, int totalSeats) {
    this.train = train;
    this.coachClass = coachClass;
    this.code = code;
    this.totalSeats = totalSeats;
  }

  public Long getId() { return id; }
  public Train getTrain() { return train; }
  public void setTrain(Train train) { this.train = train; }
  public CoachClass getCoachClass() { return coachClass; }
  public void setCoachClass(CoachClass coachClass) { this.coachClass = coachClass; }
  public String getCode() { return code; }
  public void setCode(String code) { this.code = code; }
  public int getTotalSeats() { return totalSeats; }
  public void setTotalSeats(int totalSeats) { this.totalSeats = totalSeats; }
}
