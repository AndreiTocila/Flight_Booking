package com.hcl.flight.userservice.dto.request;

public class UserRequest {

  private String email;
  private String password;
  private String firstName;
  private String lastName;
  private String address;
  private String phoneNumber;
  private String iban;
  private String role;

  public UserRequest() {}

  public UserRequest(
      String email,
      String password,
      String firstName,
      String address,
      String lastName,
      String phoneNumber,
      String iban,
      String role) {
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.address = address;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.iban = iban;
    this.role = role;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone_number() {
    return phoneNumber;
  }

  public void setPhone_number(String phoneNumber) {
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
}
