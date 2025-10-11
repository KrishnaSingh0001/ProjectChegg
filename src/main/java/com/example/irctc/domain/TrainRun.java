package com.example.irctc.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "train_run", indexes = {
    @Index(columnList = "train_id, run_date", unique = true)
})
public class TrainRun {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false)
  private Train train;

  @Column(nullable = false)
  private LocalDate runDate; // date when the train departs origin

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TrainRunStatus status = TrainRunStatus.SCHEDULED;

  public TrainRun() {}

  public TrainRun(Train train, LocalDate runDate) {
    this.train = train;
    this.runDate = runDate;
  }

  public Long getId() { return id; }
  public Train getTrain() { return train; }
  public void setTrain(Train train) { this.train = train; }
  public LocalDate getRunDate() { return runDate; }
  public void setRunDate(LocalDate runDate) { this.runDate = runDate; }
  public TrainRunStatus getStatus() { return status; }
  public void setStatus(TrainRunStatus status) { this.status = status; }
}
