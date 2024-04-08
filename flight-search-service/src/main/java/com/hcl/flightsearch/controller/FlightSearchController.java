package com.hcl.flightsearch.controller;

import com.hcl.flightsearch.model.Flight;
import com.hcl.flightsearch.model.SearchCriteria;
import com.hcl.flightsearch.service.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/flight-search")
public class FlightSearchController {

    private final FlightSearchService flightSearchService;

    @Autowired
    public FlightSearchController(FlightSearchService flightSearchService) {
        this.flightSearchService = flightSearchService;
    }

    @PostMapping("/search")
    public Mono<ResponseEntity<List<Flight>>> searchFlights(@RequestBody SearchCriteria criteria) {
        return flightSearchService.searchFlights(criteria)
                .collectList()
                .map(flights -> ResponseEntity.ok().body(flights))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}