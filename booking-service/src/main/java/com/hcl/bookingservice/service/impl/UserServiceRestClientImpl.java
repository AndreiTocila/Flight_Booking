package com.hcl.bookingservice.service.impl;

import com.hcl.bookingservice.dto.CardDetailsDTO;
import com.hcl.bookingservice.dto.FlightDetailsDTO;
import com.hcl.bookingservice.dto.FlightResponse;
import com.hcl.bookingservice.service.UserServiceRestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserServiceRestClientImpl implements UserServiceRestClient
{
    private final WebClient webClient;

    public UserServiceRestClientImpl(WebClient webClient)
    {
        this.webClient = webClient;
    }

    @Override
    public Mono<FlightDetailsDTO> getFlightDetails(Long flightId, String token)
    {
        String url = "/flights/{id}";

        return webClient.get()
                .uri(url, flightId)
                .headers(httpHeaders -> httpHeaders.setBearerAuth(token))
                .retrieve()
                .bodyToMono(FlightResponse.class)
                .flatMap(flightResponse -> Mono.just(flightResponse.getFlightDetailsDTO()))
                .log();
    }

    @Override
    public Mono<String> getCardDetails(Jwt jwt)
    {
        String url = "/user";

        return webClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path(url)
                                .queryParam("email", jwt.getClaimAsString("email"))
                                .build()
                )
                .headers(httpHeaders -> httpHeaders.setBearerAuth(jwt.getTokenValue()))
                .retrieve()
                .bodyToMono(String.class)
                .log();
//        return Mono.just(new CardDetailsDTO(10L, "RO32PORL7222448228239873"));
    }
}
