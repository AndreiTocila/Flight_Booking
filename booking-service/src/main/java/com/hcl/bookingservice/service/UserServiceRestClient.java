package com.hcl.bookingservice.service;

import com.hcl.bookingservice.dto.CardDetailsDTO;
import com.hcl.bookingservice.dto.FlightDetailsDTO;
import reactor.core.publisher.Mono;

public interface UserServiceRestClient
{
    Mono<FlightDetailsDTO> getFlightDetails(Long flightId);

    Mono<CardDetailsDTO> getCardDetails(Long cardId);
}
