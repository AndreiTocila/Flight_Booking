package com.hcl.flight.userservice.dto.request;

public class FlightsRequest {
  private Long flightNumber;
  private String flightName;
  private Long numberOfSeats;
  private Long operatorId;
  private String departure;
  private String arrival;
  private Double price;

  public FlightsRequest(
      Long flightNumber,
      Long numberOfSeats,
      String flightName,
      Long operatorId,
      String departure,
      String arrival,
      Double price) {
    this.flightNumber = flightNumber;
    this.numberOfSeats = numberOfSeats;
    this.flightName = flightName;
    this.operatorId = operatorId;
    this.departure = departure;
    this.arrival = arrival;
    this.price = price;
  }

  public FlightsRequest() {}

  public Long getFlightNumber() {
    return flightNumber;
  }

  public void setFlightNumber(Long flightNumber) {
    this.flightNumber = flightNumber;
  }

  public String getFlightName() {
    return flightName;
  }

  public void setFlightName(String flightName) {
    this.flightName = flightName;
  }

  public Long getNumberOfSeats() {
    return numberOfSeats;
  }

  public void setNumberOfSeats(Long numberOfSeats) {
    this.numberOfSeats = numberOfSeats;
  }

  public Long getOperatorId() {
    return operatorId;
  }

  public void setOperatorId(Long operatorId) {
    this.operatorId = operatorId;
  }

  public String getDeparture() {
    return departure;
  }

  public void setDeparture(String departure) {
    this.departure = departure;
  }

  public String getArrival() {
    return arrival;
  }

  public void setArrival(String arrival) {
    this.arrival = arrival;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }
}
