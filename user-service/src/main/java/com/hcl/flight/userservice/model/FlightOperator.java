package com.hcl.flight.userservice.model;

import jakarta.persistence.*;

@Entity
@Table(schema = "user_service", name = "flights_operator")
public class FlightOperator {
  @Id
  @SequenceGenerator(
      name = "flights_sequence",
      schema = "user_service",
      sequenceName = "flights_sequence",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flights_sequence")
  private Long id;

  @Column(name = "flight_number")
  private Long flightNumber;

  @Column(name = "flight_name")
  private String flightName;

  @Column(name = "number_of_seats")
  private Long numberOfSeats;

  @Column(name = "departure")
  private String departure;

  @Column(name = "arrival")
  private String arrival;

  @Column(name = "price")
  private Double price;

  @ManyToOne
  @JoinColumn(name = "operator_id", nullable = false)
  private Operator operator;

  public FlightOperator() {}

  public Operator getOperator() {
    return operator;
  }

  public void setOperator(Operator operator) {
    this.operator = operator;
  }

  public FlightOperator(
      Long id,
      Long flightNumber,
      String flightName,
      Long numberOfSeats,
      String departure,
      String arrival,
      Double price,
      Operator operator) {
    this.id = id;
    this.flightNumber = flightNumber;
    this.flightName = flightName;
    this.numberOfSeats = numberOfSeats;
    this.departure = departure;
    this.arrival = arrival;
    this.price = price;
    this.operator = operator;
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
