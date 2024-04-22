package com.hcl.flight.operatorservice.repository;

import com.hcl.flight.operatorservice.model.Flight;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;

public interface CustomFlightRepository {
    Flux<Flight> findAll(Query query);
}
