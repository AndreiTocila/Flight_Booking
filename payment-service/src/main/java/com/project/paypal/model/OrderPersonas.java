package com.project.paypal.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "personas")
public class OrderPersonas {

    @Id
    private String id;
    private String orderId;
    private String payerId;
    private String payerEmail;
    private String payeeId;
    private String payeeEmail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getPayerEmail() {
        return payerEmail;
    }

    public void setPayerEmail(String payerEmail) {
        this.payerEmail = payerEmail;
    }

    public String getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(String payeeId) {
        this.payeeId = payeeId;
    }

    public String getPayeeEmail() {
        return payeeEmail;
    }

    public void setPayeeEmail(String payeeEmail) {
        this.payeeEmail = payeeEmail;
    }

    public OrderPersonas(String id, String orderId, String payerId, String payerEmail, String payeeId, String payeeEmail) {
        this.id = id;
        this.orderId = orderId;
        this.payerId = payerId;
        this.payerEmail = payerEmail;
        this.payeeId = payeeId;
        this.payeeEmail = payeeEmail;
    }

    public OrderPersonas() {
    }
}
