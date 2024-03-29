package com.hcl.flight.userservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "user_service", name = "operators")
public class Operator {

  @Id
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "iban")
  private String iban;

  @Column(name = "uri")
  private String uri;

  public Operator(Long id, String name, String iban, String uri) {
    this.id = id;
    this.name = name;
    this.iban = iban;
    this.uri = uri;
  }

  public Operator() {}

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
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
