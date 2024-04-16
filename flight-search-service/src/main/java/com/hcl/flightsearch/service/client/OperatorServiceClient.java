package com.hcl.flightsearch.service.client;

import com.hcl.flightsearch.dto.SearchCriteriaDTO;
import com.hcl.flightsearch.model.Flight;
import com.hcl.flightsearch.model.SearchCriteria;
import com.hcl.flightsearch.service.FlightSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class OperatorServiceClient {
    private static final Logger logger = LoggerFactory.getLogger(OperatorServiceClient.class);
    private final Map<String, WebClient> clients;

    public OperatorServiceClient(WebClient.Builder webClientBuilder) {
        clients = new HashMap<>();
        clients.put("Lufthansa", webClientBuilder.baseUrl("http://localhost:8051/operators").build());
        clients.put("WizzAir", webClientBuilder.baseUrl("http://localhost:8052/operators").build());
        clients.put("RyanAir", webClientBuilder.baseUrl("http://localhost:8053/operators").build());
    }

    public Flux<Flight> fetchFlights(SearchCriteriaDTO criteria) {
        return Flux.fromIterable(clients.entrySet())
                .flatMap(entry -> entry.getValue().get()
                        .uri(uriBuilder -> uriBuilder.path("/available")
                                .queryParam("operatorName", criteria.getOperatorName())
                                .queryParam("departureAirport", criteria.getDepartureAirport())
                                .queryParam("arrivalAirport", criteria.getArrivalAirport())
                                .queryParam("departureFrom", criteria.getDepartureFrom())
                                .queryParam("departureTo", criteria.getDepartureTo())
                                .build())
                        .retrieve()
                        .bodyToFlux(Flight.class)
                        .doOnNext(flight -> logger.info("Retrieved flight: {}", flight))
                        .doOnError(error -> logger.error("Error retrieving flights", error)));
    }
}



