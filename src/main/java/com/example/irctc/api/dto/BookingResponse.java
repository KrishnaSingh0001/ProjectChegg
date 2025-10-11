package com.example.irctc.api.dto;

public class BookingResponse {
  private String pnr;
  private String status;

  public BookingResponse() {}
  public BookingResponse(String pnr, String status) {
    this.pnr = pnr;
    this.status = status;
  }

  public String getPnr() { return pnr; }
  public void setPnr(String pnr) { this.pnr = pnr; }
  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }
}
