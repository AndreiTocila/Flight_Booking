package com.hcl.flight.userservice.service;

import com.hcl.flight.userservice.dto.UserDTO;
import com.hcl.flight.userservice.dto.request.LoginRequest;
import com.hcl.flight.userservice.dto.response.LoginResponse;
import com.hcl.flight.userservice.dto.response.data.LoginResponseData;
import com.hcl.flight.userservice.mapper.UserMapper;
import com.hcl.flight.userservice.repository.UserRepository;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.representations.idm.authorization.AuthorizationRequest;
import org.keycloak.representations.idm.authorization.AuthorizationResponse;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {

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
    }
    return null;
  }
}
