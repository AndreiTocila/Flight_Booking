package com.project.paypal.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orderAuth")
public class OrderAuthorize {

    @Id
    private String id;
    private String orderId;
    private String authId;
    private String captureId;
    private String status;
    private String creationTime;
    private String expirationTime;
    private String bookingId;
    private String iban;
    private Long flightId;

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getCaptureId() {
        return captureId;
    }

    public void setCaptureId(String captureId) {
        this.captureId = captureId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public OrderAuthorize() {
    }

//    public OrderAuthorize(String id, String orderId, String authId, String captureId, String status, String creationTime, String expirationTime, String bookingId, Long flightId) {
//        this.id = id;
//        this.orderId = orderId;
//        this.authId = authId;
//        this.captureId = captureId;
//        this.status = status;
//        this.creationTime = creationTime;
//        this.expirationTime = expirationTime;
//        this.bookingId = bookingId;
//        this.flightId = flightId;
//    }


    public OrderAuthorize(String id, String orderId, String authId, String captureId, String status, String creationTime, String expirationTime, String bookingId, String iban, Long flightId) {
        this.id = id;
        this.orderId = orderId;
        this.authId = authId;
        this.captureId = captureId;
        this.status = status;
        this.creationTime = creationTime;
        this.expirationTime = expirationTime;
        this.bookingId = bookingId;
        this.iban = iban;
        this.flightId = flightId;
    }

    public OrderAuthorize(String id, String orderId, String authId, String captureId, String status, String creationTime, String expirationTime) {
        this.id = id;
        this.orderId = orderId;
        this.authId = authId;
        this.captureId = captureId;
        this.status = status;
        this.creationTime = creationTime;
        this.expirationTime = expirationTime;
    }

    public OrderAuthorize(String status, String id) {
        this.status = status;
        this.id = id;
    }
}
