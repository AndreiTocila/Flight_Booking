package com.hcl.bookingservice.repository;

import com.hcl.bookingservice.domain.Booking;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository
public interface BookingRepository extends ReactiveMongoRepository<Booking, String>
{
    Flux<Booking> findByExpirationDateLessThanAndStatus(LocalDateTime localDateTime, String status);
}
