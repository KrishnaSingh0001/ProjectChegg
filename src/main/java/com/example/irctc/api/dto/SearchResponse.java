package com.example.irctc.api.dto;

public class SearchResponse {
  private String trainNumber;
  private String trainName;
  private String from;
  private String to;
  private String coachClass;
  private String date;
  private int available;

  public String getTrainNumber() { return trainNumber; }
  public void setTrainNumber(String trainNumber) { this.trainNumber = trainNumber; }
  public String getTrainName() { return trainName; }
  public void setTrainName(String trainName) { this.trainName = trainName; }
  public String getFrom() { return from; }
  public void setFrom(String from) { this.from = from; }
  public String getTo() { return to; }
  public void setTo(String to) { this.to = to; }
  public String getCoachClass() { return coachClass; }
  public void setCoachClass(String coachClass) { this.coachClass = coachClass; }
  public String getDate() { return date; }
  public void setDate(String date) { this.date = date; }
  public int getAvailable() { return available; }
  public void setAvailable(int available) { this.available = available; }
}
