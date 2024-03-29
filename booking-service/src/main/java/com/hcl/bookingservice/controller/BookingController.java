package com.hcl.bookingservice.controller;

import com.hcl.bookingservice.domain.Booking;
import com.hcl.bookingservice.dto.BookingDTO;
import com.hcl.bookingservice.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public Mono<ResponseEntity<Booking>> createBooking(@RequestBody BookingDTO bookingDTO)
    {
        return bookingService.saveBooking(bookingDTO)
                .map(booking -> new ResponseEntity<>(booking, HttpStatus.CREATED))
                .log();
    }
}
