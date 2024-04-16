package com.hcl.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hcl.notification.kafka.MessageProducer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/mock/booking")
public class MockBookingController {

    private final MessageProducer messageProducer;

    @Autowired
    public MockBookingController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createBooking(@RequestBody Map<String, String> bookingDetails) {
        bookingDetails.put("status", "confirmed");
        bookingDetails.put("bookingId", UUID.randomUUID().toString());
        messageProducer.sendMessage("booking-confirmed", "Booking confirmed: " + bookingDetails.toString());
        return ResponseEntity.ok(bookingDetails);
    }
}
