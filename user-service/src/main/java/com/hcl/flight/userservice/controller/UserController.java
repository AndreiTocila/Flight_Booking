package com.hcl.flight.userservice.controller;

import com.hcl.flight.userservice.dto.UserDTO;
import com.hcl.flight.userservice.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/user")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_admin')")
  public UserDTO getById(@PathVariable Long id) {
    return userService.findById(id);
  }
}
