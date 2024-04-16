package com.hcl.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hcl.notification.kafka.MessageProducer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/mock/payment")
public class MockPaymentController {

    private final MessageProducer messageProducer;

    @Autowired
    public MockPaymentController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> processPayment(@RequestBody Map<String, String> paymentDetails) {
        Map<String, Object> paymentResponse = new HashMap<>();
        paymentResponse.put("status", "success");
        paymentResponse.put("transactionId", UUID.randomUUID().toString());
        messageProducer.sendMessage("payment-received", "Payment processed: " + paymentResponse.toString());
        return ResponseEntity.ok(paymentResponse);
    }
}
