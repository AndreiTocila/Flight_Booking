package com.hcl.flightsearch.controller;

import com.hcl.flightsearch.dto.SearchCriteriaDTO;
import com.hcl.flightsearch.model.Flight;
import com.hcl.flightsearch.model.SearchCriteria;
import com.hcl.flightsearch.service.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/flight-search")
public class FlightSearchController {

    private final FlightSearchService flightSearchService;

    @Autowired
    public FlightSearchController(FlightSearchService flightSearchService) {
        this.flightSearchService = flightSearchService;
    }

    // FlightSearchController.java
    @GetMapping("/search")
    public Mono<ResponseEntity<List<Flight>>> searchFlights(@RequestParam(required = false) String operatorName,
                                                            @RequestParam(required = false) String departureAirport,
                                                            @RequestParam(required = false) String arrivalAirport,
                                                            @RequestParam(required = false) LocalDateTime departureFrom,
                                                            @RequestParam(required = false) LocalDateTime departureTo) {
        SearchCriteriaDTO criteria = new SearchCriteriaDTO(operatorName, departureAirport, arrivalAirport, departureFrom, departureTo);
        return flightSearchService.searchFlights(criteria)
                .collectList()
                .map(flights -> ResponseEntity.ok().body(flights))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}