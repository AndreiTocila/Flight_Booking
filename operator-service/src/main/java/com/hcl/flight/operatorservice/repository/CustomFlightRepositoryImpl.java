package com.hcl.flight.operatorservice.repository;

import com.hcl.flight.operatorservice.model.Flight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;

public class CustomFlightRepositoryImpl implements CustomFlightRepository {

    private static final Logger logger = LoggerFactory.getLogger(CustomFlightRepositoryImpl.class);

    private final ReactiveMongoTemplate mongoTemplate;

    @Autowired
    public CustomFlightRepositoryImpl(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Flux<Flight> findAll(Query query) {
        logger.info("Executing MongoDB query: {}", query.toString());
        return mongoTemplate.find(query, Flight.class)
                .doOnNext(flight -> logger.info("Retrieved flight: {}", flight))
                .doOnError(error -> logger.error("Error executing MongoDB query", error));
    }
}

