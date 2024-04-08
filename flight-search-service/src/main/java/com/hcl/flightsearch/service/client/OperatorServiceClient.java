package com.hcl.flightsearch.service.client;

import com.hcl.flightsearch.model.Flight;
import com.hcl.flightsearch.model.SearchCriteria;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class OperatorServiceClient {

    private final WebClient webClient;

    public OperatorServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://operator-service").build();
    }

    public Flux<Flight> fetchFlights(String operatorName, SearchCriteria criteria) {
        return webClient.post()
                .uri("/{operatorName}/search", operatorName)
                .bodyValue(criteria)
                .retrieve()
                .bodyToFlux(Flight.class);
    }
}

