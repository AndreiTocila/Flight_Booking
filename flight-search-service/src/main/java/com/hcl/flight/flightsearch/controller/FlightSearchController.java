package com.hcl.flight.flightsearch.controller;

import com.hcl.flight.flightsearch.model.Flight;
import com.hcl.flight.flightsearch.model.SearchCriteria;
import com.hcl.flight.flightsearch.service.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/flights")
public class FlightSearchController {

    private final FlightSearchService flightSearchService;

    @Autowired
    public FlightSearchController(FlightSearchService flightSearchService) {
        this.flightSearchService = flightSearchService;
    }

    @GetMapping("/search")
    public Flux<Flight> searchFlights(@RequestBody SearchCriteria criteria) {
        return flightSearchService.searchFlights(criteria);
    }

    @GetMapping("/available")
    public Flux<Flight> getAvailableFlights() {
        return flightSearchService.getAllAvailableFlights();
    }

    @GetMapping("/operator/{operatorName}")
    public Flux<Flight> getFlightsByOperator(@PathVariable("operatorName") String operatorName) {
        return flightSearchService.findFlightsByOperator(operatorName);
    }

    @PostMapping
    public Mono<Flight> addFlight(@RequestBody Flight flight) {
        return flightSearchService.addFlight(flight);
    }
}
