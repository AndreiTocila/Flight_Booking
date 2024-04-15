package com.hcl.flight.userservice.dto.response.data;

public class FlightResponseData {
  private Long id;
  private String departure;
  private String arrival;
  private Double price;
  private String ibanOperator;
  private Long numberOfSeats;

  public FlightResponseData(
          Long id, String departure, String arrival, Double price, String ibanOperator, Long numberOfSeats) {
    this.id = id;
    this.departure = departure;
    this.arrival = arrival;
    this.price = price;
    this.ibanOperator = ibanOperator;
      this.numberOfSeats = numberOfSeats;
  }

  public FlightResponseData() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getIbanOperator() {
    return ibanOperator;
  }

  public void setIbanOperator(String ibanOperator) {
    this.ibanOperator = ibanOperator;
  }

  public Long getNumberOfSeats() {
    return numberOfSeats;
  }

  public void setNumberOfSeats(Long numberOfSeats) {
    this.numberOfSeats = numberOfSeats;
  }
}
