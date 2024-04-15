package com.hcl.flight.userservice.dto;

import com.hcl.flight.userservice.model.User;

public class UserDTO {
  private Long id;

  private String firstName;

  private String lastName;

  private String email;


  private String address;

  private String phoneNumber;

  private String iban;


  public UserDTO(Long id, String firstName, String email, String lastName , String address, String phoneNumber, String iban, User user) {
    this.id = id;
    this.firstName = firstName;
    this.email = email;
    this.lastName = lastName;

    this.address = address;
    this.phoneNumber = phoneNumber;
    this.iban = iban;
  }

  public UserDTO() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

}
