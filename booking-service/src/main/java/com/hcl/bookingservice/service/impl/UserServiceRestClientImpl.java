package com.hcl.bookingservice.service.impl;

import com.hcl.bookingservice.dto.CardDetailsDTO;
import com.hcl.bookingservice.dto.FlightDetailsDTO;
import com.hcl.bookingservice.dto.FlightResponse;
import com.hcl.bookingservice.service.UserServiceRestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserServiceRestClientImpl implements UserServiceRestClient
{
    private final WebClient webClient;

    @Value("${user-service.url}")
    private String userServiceUrl;

    public UserServiceRestClientImpl(WebClient webClient)
    {
        this.webClient = webClient;
    }

    @Override
    public Mono<FlightDetailsDTO> getFlightDetails(Long flightId)
    {
        var url = userServiceUrl.concat("/flights/{id}");

        return webClient.get()
                .uri(url, flightId)
                .retrieve()
                .bodyToMono(FlightResponse.class)
                .flatMap(flightResponse -> Mono.just(flightResponse.getFlightDetailsDTO()))
                .log();
    }

    @Override
    public Mono<CardDetailsDTO> getCardDetails(Long cardId)
    {
//        var url = userServiceUrl.concat("cardDetails/{id}");
//
//        return webClient.get()
//                .uri(url, cardId)
//                .retrieve()
//                .bodyToMono(CardDetailsDTO.class)
//                .log();
        return Mono.just(new CardDetailsDTO(10L, "RO32PORL7222448228239873"));
    }
}
