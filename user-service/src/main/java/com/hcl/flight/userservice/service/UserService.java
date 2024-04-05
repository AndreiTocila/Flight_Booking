package com.hcl.flight.userservice.service;

import com.hcl.flight.userservice.dto.UserDTO;
import com.hcl.flight.userservice.dto.request.LoginRequest;
import com.hcl.flight.userservice.dto.response.LoginResponse;
import com.hcl.flight.userservice.dto.response.data.LoginResponseData;
import com.hcl.flight.userservice.mapper.UserMapper;
import com.hcl.flight.userservice.model.User;
import com.hcl.flight.userservice.repository.UserRepository;
import org.keycloak.admin.client.Keycloak;
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

  public UserDTO findById(Long id) {
    Optional<User> user = userRepository.findById(id);
    return user.map(userMapper::entityToDTO).orElse(null);
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
        userMapper.entityToDTO(userRepository.findByEmail(loginRequest.getUsername()).get());

    LoginResponseData loginResponseData = new LoginResponseData();
    loginResponseData.setAuthenticationToken(rpt);
    loginResponseData.setUserDTO(userDTO);
    LoginResponse loginResponse = new LoginResponse();
    loginResponse.setLoginResponseData(loginResponseData);
    return loginResponse;
  }
}
