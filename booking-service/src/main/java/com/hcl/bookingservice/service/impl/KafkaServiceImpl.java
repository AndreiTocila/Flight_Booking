package com.hcl.bookingservice.service.impl;

import com.hcl.bookingservice.domain.Booking;
import com.hcl.bookingservice.service.KafkaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaServiceImpl implements KafkaService
{
    private final static Logger logger = LoggerFactory.getLogger(KafkaServiceImpl.class);

    private final KafkaTemplate<Long, Integer> adminKafkaTemplate;

    private final KafkaTemplate<String, String> notificationKafkaTemplate;

    public KafkaServiceImpl(KafkaTemplate<Long, Integer> adminKafkaTemplate, KafkaTemplate<String, String> notificationKafkaTemplate)
    {
        this.adminKafkaTemplate = adminKafkaTemplate;
        this.notificationKafkaTemplate = notificationKafkaTemplate;
    }

    @Override
    public void sendAddBookingMessages(Booking booking)
    {
        CompletableFuture.supplyAsync(() -> {
            try
            {
                Thread.sleep(1000L);
            } catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
            System.out.println("Trimit");
            adminKafkaTemplate.send("seat_reservation", booking.getFlight().getId(), booking.getNumberOfSeats());

            notificationKafkaTemplate.send("notification", booking.getUserId(), "Status for flight " + booking.getFlight().getId() + ": " + booking.getStatus());

            return null;
        }).whenComplete((result, exception) ->
        {
            if (exception == null)
            {
                logger.info("Booking: " + booking + " processed successfully");
            } else
            {
                logger.error(exception.getMessage());
            }
        });
    }
}
