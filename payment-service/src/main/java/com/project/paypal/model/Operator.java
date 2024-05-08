package com.project.paypal.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "operators")
public class Operator {

    @Id
    private String id;
    private String iban;
    private String clientId;
    private String clientSecret;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public Operator(String id, String iban, String clientId, String clientSecret) {
        this.id = id;
        this.iban = iban;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public Operator() {
    }
}
