package com.hcl.bookingservice.controller;


import com.hcl.bookingservice.dto.CardDetailsDTO;
import com.hcl.bookingservice.dto.FlightDetailsDTO;
import com.hcl.bookingservice.dto.temp.FlightResponse;
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
    public FlightResponse getFlight(@PathVariable Long id)
    {
        return new FlightResponse(new FlightDetailsDTO(3213L, "IASI", "BUCURESTI", 100.00, "RO52RZBR2717288441671358"));
    }

    @GetMapping("/cardDetails/{id}")
    public Mono<CardDetailsDTO> getCardDetails(@PathVariable Long id)
    {
        return Mono.just(new CardDetailsDTO(10L, "RO32PORL7222448228239873"));
    }
}
