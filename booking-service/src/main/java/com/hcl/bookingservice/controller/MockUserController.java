package com.hcl.bookingservice.controller;


import com.hcl.bookingservice.dto.CardDetailsDTO;
import com.hcl.bookingservice.dto.FlightDetailsDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping()
public class MockUserController
{
    @GetMapping("/flight/{id}")
    public Mono<FlightDetailsDTO> getFlight(@PathVariable Long id)
    {
        return Mono.just(new FlightDetailsDTO(10L, "Bucuresti", "Iasi"));
    }

    @GetMapping("/cardDetails/{id}")
    public Mono<CardDetailsDTO> getCardDetails(@PathVariable Long id)
    {
        return Mono.just(new CardDetailsDTO(10L, "sdsadsdasdsadSA"));
    }
}
