package com.hcl.flight.operatorservice.controller;

import com.hcl.flight.operatorservice.model.Flight;
import com.hcl.flight.operatorservice.model.SearchCriteria;
import com.hcl.flight.operatorservice.service.OperatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/operators")
public class OperatorController {

    @Value("${OPERATOR_NAME}")
    private String operatorName;

    private static final Logger logger = LoggerFactory.getLogger(OperatorController.class);

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
    public Flux<Flight> getAvailableFlights(@RequestParam(required = false) String departureAirport,
                                            @RequestParam(required = false) String arrivalAirport,
                                            @RequestParam(required = false) LocalDateTime departureFrom,
                                            @RequestParam(required = false) LocalDateTime departureTo) {
        if ((departureAirport != null && departureAirport.trim().isEmpty()) ||
                (arrivalAirport != null && arrivalAirport.trim().isEmpty())) {
            logger.error("Invalid request: Departure or arrival airport cannot be empty strings.");
            return Flux.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Departure or arrival airport cannot be empty strings."));
        }
        logger.info("Querying for operatorName: {}, departureAirport: {}, arrivalAirport: {}", operatorName, departureAirport, arrivalAirport);
        SearchCriteria criteria = new SearchCriteria(operatorName, departureAirport, arrivalAirport, departureFrom, departureTo);
        return operatorService.searchFlights(criteria);
    }


    @PostMapping
    public Mono<Flight> addFlight(@RequestBody Flight flight) {
        flight.setOperatorName(operatorName);
        return operatorService.addFlight(flight);
    }

}
