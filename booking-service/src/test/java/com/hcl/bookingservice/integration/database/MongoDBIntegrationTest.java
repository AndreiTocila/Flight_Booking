package com.hcl.bookingservice.integration.database;

import com.hcl.bookingservice.domain.Booking;
import com.hcl.bookingservice.dto.CardDetailsDTO;
import com.hcl.bookingservice.dto.FlightDetailsDTO;
import com.hcl.bookingservice.repository.BookingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class MongoDBIntegrationTest
{
    @Container
    public static MongoDBContainer MONGODB_CONTAINER = new MongoDBContainer("mongo:latest");

    @Autowired
    private BookingRepository bookingRepository;

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry)
    {
        registry.add("spring.data.mongodb.uri", () -> "mongodb://" + MONGODB_CONTAINER.getHost() + ":" + MONGODB_CONTAINER.getFirstMappedPort() + "/Bookings");
    }

    @Test
    public void saveBookingTest()
    {
        FlightDetailsDTO flightDetailsDTO = new FlightDetailsDTO(123L, "BUC", "Iasi", 100.0, "RO32PORL7222448228239873");
        CardDetailsDTO cardDetailsDTO = new CardDetailsDTO("RO32PORL7222448228239873");
        Booking booking = new Booking("661e2494a634e14e1f35142e", flightDetailsDTO, cardDetailsDTO, 5L, "testUserId","RESERVED", LocalDateTime.now().plusMinutes(15L));

        Booking savedBooking = bookingRepository.save(booking).block();

        assertEquals(booking,savedBooking);

    }

    @Test
    public void findBookingTest()
    {
        FlightDetailsDTO flightDetailsDTO = new FlightDetailsDTO(123L, "BUC", "Iasi", 100.0, "RO32PORL7222448228239873");
        CardDetailsDTO cardDetailsDTO = new CardDetailsDTO("RO32PORL7222448228239873");
        Booking booking = new Booking("661e2494a634e14e1f35142e", flightDetailsDTO, cardDetailsDTO, 5L, "testUserId","RESERVED", LocalDateTime.now().plusMinutes(15L));

        bookingRepository.save(booking).block();

        Booking foundBooking = bookingRepository.findById("661e2494a634e14e1f35142e").block();

        assertEquals(booking.getId(),foundBooking.getId());
    }

}
