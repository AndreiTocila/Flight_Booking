package com.hcl.flight.operatorservice.repository;


import com.hcl.flight.operatorservice.model.Flight;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FlightRepository extends ReactiveMongoRepository<Flight, String> {

}