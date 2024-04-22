package com.hcl.flight.userservice.repository;

import com.hcl.flight.userservice.model.FlightOperator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<FlightOperator, Long> {
  Optional<FlightOperator> findByFlightNumber(Long flightNumber);
}
