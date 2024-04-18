package com.hcl.notification.kafka.listener;

import com.hcl.kafka.dto.NotificationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Component
public class NotificationListener {
    private static final Logger logger = LoggerFactory.getLogger(NotificationListener.class);
    private static final List<NotificationDTO> messages = Collections.synchronizedList(new LinkedList<>());

    @KafkaListener(topics = "notification", groupId = "notification-group", containerFactory = "kafkaListenerContainerFactory")
    public void listen(NotificationDTO notification) {
        logger.info("Received notification for user {}: {}", notification.getUserEmail(), notification.getNotificationMessage());
        messages.add(notification);
    }

    public static List<NotificationDTO> getMessages() {
        return new LinkedList<>(messages);
    }

    public static void clearMessages() {
        messages.clear();
    }
}
