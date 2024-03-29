package com.hcl.bookingservice.repository;

import com.hcl.bookingservice.domain.Booking;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends ReactiveMongoRepository<Booking, Long>
{
}
