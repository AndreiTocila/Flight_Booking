package com.hcl.bookingservice.service.impl;

import com.hcl.bookingservice.domain.Booking;
import com.hcl.bookingservice.dto.BookingDTO;
import com.hcl.bookingservice.dto.CardDetailsDTO;
import com.hcl.bookingservice.dto.FlightDetailsDTO;
import com.hcl.bookingservice.repository.BookingRepository;
import com.hcl.bookingservice.service.BookingService;
import com.hcl.bookingservice.service.KafkaService;
import com.hcl.bookingservice.service.UserServiceRestClient;
import org.bson.internal.BsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Service
public class BookingServiceImpl implements BookingService
{
    private final BookingRepository bookingRepository;

    private final KafkaService kafkaService;

    private final UserServiceRestClient userServiceRestClient;

    public BookingServiceImpl(BookingRepository bookingRepository, KafkaService kafkaService,  UserServiceRestClient userServiceRestClient)
    {
        this.bookingRepository = bookingRepository;
        this.kafkaService = kafkaService;
        this.userServiceRestClient = userServiceRestClient;
    }

    @Override
    public Mono<Booking> saveBooking(BookingDTO bookingDTO)
    {
        Booking booking = new Booking();
        booking.setId(null);
        booking.setNumberOfSeats(bookingDTO.getNumberOfSeats());
        booking.setStatus("RESERVED");
        booking.setUserId("testUserId");
        booking.setExpirationDate(LocalDateTime.now().plusMinutes(15L));

        Mono<FlightDetailsDTO> flightDetailsDTOMono = userServiceRestClient.getFlightDetails(bookingDTO.getFlightId());
        Mono<CardDetailsDTO> cardDetailsDTOMono = userServiceRestClient.getCardDetails(bookingDTO.getCardDetailsId());

        return Mono.zip(flightDetailsDTOMono, cardDetailsDTOMono)
                .flatMap(tuple ->
                {
                    FlightDetailsDTO flightDetails = tuple.getT1();
                    CardDetailsDTO cardDetails = tuple.getT2();

                    booking.setFlight(flightDetails);
                    booking.setCardDetails(cardDetails);

                    return bookingRepository.save(booking).doOnNext(kafkaService::sendAddBookingMessages);
                })
                .log();
    }

    @Override
    public Flux<Booking> findAllExpired()
    {
        return bookingRepository.findByExpirationDateLessThanAndStatus(LocalDateTime.now().minusMinutes(15L), "RESERVED");
    }


}
