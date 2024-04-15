package com.hcl.flight.userservice.controller;

import com.hcl.flight.userservice.dto.UserDTO;
import com.hcl.flight.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_USER')")
  public UserDTO getById(@PathVariable Long id) {
    return userService.findById(id).get();
  }

  @GetMapping("/{email}")
  @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_USER')")
  public ResponseEntity<String> getIban(@PathVariable String email) {
    return ResponseEntity.ok(userService.getIban(email));
  }
}
