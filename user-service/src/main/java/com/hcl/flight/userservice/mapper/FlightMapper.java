package com.hcl.flight.userservice.mapper;

import com.hcl.flight.userservice.dto.FlightOperatorDTO;
import com.hcl.flight.userservice.model.FlightOperator;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {
  public FlightOperator dtoToEntity(FlightOperatorDTO flightOperatorDTO) {
    FlightOperator flightOperator = new FlightOperator();
    if (flightOperatorDTO == null) {
      return null;
    }
    flightOperator.setId(flightOperatorDTO.getId());
    flightOperator.setFlightName(flightOperatorDTO.getFlightName());
    flightOperator.setFlightNumber(flightOperatorDTO.getFlightNumber());
    flightOperator.setNumberOfSeats(flightOperatorDTO.getNumberOfSeats());
    flightOperator.setDeparture(flightOperatorDTO.getDeparture());
    flightOperator.setArrival(flightOperatorDTO.getArrival());
    flightOperator.setPrice(flightOperatorDTO.getPrice());
    return flightOperator;
  }
}
