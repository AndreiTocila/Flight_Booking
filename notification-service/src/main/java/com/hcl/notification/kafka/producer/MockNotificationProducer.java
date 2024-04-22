package com.hcl.notification.kafka.producer;

import com.hcl.kafka.dto.NotificationDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MockNotificationProducer {
    private final KafkaTemplate<String, NotificationDTO> kafkaTemplate;

    @Autowired
    public MockNotificationProducer(KafkaTemplate<String, NotificationDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotification(String topic, NotificationDTO notification) {
        kafkaTemplate.send(topic, notification.getUserEmail(), notification);
    }
}

