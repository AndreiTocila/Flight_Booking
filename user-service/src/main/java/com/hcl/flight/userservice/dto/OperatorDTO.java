package com.hcl.flight.userservice.dto;
public class OperatorDTO {

    private Long id;
    private String name;
    private String iban;

    private String uri;

    public OperatorDTO(Long id, String name, String iban, String uri) {
        this.id = id;
        this.name = name;
        this.iban = iban;
        this.uri = uri;
    }

    public OperatorDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
