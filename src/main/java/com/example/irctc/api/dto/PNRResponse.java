package com.example.irctc.api.dto;

import java.util.*;

public class PNRResponse {
  private String pnr;
  private String trainNumber;
  private String from;
  private String to;
  private String date;
  private String coachClass;
  private String status;
  private List<String> passengerNames;

  public String getPnr() { return pnr; }
  public void setPnr(String pnr) { this.pnr = pnr; }
  public String getTrainNumber() { return trainNumber; }
  public void setTrainNumber(String trainNumber) { this.trainNumber = trainNumber; }
  public String getFrom() { return from; }
  public void setFrom(String from) { this.from = from; }
  public String getTo() { return to; }
  public void setTo(String to) { this.to = to; }
  public String getDate() { return date; }
  public void setDate(String date) { this.date = date; }
  public String getCoachClass() { return coachClass; }
  public void setCoachClass(String coachClass) { this.coachClass = coachClass; }
  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }
  public List<String> getPassengerNames() { return passengerNames; }
  public void setPassengerNames(List<String> passengerNames) { this.passengerNames = passengerNames; }
}
