package com.hcl.flight.userservice.model;

import jakarta.persistence.*;

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

  @OneToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User userId;

  public Operator(Long id, String name, String iban, String uri, User userId) {
    this.id = id;
    this.name = name;
    this.iban = iban;
    this.uri = uri;
    this.userId = userId;
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

  public User getUserId() {
    return userId;
  }

  public void setUserId(User userId) {
    this.userId = userId;
  }
}
