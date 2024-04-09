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
public class PaymentKafkaConsumer
{
    private final BookingService bookingService;

    private final Logger logger = LoggerFactory.getLogger(PaymentKafkaConsumer.class);

    public PaymentKafkaConsumer(BookingService bookingService)
    {
        this.bookingService = bookingService;
    }

    @KafkaListener(topics = "payment_feedback", groupId = "booking_group", containerFactory = "containerFactory")
    public void consume(ConsumerRecord<Long, GenericRecord> record)
    {
        logger.info("Received payment feedback: " + record.value());
        bookingService.updateBookingAfterPaymentValidation(record);
        logger.info("Finished processing payment feedback: " + record.value());
    }
}
