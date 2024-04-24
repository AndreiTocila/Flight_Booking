package com.hcl.flight.userservice.dto.response.data;

import com.hcl.flight.userservice.dto.UserDTO;

public class LoginResponseData {
    private String authenticationToken;
    private UserDTO userDTO;

    public LoginResponseData() {
    }

    public LoginResponseData(String authenticationToken, UserDTO userDTO) {
        this.authenticationToken = authenticationToken;
        this.userDTO = userDTO;
    }

    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
