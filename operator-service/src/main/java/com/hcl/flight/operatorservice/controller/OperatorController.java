package com.hcl.flight.operatorservice.controller;

import com.hcl.flight.operatorservice.model.Flight;
import com.hcl.flight.operatorservice.model.SearchCriteria;
import com.hcl.flight.operatorservice.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/operators")
public class OperatorController {

    @Value("${operator.name}")
    private String operatorName;

    private final OperatorService operatorService;

    @Autowired
    public OperatorController(OperatorService operatorService) {
        this.operatorService = operatorService;
    }


    @PostMapping("/search")
    public Flux<Flight> searchFlights(@RequestBody SearchCriteria criteria) {
        criteria.setOperatorName(operatorName);
        return operatorService.searchFlights(criteria);
    }


    @GetMapping("/available")
    public Flux<Flight> getAvailableFlights() {
        return operatorService.getAllAvailableFlightsForOperator(operatorName);
    }


    @PostMapping
    public Mono<Flight> addFlight(@RequestBody Flight flight) {
        flight.setOperatorName(operatorName);
        return operatorService.addFlight(flight);
    }

}
