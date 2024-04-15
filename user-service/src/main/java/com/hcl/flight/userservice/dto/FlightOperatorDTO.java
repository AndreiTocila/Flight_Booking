package com.hcl.flight.userservice.dto;

import com.hcl.flight.userservice.model.Operator;

public class FlightOperatorDTO {

  private Long id;

  private Long flightNumber;

  private String flightName;

  private Long numberOfSeats;

  private Operator operator;

  private String departure;
  private String arrival;
  private Double price;

  public FlightOperatorDTO() {}

  public FlightOperatorDTO(
      Long id,
      Long flightNumber,
      String flightName,
      Long numberOfSeats,
      Operator operator,
      String departure,
      String arrival,
      Double price) {
    this.id = id;
    this.flightNumber = flightNumber;
    this.flightName = flightName;
    this.numberOfSeats = numberOfSeats;
    this.operator = operator;
    this.departure = departure;
    this.arrival = arrival;
    this.price = price;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public Operator getOperator() {
    return operator;
  }

  public void setOperator(Operator operator) {
    this.operator = operator;
  }

  public String getArrival() {
    return arrival;
  }

  public void setArrival(String arrival) {
    this.arrival = arrival;
  }

  public String getDeparture() {
    return departure;
  }

  public void setDeparture(String departure) {
    this.departure = departure;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }
}
