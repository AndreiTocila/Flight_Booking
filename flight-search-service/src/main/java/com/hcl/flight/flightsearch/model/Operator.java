package com.hcl.flight.flightsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Operator {
    @Id
    private String id;
    private String name;
    private String iban;
    private String apiUri;

    public Operator(String id, String name, String iban, String apiUri) {
        this.id = id;
        this.name = name;
        this.iban = iban;
        this.apiUri = apiUri;
    }

    public Operator(String name, String iban, String apiUri) {
        this.name = name;
        this.iban = iban;
        this.apiUri = apiUri;
    }

    public Operator() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getApiUri() {
        return apiUri;
    }

    public void setApiUri(String apiUri) {
        this.apiUri = apiUri;
    }
}