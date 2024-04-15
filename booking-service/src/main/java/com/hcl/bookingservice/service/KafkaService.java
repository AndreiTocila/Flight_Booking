package com.hcl.bookingservice.service;

import com.hcl.bookingservice.domain.Booking;

public interface KafkaService
{
    void sendAddBookingMessages(Booking booking);

    void sendRejectedMessages(Booking booking, String message);

    void sendAfterAdminValidationMessages(Long flightId, Booking booking);

    void sendAfterPaymentValidationMessages(Booking booking, String status, Boolean validated);
}
