package com.example.irctc.api.dto;

import com.example.irctc.domain.CoachClass;
import com.example.irctc.domain.Gender;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.*;

public class BookingRequest {
  @NotBlank private String trainNumber;
  @NotNull private LocalDate date;
  @NotBlank private String from;
  @NotBlank private String to;
  @NotNull private CoachClass coachClass;
  @Size(min = 1)
  private List<Pax> passengers;

  public static class Pax {
    @NotBlank public String name;
    @Min(0) public int age;
    @NotNull public Gender gender;
  }

  public String getTrainNumber() { return trainNumber; }
  public void setTrainNumber(String trainNumber) { this.trainNumber = trainNumber; }
  public LocalDate getDate() { return date; }
  public void setDate(LocalDate date) { this.date = date; }
  public String getFrom() { return from; }
  public void setFrom(String from) { this.from = from; }
  public String getTo() { return to; }
  public void setTo(String to) { this.to = to; }
  public CoachClass getCoachClass() { return coachClass; }
  public void setCoachClass(CoachClass coachClass) { this.coachClass = coachClass; }
  public List<Pax> getPassengers() { return passengers; }
  public void setPassengers(List<Pax> passengers) { this.passengers = passengers; }
}
