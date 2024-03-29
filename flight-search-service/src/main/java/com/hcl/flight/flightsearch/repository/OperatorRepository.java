package com.hcl.flight.flightsearch.repository;


import com.hcl.flight.flightsearch.model.Operator;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OperatorRepository extends ReactiveMongoRepository<Operator, String> {
}
