package com.project.paypal.model;

import java.io.Serializable;

public class OrderEntity implements Serializable {

    String payerId;
    String payerEmail;
    String payeeId;
    String payeeEmail;

    public OrderEntity(String payerId, String payerEmail, String payeeId, String payeeEmail) {
        this.payerId = payerId;
        this.payerEmail = payerEmail;
        this.payeeId = payeeId;
        this.payeeEmail = payeeEmail;
    }

    public OrderEntity(){
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
}
