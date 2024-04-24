package com.hcl.flight.userservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hcl.flight.userservice.dto.response.data.LoginResponseData;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse {
  @JsonProperty("data")
  private LoginResponseData loginResponseData;

  public LoginResponse(LoginResponseData loginResponseData) {
    this.loginResponseData = loginResponseData;
  }

  public LoginResponse() {}

  public LoginResponseData getLoginResponseData() {
    return loginResponseData;
  }

  public void setLoginResponseData(LoginResponseData loginResponseData) {
    this.loginResponseData = loginResponseData;
  }
}
