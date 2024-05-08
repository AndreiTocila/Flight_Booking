package com.project.paypal.model;

import com.paypal.orders.Payee;
import com.paypal.orders.Payer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "personas")
public class OrderPersonas {

    @Id
    private String id;
    private String payerId;
    private String payerEmail;
    private String payeeId;
    private String payeeEmail;
    private Payee payee;
    private Payer payer;

    public Payee getPayee() {
        return payee;
    }

    public void setPayee(Payee payee) {
        this.payee = payee;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public OrderPersonas(Payee payee, Payer payer) {
        this.payee = payee;
        this.payer = payer;
    }

    public OrderPersonas() {
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
