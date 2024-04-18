package com.hcl.notification.service;

import com.hcl.kafka.dto.NotificationDTO;
import com.hcl.notification.model.Notification;
import com.hcl.notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private static final String TOPIC = "notification";

    @Autowired
    private KafkaTemplate<String, NotificationDTO> kafkaTemplate;

    @Autowired
    private NotificationRepository notificationRepository;

    public void sendNotification(NotificationDTO notificationDTO) {
        kafkaTemplate.send(TOPIC, notificationDTO.getUserEmail(), notificationDTO);
        Notification notification = new Notification();
        notification.setUserEmail(notificationDTO.getUserEmail());
        notification.setNotificationMessage(notificationDTO.getNotificationMessage());
        notificationRepository.save(notification);
    }
}
