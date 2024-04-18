package com.hcl.bookingservice.service;

import com.hcl.bookingservice.dto.CardDetailsDTO;
import com.hcl.bookingservice.dto.FlightDetailsDTO;
import org.springframework.security.oauth2.jwt.Jwt;
import reactor.core.publisher.Mono;

public interface UserServiceRestClient
{
    Mono<FlightDetailsDTO> getFlightDetails(Long flightId, String token);

    Mono<String> getCardDetails(Long cardId, Jwt jwt);
}
