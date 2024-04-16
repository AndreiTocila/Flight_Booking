package com.hcl.notification.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);

    @KafkaListener(topics = {"booking-confirmed", "payment-received", "flight-status-updated"}, groupId = "notification-group")
    public void listen(String message) {
        logger.info("Received message in group notification-group: {}", message);
    }
}
