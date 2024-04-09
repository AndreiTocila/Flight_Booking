package com.hcl.bookingservice.service;

import com.hcl.bookingservice.domain.Booking;
import org.apache.avro.generic.GenericRecord;

public interface KafkaService
{
    void sendAddBookingMessages(Booking booking);

    void sendAfterAdminValidationMessages(Long flightId, Booking booking);

    void sendAfterPaymentValidationMessages(Booking booking, String status);
}
