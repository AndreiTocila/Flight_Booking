package com.hcl.bookingservice.kafka;

import com.hcl.bookingservice.service.BookingService;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class AdminKafkaConsumer
{

    private final BookingService bookingService;

    private final Logger logger = LoggerFactory.getLogger(AdminKafkaConsumer.class);

    public AdminKafkaConsumer(BookingService bookingService)
    {
        this.bookingService = bookingService;
    }

    @KafkaListener(topics = "admin_feedback", groupId = "booking_group", containerFactory = "containerFactory")
    public void consume(ConsumerRecord<Long, GenericRecord> record)
    {
        logger.info("Received admin feedback: " + record.value());
        bookingService.updateBookingAfterAdminValidation(record);
        logger.info("Finished processing admin feedback: " + record.value());
    }
}
