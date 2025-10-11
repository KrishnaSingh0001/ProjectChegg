package com.example.irctc.api.dto;

import com.example.irctc.domain.CoachClass;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class SearchRequest {
  @NotBlank
  private String from;

  @NotBlank
  private String to;

  @NotNull
  private LocalDate date;

  @NotNull
  private CoachClass coachClass;

  public String getFrom() { return from; }
  public void setFrom(String from) { this.from = from; }
  public String getTo() { return to; }
  public void setTo(String to) { this.to = to; }
  public LocalDate getDate() { return date; }
  public void setDate(LocalDate date) { this.date = date; }
  public CoachClass getCoachClass() { return coachClass; }
  public void setCoachClass(CoachClass coachClass) { this.coachClass = coachClass; }
}
