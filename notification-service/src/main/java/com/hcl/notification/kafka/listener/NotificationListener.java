package com.hcl.notification.kafka.listener;

import com.hcl.kafka.dto.NotificationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Component
public class NotificationListener {

    @Autowired
    private JavaMailSender emailSender;

    private static final Logger logger = LoggerFactory.getLogger(NotificationListener.class);

    @KafkaListener(topics = "notification", groupId = "notification-group", containerFactory = "kafkaListenerContainerFactory")
    public void listen(NotificationDTO notification) {
        logger.info("Received notification for user {}: {}", notification.getUserEmail(), notification.getNotificationMessage());

        sendEmail(notification.getUserEmail(), notification.getNotificationMessage());
    }

    private void sendEmail(String to, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("porumbmihai20@stud.ase.ro");
        message.setTo(to);
        message.setSubject("New Notification");
        message.setText(body);
        emailSender.send(message);
    }
}
