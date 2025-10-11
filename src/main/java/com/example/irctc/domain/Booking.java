package com.example.irctc.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "booking", indexes = {
    @Index(columnList = "pnr", unique = true),
    @Index(columnList = "train_run_id"),
    @Index(columnList = "from_stop_id, to_stop_id")
})
public class Booking {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 10)
  private String pnr;

  @ManyToOne(optional = false)
  private TrainRun trainRun;

  @ManyToOne(optional = false)
  private TrainRouteStop fromStop;

  @ManyToOne(optional = false)
  private TrainRouteStop toStop;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private CoachClass coachClass;

  @Column(nullable = false)
  private int seatCount;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "booking_passenger", joinColumns = @JoinColumn(name = "booking_id"))
  private List<Passenger> passengers = new ArrayList<>();

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private BookingStatus status = BookingStatus.CONFIRMED;

  @Column(nullable = false)
  private LocalDateTime bookedAt = LocalDateTime.now();

  public Booking() {}

  public Booking(String pnr, TrainRun trainRun, TrainRouteStop fromStop, TrainRouteStop toStop,
                 CoachClass coachClass, int seatCount, List<Passenger> passengers) {
    this.pnr = pnr;
    this.trainRun = trainRun;
    this.fromStop = fromStop;
    this.toStop = toStop;
    this.coachClass = coachClass;
    this.seatCount = seatCount;
    this.passengers = passengers;
  }

  public Long getId() { return id; }
  public String getPnr() { return pnr; }
  public void setPnr(String pnr) { this.pnr = pnr; }
  public TrainRun getTrainRun() { return trainRun; }
  public void setTrainRun(TrainRun trainRun) { this.trainRun = trainRun; }
  public TrainRouteStop getFromStop() { return fromStop; }
  public void setFromStop(TrainRouteStop fromStop) { this.fromStop = fromStop; }
  public TrainRouteStop getToStop() { return toStop; }
  public void setToStop(TrainRouteStop toStop) { this.toStop = toStop; }
  public CoachClass getCoachClass() { return coachClass; }
  public void setCoachClass(CoachClass coachClass) { this.coachClass = coachClass; }
  public int getSeatCount() { return seatCount; }
  public void setSeatCount(int seatCount) { this.seatCount = seatCount; }
  public List<Passenger> getPassengers() { return passengers; }
  public void setPassengers(List<Passenger> passengers) { this.passengers = passengers; }
  public BookingStatus getStatus() { return status; }
  public void setStatus(BookingStatus status) { this.status = status; }
  public LocalDateTime getBookedAt() { return bookedAt; }
  public void setBookedAt(LocalDateTime bookedAt) { this.bookedAt = bookedAt; }
}
