package com.hcl.bookingservice.controller;

import com.hcl.bookingservice.domain.Booking;
import com.hcl.bookingservice.dto.BookingDTO;
import com.hcl.bookingservice.service.BookingService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/booking")
public class BookingController
{

    private final BookingService bookingService;

    public BookingController(BookingService bookingService)
    {
        this.bookingService = bookingService;
    }

    @PostMapping()
    public Mono<ResponseEntity<Booking>> createBooking(@RequestBody BookingDTO bookingDTO, @AuthenticationPrincipal Jwt jwt)
    {
        return bookingService.saveBooking(bookingDTO, jwt)
                .map(booking -> new ResponseEntity<>(booking, HttpStatus.CREATED))
                .log();
    }

    @GetMapping("/expired")
    public ResponseEntity<Flux<Booking>> getAllExpiredBookings()
    {
        return new ResponseEntity<>(bookingService.findAllExpired(), HttpStatus.OK);
    }
}
