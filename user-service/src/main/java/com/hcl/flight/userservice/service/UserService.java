package com.hcl.flight.userservice.service;

import com.hcl.flight.userservice.dto.UserDTO;
import com.hcl.flight.userservice.dto.request.LoginRequest;
import com.hcl.flight.userservice.dto.response.LoginResponse;
import com.hcl.flight.userservice.dto.response.data.LoginResponseData;
import com.hcl.flight.userservice.mapper.UserMapper;
import com.hcl.flight.userservice.repository.UserRepository;
import com.hcl.flight.userservice.utils.UserNotFoundException;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.representations.idm.authorization.AuthorizationRequest;
import org.keycloak.representations.idm.authorization.AuthorizationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {
  @Value("${keycloak.auth-server-url}")
  private String serverUrl;

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public UserService(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  public Optional<UserDTO> findById(Long id) {
    return userRepository.findById(id).map(userMapper::entityToDTO);
  }

  public String getIban(String email) {
    return userRepository.findByEmail(email).getIban();
  }

  public LoginResponse authenticateUser(LoginRequest loginRequest) {

    try {
      if (loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
        throw new UserNotFoundException("User not found");
      }
      AuthzClient authzClient = AuthzClient.create();
      AuthorizationRequest authorizationRequest = new AuthorizationRequest();

      AuthorizationResponse response =
          authzClient
              .authorization(loginRequest.getUsername(), loginRequest.getPassword())
              .authorize(authorizationRequest);
      String rpt = response.getToken();

      UserDTO userDTO =
          userMapper.entityToDTO(userRepository.findByEmail(loginRequest.getUsername()));

      if (userDTO != null) {
        LoginResponseData loginResponseData = new LoginResponseData();
        loginResponseData.setAuthenticationToken(rpt);
        loginResponseData.setUserDTO(userDTO);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setLoginResponseData(loginResponseData);
        return loginResponse;
      } else {
        throw new UserNotFoundException("User not found for email: " + loginRequest.getUsername());
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("An error occurred while authenticating user", e);
    }
  }
}
