package com.hcl.notification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mock/user")
public class MockUserController {

    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, String>> getUserDetails(@PathVariable String userId) {
        Map<String, String> userDetails = new HashMap<>();
        userDetails.put("userId", userId);
        userDetails.put("name", "John Doe");
        userDetails.put("email", "john.doe@example.com");
        return ResponseEntity.ok(userDetails);
    }
}
