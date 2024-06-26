package com.hcl.bookingservice.service;

import com.hcl.bookingservice.domain.Booking;
import com.hcl.bookingservice.dto.BookingDTO;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.security.oauth2.jwt.Jwt;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookingService
{
    Mono<Booking> saveBooking(BookingDTO bookingDTO, Jwt jwt);

    void updateBookingAfterAdminValidation(ConsumerRecord<Long, GenericRecord> record);

    void updateBookingAfterPaymentValidation(ConsumerRecord<Long, GenericRecord> record);

    Flux<Booking> findAllExpired();
}
