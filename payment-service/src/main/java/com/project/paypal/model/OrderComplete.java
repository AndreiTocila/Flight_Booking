package com.project.paypal.model;

import java.io.Serializable;

public class OrderComplete implements Serializable {
    private String status;
    private String payId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public OrderComplete() {
    }

    public OrderComplete(String status, String payId) {
        this.status = status;
        this.payId = payId;
    }

    public OrderComplete(String status) {
        this.status = status;
    }
}
