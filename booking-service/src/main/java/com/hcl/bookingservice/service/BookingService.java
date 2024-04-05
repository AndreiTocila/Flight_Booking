package com.hcl.bookingservice.service;

import com.hcl.bookingservice.domain.Booking;
import com.hcl.bookingservice.dto.BookingDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookingService
{
    Mono<Booking> saveBooking(BookingDTO bookingDTO);

    Flux<Booking> findAllExpired();
}
