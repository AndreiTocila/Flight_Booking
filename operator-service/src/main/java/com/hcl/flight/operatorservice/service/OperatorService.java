package com.hcl.flight.operatorservice.service;

import com.hcl.flight.operatorservice.model.Flight;
import com.hcl.flight.operatorservice.model.SearchCriteria;
import com.hcl.flight.operatorservice.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OperatorService {

    private final FlightRepository flightRepository;

    @Autowired
    public OperatorService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Flux<Flight> searchFlights(SearchCriteria criteria) {
        Criteria dbCriteria = new Criteria();

        if (criteria.getOperatorName() != null && !criteria.getOperatorName().isEmpty()) {
            dbCriteria.and("operatorName").is(criteria.getOperatorName());
        }
        if (criteria.getDepartureAirport() != null && !criteria.getDepartureAirport().isEmpty()) {
            dbCriteria.and("departureAirport").is(criteria.getDepartureAirport());
        }
        if (criteria.getArrivalAirport() != null && !criteria.getArrivalAirport().isEmpty()) {
            dbCriteria.and("arrivalAirport").is(criteria.getArrivalAirport());
        }
        if (criteria.getDepartureFrom() != null && criteria.getDepartureTo() != null) {
            dbCriteria.and("departureTime").gte(criteria.getDepartureFrom()).lte(criteria.getDepartureTo());
        } else if (criteria.getDepartureFrom() != null) {
            dbCriteria.and("departureTime").gte(criteria.getDepartureFrom());
        } else if (criteria.getDepartureTo() != null) {
            dbCriteria.and("departureTime").lte(criteria.getDepartureTo());
        }

        Query query = new Query(dbCriteria);
        System.out.println("Executing query: " + query.toString());
        return flightRepository.findAll(query);
    }

    public Flux<Flight> getAllAvailableFlightsForOperator(String operatorName) {
        return flightRepository.findAll()
                .filter(flight -> flight.getAvailableSeats() > 0 && flight.getOperatorName().equals(operatorName))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "No available flights found for operator: " + operatorName)));
    }

    public Mono<Flight> addFlight(Flight flight) {
        return flightRepository.save(flight)
                .onErrorMap(ex -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to add flight", ex));
    }
}