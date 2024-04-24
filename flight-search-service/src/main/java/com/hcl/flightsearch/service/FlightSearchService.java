package com.hcl.flightsearch.service;

import com.hcl.flightsearch.dto.SearchCriteriaDTO;
import com.hcl.flightsearch.model.Flight;
import com.hcl.flightsearch.model.SearchCriteria;
import com.hcl.flightsearch.service.client.OperatorServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class FlightSearchService {

    private static final Logger logger = LoggerFactory.getLogger(FlightSearchService.class);
    private final OperatorServiceClient operatorServiceClient;

    @Autowired
    public FlightSearchService(OperatorServiceClient operatorServiceClient) {
        this.operatorServiceClient = operatorServiceClient;
    }

    public Flux<Flight> searchFlights(SearchCriteriaDTO criteria) {
        return operatorServiceClient.fetchFlights(criteria)
                .distinct()
                .doOnNext(flight -> logger.info("Retrieved flight: {}", flight))
                .doOnError(error -> logger.error("Error retrieving flights", error))
                .doOnComplete(() -> logger.info("Completed fetching flights"))
                .doOnSubscribe(subscription -> logger.info("Subscription started: {}", subscription));
    }
}

