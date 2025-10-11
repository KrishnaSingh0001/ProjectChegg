package com.example.irctc.domain;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "train_route_stop", indexes = {
    @Index(columnList = "train_id, sequence_index", unique = true)
})
public class TrainRouteStop {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false)
  private Train train;

  @ManyToOne(optional = false)
  private Station station;

  @Column(nullable = false)
  private int sequenceIndex; // 0..N along the route

  @Column(nullable = false)
  private LocalTime arrivalTime; // time-of-day, relative schedule

  @Column(nullable = false)
  private LocalTime departureTime;

  public TrainRouteStop() {}

  public TrainRouteStop(Train train, Station station, int sequenceIndex, LocalTime arrivalTime, LocalTime departureTime) {
    this.train = train;
    this.station = station;
    this.sequenceIndex = sequenceIndex;
    this.arrivalTime = arrivalTime;
    this.departureTime = departureTime;
  }

  public Long getId() { return id; }
  public Train getTrain() { return train; }
  public void setTrain(Train train) { this.train = train; }
  public Station getStation() { return station; }
  public void setStation(Station station) { this.station = station; }
  public int getSequenceIndex() { return sequenceIndex; }
  public void setSequenceIndex(int sequenceIndex) { this.sequenceIndex = sequenceIndex; }
  public LocalTime getArrivalTime() { return arrivalTime; }
  public void setArrivalTime(LocalTime arrivalTime) { this.arrivalTime = arrivalTime; }
  public LocalTime getDepartureTime() { return departureTime; }
  public void setDepartureTime(LocalTime departureTime) { this.departureTime = departureTime; }
}
