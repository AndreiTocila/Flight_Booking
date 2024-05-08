package com.project.paypal.model;

import org.springframework.data.annotation.Id;

public class OrderRefund {

    @Id
    private String id;
    private String refundId;
    private String status;
    private Long creationTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return refundId;
    }

    public void setOrderId(String refundId) {
        this.refundId = refundId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }

    public OrderRefund() {
    }

    public OrderRefund(String id, String refundId, String status, Long creationTime) {
        this.id = id;
        this.refundId = refundId;
        this.status = status;
        this.creationTime = creationTime;
    }

    public OrderRefund(String orderId, String status) {
        this.refundId = orderId;
        this.status = status;
    }
}
