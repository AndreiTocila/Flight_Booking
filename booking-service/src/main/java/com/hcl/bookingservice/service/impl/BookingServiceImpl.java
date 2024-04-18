package com.hcl.bookingservice.service.impl;

import com.hcl.bookingservice.domain.Booking;
import com.hcl.bookingservice.dto.BookingDTO;
import com.hcl.bookingservice.dto.CardDetailsDTO;
import com.hcl.bookingservice.dto.FlightDetailsDTO;
import com.hcl.bookingservice.repository.BookingRepository;
import com.hcl.bookingservice.service.BookingService;
import com.hcl.bookingservice.service.KafkaService;
import com.hcl.bookingservice.service.UserServiceRestClient;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

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
    public Mono<Booking> saveBooking(BookingDTO bookingDTO, Jwt jwt)
    {
        Booking booking = new Booking();
        booking.setId(null);
        booking.setNumberOfSeats(bookingDTO.getNumberOfSeats());
        booking.setStatus("RESERVED");
        booking.setEmail(jwt.getClaimAsString("email"));
        booking.setExpirationDate(LocalDateTime.now().plusMinutes(15L));

        Mono<FlightDetailsDTO> flightDetailsDTOMono = userServiceRestClient.getFlightDetails(bookingDTO.getFlightId(), jwt.getTokenValue());
        Mono<String> ibanMono = userServiceRestClient.getCardDetails(bookingDTO.getCardDetailsId(), jwt);

        return Mono.zip(flightDetailsDTOMono, ibanMono)
                .flatMap(tuple ->
                {
                    FlightDetailsDTO flightDetails = tuple.getT1();
                    String iban = tuple.getT2();

                    booking.setFlight(flightDetails);
                    booking.setCardDetails(new CardDetailsDTO(iban));

                    return bookingRepository.save(booking).doOnNext(kafkaService::sendAddBookingMessages);
                })
                .log();
    }

    @Override
    public void updateBookingAfterAdminValidation(ConsumerRecord<Long, GenericRecord> record)
    {
        GenericRecord genericRecord = record.value();
        bookingRepository.findById((String) genericRecord.get("bookingId"))
                .flatMap(booking ->
                {
                    Boolean validated = (Boolean) genericRecord.get("statusValidation");
                    if (validated)
                    {
                        kafkaService.sendAfterAdminValidationMessages(record.key(), booking);
                        return Mono.just(booking);
                    }
                    else
                    {
                        booking.setStatus("REJECTED");
                        return bookingRepository.save(booking);
                    }
                })
                .subscribe();
    }

    @Override
    public void updateBookingAfterPaymentValidation(ConsumerRecord<Long, GenericRecord> record)
    {
        GenericRecord genericRecord = record.value();
        bookingRepository.findById((String) genericRecord.get("bookingId"))
                .flatMap(booking ->
                {
                    Boolean validated = (Boolean) genericRecord.get("statusValidation");
                    String status = validated ? "ACCEPTED" : "REJECTED";
                    kafkaService.sendAfterPaymentValidationMessages(booking, status, validated);

                    booking.setStatus(status);
                    return bookingRepository.save(booking);
                })
                .subscribe();
    }

    @Override
    public Flux<Booking> findAllExpired()
    {
        return bookingRepository.findByExpirationDateLessThanAndStatus(LocalDateTime.now().minusMinutes(15L), "RESERVED");
    }


}
