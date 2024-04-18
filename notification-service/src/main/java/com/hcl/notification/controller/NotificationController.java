package com.hcl.notification.controller;

import com.hcl.kafka.dto.NotificationDTO;
import com.hcl.notification.kafka.listener.NotificationListener;
import com.hcl.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationDTO notification) {
        notificationService.sendNotification(notification);
        return ResponseEntity.ok("Notification sent successfully!");
    }

    @GetMapping("/received")
    public ResponseEntity<List<NotificationDTO>> getReceivedNotifications() {
        List<NotificationDTO> receivedNotifications = NotificationListener.getMessages();
        return ResponseEntity.ok(receivedNotifications);
    }
}
