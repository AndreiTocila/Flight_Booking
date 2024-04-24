package com.hcl.flight.operatorservice.repository;


import com.hcl.flight.operatorservice.model.Flight;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface FlightRepository extends ReactiveMongoRepository<Flight, String>, CustomFlightRepository {

}