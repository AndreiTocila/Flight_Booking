package com.hcl.notification.model;

public class NotificationPOJO {
    private String userEmail;
    private String notificationMessage;

    public NotificationPOJO(String userEmail, String notificationMessage) {
        this.userEmail = userEmail;
        this.notificationMessage = notificationMessage;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }
}
