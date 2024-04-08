package com.hcl.flight.operatorservice.service;


import com.hcl.flight.operatorservice.model.Flight;
import com.hcl.flight.operatorservice.model.SearchCriteria;
import com.hcl.flight.operatorservice.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return flightRepository.findAll()
                .filter(flight ->
                        (criteria.getOperatorName().equals(flight.getOperatorName())) &&
                        (criteria.getDepartureAirport() == null || flight.getDepartureAirport().equals(criteria.getDepartureAirport())) &&
                        (criteria.getArrivalAirport() == null || flight.getArrivalAirport().equals(criteria.getArrivalAirport())) &&
                        (criteria.getDepartureFrom() == null || flight.getDepartureTime().isAfter(criteria.getDepartureFrom())) &&
                        (criteria.getDepartureTo() == null || flight.getDepartureTime().isBefore(criteria.getDepartureTo()))
                )
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "No flights match the search criteria")));
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
