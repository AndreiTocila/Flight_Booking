package com.hcl.bookingservice.service;

import com.hcl.bookingservice.domain.Booking;

public interface KafkaService
{
    public void sendAddBookingMessages(Booking booking);
}
