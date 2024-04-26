package com.hcl.flight.userservice.controller;

import com.hcl.flight.userservice.dto.request.LoginRequest;
import com.hcl.flight.userservice.dto.request.UserRequest;
import com.hcl.flight.userservice.dto.response.LoginResponse;
import com.hcl.flight.userservice.service.KeycloakService;
import com.hcl.flight.userservice.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
  private final UserService userService;
  private final KeycloakService keycloakService;

  public AuthenticationController(UserService userService, KeycloakService keycloakService) {
    this.userService = userService;
    this.keycloakService = keycloakService;
  }

  @GetMapping("/login")
  public LoginResponse login(@RequestBody LoginRequest loginRequest) {
    return userService.authenticateUser(loginRequest);
  }

  @GetMapping("/register")
  public void register(@RequestBody UserRequest userRequest) {
    keycloakService.createUser(userRequest);
  }
}
