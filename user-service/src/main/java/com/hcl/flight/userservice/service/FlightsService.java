package com.hcl.flight.userservice.service;

import com.hcl.flight.userservice.dto.request.FlightsRequest;
import com.hcl.flight.userservice.dto.response.FlightResponse;
import com.hcl.flight.userservice.dto.response.data.FlightResponseData;
import com.hcl.flight.userservice.model.FlightOperator;
import com.hcl.flight.userservice.model.Operator;
import com.hcl.flight.userservice.repository.FlightRepository;
import com.hcl.flight.userservice.repository.OperatorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FlightsService {
  private final FlightRepository flightRepository;
  private final OperatorRepository operatorRepository;

  public FlightsService(FlightRepository flightRepository, OperatorRepository operatorRepository) {
    this.flightRepository = flightRepository;
    this.operatorRepository = operatorRepository;
  }

  public void save(FlightsRequest flightsRequest) {
    FlightOperator flightOperator = new FlightOperator();
    flightOperator.setFlightName(flightsRequest.getFlightName());
    flightOperator.setFlightNumber(flightsRequest.getFlightNumber());
    flightOperator.setNumberOfSeats(flightsRequest.getNumberOfSeats());

    Optional<Operator> operator = operatorRepository.findById(flightsRequest.getOperatorId());

    flightOperator.setOperator(operator.get());
    flightOperator.setDeparture(flightsRequest.getDeparture());
    flightOperator.setArrival(flightsRequest.getArrival());
    flightOperator.setPrice(flightsRequest.getPrice());

    flightRepository.save(flightOperator);
  }

  public FlightResponse getFlightByNumber(Long flightNumber) {
    FlightOperator flightOperator = flightRepository.findByFlightNumber(flightNumber).get();
    FlightResponseData flightResponseData = new FlightResponseData();
    flightResponseData.setId(flightOperator.getFlightNumber());
    flightResponseData.setDeparture(flightOperator.getDeparture());
    flightResponseData.setArrival(flightOperator.getArrival());
    flightResponseData.setPrice(flightOperator.getPrice());
    flightResponseData.setNumberOfSeats(flightOperator.getNumberOfSeats());
    flightResponseData.setIbanOperator(
        operatorRepository.findById(flightOperator.getId()).get().getIban());
    FlightResponse flightResponse = new FlightResponse();
    flightResponse.setFlightResponseData(flightResponseData);
    return flightResponse;
  }
}
