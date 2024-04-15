package com.hcl.flight.userservice.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Objects;

@Entity
@Table(schema = "user_service", name = "users")
public class User {
  @Id
  @SequenceGenerator(
      name = "users_seq",
      schema = "user_service",
      sequenceName = "user_sequence",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email")
  private String email;

  @Column(name = "address")
  private String address;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "iban")
  private String iban;

  @Column(name = "role")
  private String role;

  public User(
      Long id,
      String firstName,
      String lastName,
      String email,
      String address,
      String phoneNumber,
      String iban,
      String role) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.iban = iban;
    this.role = role;
  }

  public User() {}

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getIban() {
    return iban;
  }

  public void setIban(String iban) {
    this.iban = iban;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User user)) return false;
    return Objects.equals(getId(), user.getId())
        && Objects.equals(getFirstName(), user.getFirstName())
        && Objects.equals(getLastName(), user.getLastName())
        && Objects.equals(getEmail(), user.getEmail())
        && Objects.equals(getAddress(), user.getAddress())
        && Objects.equals(getPhoneNumber(), user.getPhoneNumber())
        && Objects.equals(getIban(), user.getIban());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        getId(),
        getFirstName(),
        getLastName(),
        getEmail(),
        getAddress(),
        getPhoneNumber(),
        getIban());
  }
}
