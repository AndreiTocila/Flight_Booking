package com.hcl.flightsearch.service;

import com.hcl.flightsearch.model.Flight;
import com.hcl.flightsearch.model.SearchCriteria;
import com.hcl.flightsearch.service.client.OperatorServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class FlightSearchService {

    private final OperatorServiceClient operatorServiceClient;

    @Autowired
    public FlightSearchService(OperatorServiceClient operatorServiceClient) {
        this.operatorServiceClient = operatorServiceClient;
    }

    public Flux<Flight> searchFlights(SearchCriteria criteria) {
        return Flux.merge(
                operatorServiceClient.fetchFlights("Lufthansa", criteria),
                operatorServiceClient.fetchFlights("WizzAir", criteria),
                operatorServiceClient.fetchFlights("RyanAir", criteria)
        );
    }
}

