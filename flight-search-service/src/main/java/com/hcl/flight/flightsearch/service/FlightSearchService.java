package com.hcl.flight.flightsearch.service;


import com.hcl.flight.flightsearch.model.Flight;
import com.hcl.flight.flightsearch.model.SearchCriteria;
import com.hcl.flight.flightsearch.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FlightSearchService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightSearchService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Flux<Flight> searchFlights(SearchCriteria criteria) {
        return flightRepository.findAll()
                .filter(flight ->
                        (criteria.getDepartureAirport() == null || flight.getDepartureAirport().equals(criteria.getDepartureAirport())) &&
                        (criteria.getArrivalAirport() == null || flight.getArrivalAirport().equals(criteria.getArrivalAirport())) &&
                        (criteria.getDepartureFrom() == null || flight.getDepartureTime().isAfter(criteria.getDepartureFrom())) &&
                        (criteria.getDepartureTo() == null || flight.getDepartureTime().isBefore(criteria.getDepartureTo()))
                );
    }

    public Flux<Flight> getAllAvailableFlights() {
        return flightRepository.findAll()
                .filter(flight -> flight.getAvailableSeats() > 0);
    }

    public Mono<Flight> addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flux<Flight> findFlightsByOperator(String operatorName) {
        return flightRepository.findAll()
                .filter(flight -> flight.getOperatorId().equalsIgnoreCase(operatorName));
    }
}
