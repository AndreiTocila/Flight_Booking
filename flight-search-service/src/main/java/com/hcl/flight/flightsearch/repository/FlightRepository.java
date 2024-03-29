package com.hcl.flight.flightsearch.repository;


import com.hcl.flight.flightsearch.model.Flight;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FlightRepository extends ReactiveMongoRepository<Flight, String> {

}